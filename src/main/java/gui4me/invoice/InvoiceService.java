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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gui4me.user.User;
import gui4me.exceptions.invoice.InvoiceAlreadyProcessedException;
import gui4me.exceptions.invoice.InvoiceParseErrorException;
import gui4me.exceptions.invoice.InvoiceUrlIsNotQrCode;
import gui4me.invoice_item.InvoiceItem;
import gui4me.invoice_item.InvoiceItemRepository;
import gui4me.product.ProductService;
import gui4me.store.Store;
import gui4me.store.StoreService;

@Service
public class InvoiceService {

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final Pattern cnpjPattern = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductService productService;

    public Invoice save(String invoiceUrl, User user) {
        if (isQrCodeUrl(invoiceUrl)) {
            // Proceed normally
            try {
                Document doc = Jsoup.connect(invoiceUrl).get();
                String invoiceKey = doc.getElementsByClass("chave").text();

                if (invoiceRepository.findByKey(invoiceKey).isPresent()) {
                    throw new InvoiceAlreadyProcessedException(invoiceKey);
                }

                Invoice invoice = new Invoice();
                invoice.setKey(invoiceKey);
                invoice.setTotalPrice(parseDouble(doc.selectFirst("span.totalNumb.txtMax").text().trim()));
                invoice.setIssuanceDate(extractIssuanceDate(doc, invoiceUrl));
                invoice.setStore(fetchAndSaveStore(doc));
                invoice.setUser(user);

                processInvoiceItems(doc, invoice);
                return invoiceRepository.save(invoice);
            } catch (IOException e) {
                throw new InvoiceParseErrorException(invoiceUrl);
            }
        } else {
            String invoiceKey = extractKeyFromNfeUrl(invoiceUrl);
            if (invoiceKey == null) {
                throw new InvoiceParseErrorException(invoiceUrl);
            }

            throw new InvoiceUrlIsNotQrCode(invoiceKey);
        }
    }

    private LocalDateTime extractIssuanceDate(Document doc, String invoiceUrl) {
        Element element = doc.selectFirst("div[data-role=collapsible] ul li");

        if (element == null) {
            throw new InvoiceParseErrorException(invoiceUrl);
        }

        Matcher matcher = DATE_PATTERN.matcher(element.text());

        if (matcher.find()) {
            String dateStr = matcher.group();
            return LocalDateTime.parse(dateStr, DATE_FORMATTER);
        } else {
            throw new InvoiceParseErrorException(element.toString(), invoiceUrl);
        }
    }

    private Store fetchAndSaveStore(Document doc) {
        Element cnpjElement = doc.getElementsContainingOwnText("CNPJ:").first();

        if (cnpjElement == null) {
            throw new InvoiceParseErrorException("Could not find CNPJ element", doc.text());
        }

        String storeDocument = cnpjElement.text().replace("CNPJ:", "").strip();
        Matcher matcher = cnpjPattern.matcher(storeDocument);

        if (matcher.find()) {
            String cnpj = matcher.group();
            return storeService.fetchAndSaveByCnpj(cnpj);
        } else {
            throw new InvoiceParseErrorException("Could not extract valid CNPJ", doc.text());
        }
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

    public List<Invoice> findAllByUser(User user, Sort sort) {
        return invoiceRepository.findAllByUser(user, sort);
    }

    private String extractText(Element row, String selector) {
        Element element = row.selectFirst(selector);
        return element != null ? element.text() : "";
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value.replace(",", "."));
        } catch (NumberFormatException e) {
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
