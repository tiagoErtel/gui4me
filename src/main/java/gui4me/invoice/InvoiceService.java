package gui4me.invoice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gui4me.custom_user_details.CustomUserDetails;
import gui4me.exceptions.invoice.InvoiceAlreadyProcessedException;
import gui4me.exceptions.invoice.InvoiceParseErrorException;
import gui4me.invoice_item.InvoiceItem;
import gui4me.invoice_item.InvoiceItemRepository;
import gui4me.product.ProductService;
import gui4me.store.Store;
import gui4me.store.StoreService;

@Service
public class InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductService productService;

    public Invoice save(String invoiceUrl, CustomUserDetails user) {
        if (isQrCodeUrl(invoiceUrl)) {
            // Proceed normally
            try {
                Document doc = Jsoup.connect(invoiceUrl).get();
                String invoicekey = doc.getElementsByClass("chave").text();

                if (invoiceRepository.findByKey(invoicekey).isPresent()) {
                    logger.error("Invoice with key '{}' already exists.", invoicekey);
                    throw new InvoiceAlreadyProcessedException(invoicekey);
                }

                Invoice invoice = new Invoice();
                invoice.setKey(invoicekey);
                invoice.setTotalPrice(parseDouble(doc.selectFirst("span.totalNumb.txtMax").text().trim()));
                invoice.setIssuanceDate(extractIssuanceDate(doc));
                invoice.setStore(createOrFetchStore(doc));
                invoice.setUser(user);

                processInvoiceItems(doc, invoice);
                return invoiceRepository.save(invoice);
            } catch (IOException e) {
                throw new InvoiceParseErrorException();
            }
        } else {
            String key = extractKeyFromNfeUrl(invoiceUrl);
            if (key == null) {
                logger.error("Failed to extract key from NFE URL: {}", invoiceUrl);
                throw new InvoiceParseErrorException();
            }
            String redirectUrl = "https://www.sefaz.rs.gov.br/dfe/Consultas/ConsultaPublicaDfe?keyAcessoDfe="
                    + key;
            throw new InvoiceParseErrorException();
        }
    }

    private LocalDateTime extractIssuanceDate(Document doc) {
        Element element = doc.selectFirst("div[data-role=collapsible] ul li");
        if (element != null) {
            Matcher matcher = DATE_PATTERN.matcher(element.text());
            if (matcher.find()) {
                return LocalDateTime.parse(matcher.group(), DATE_FORMATTER);
            }
        }
        throw new InvoiceParseErrorException();
    }

    private Store createOrFetchStore(Document doc) {
        String storeName = doc.getElementsByClass("txtTopo").text();
        String storeDocument = doc.getElementsContainingOwnText("CNPJ:").text().replace("CNPJ:", "").strip();

        Store store = new Store();
        store.setDocument(storeDocument);
        store.setName(storeName);

        return storeService.checkStoreDocAndSave(store);
    }

    private void processInvoiceItems(Document doc, Invoice invoice) {
        Elements rows = doc.select("table#tabResult tr");

        for (Element row : rows) {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setUnit(extractText(row, "span.RUN").replace("UN:", "").trim());
            invoiceItem.setQuantity(parseDouble(extractText(row, "span.Rqtd").replace("Qtde.:", "").trim()));
            invoiceItem.setUnitPrice(parseDouble(extractText(row, "span.RvlUnit").replace("Vl. Unit.:", "").trim()));
            invoiceItem.setTotalPrice(parseDouble(extractText(row, "span.valor")));
            invoiceItem.setInvoice(invoice);
            invoiceItem.setProduct(productService.getOrCreateProduct(extractText(row, "span.txtTit")));

            invoiceItemRepository.save(invoiceItem);
        }
    }

    public List<Invoice> findAllByUser(CustomUserDetails user) {
        return invoiceRepository.findAllByUser(user);
    }

    private String extractText(Element row, String selector) {
        Element element = row.selectFirst(selector);
        return element != null ? element.text() : "";
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value.replace(",", "."));
        } catch (NumberFormatException e) {
            logger.warn("Failed to parse double value: {}", value);
            return 0.0;
        }
    }

    private boolean isQrCodeUrl(String invoiceUrl) {
        return invoiceUrl.contains("/NFCE/NFCE-COM.aspx?p=");
    }

    private String extractKeyFromNfeUrl(String url) {
        Pattern pattern = Pattern.compile("chaveNFe=(\\d{44})");
        Matcher matcher = pattern.matcher(url);
        return matcher.find() ? matcher.group(1) : null;
    }
}
