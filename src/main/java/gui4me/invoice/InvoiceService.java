package gui4me.invoice;

import gui4me.custom_user_details.CustomUserDetails;
import gui4me.exceptions.InvoiceAlreadyProcessedException;
import gui4me.invoice_item.InvoiceItem;
import gui4me.product.Product;
import gui4me.product.ProductService;
import gui4me.store.Store;
import gui4me.store.StoreService;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

@Service
public class InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    StoreService storeService;

    @Autowired
    ProductService productService;

    public Invoice save(String invoiceUrl, CustomUserDetails user) {
        try {
            // Get the invoice HTML document
            Document doc = Jsoup.connect(invoiceUrl).get();

            // Check if the invoice already exists based on the 'chave'
            String invoiceChave = doc.getElementsByClass("chave").text();
            Optional<Invoice> existingInvoice = invoiceRepository.findByChave(invoiceChave);
            if (existingInvoice.isPresent()) {
                logger.error("Invoice with chave '{}' already exists. Throwing exception.", invoiceChave);
                throw new InvoiceAlreadyProcessedException();
            }

            // Create or fetch the store
            Store store = createOrFetchStore(doc);

            // Create the invoice
            Invoice invoice = new Invoice();
            invoice.setStore(store);
            invoice.setUser(user);
            invoice.setChave(doc.getElementsByClass("chave").text());

            // Process the invoice items
            processInvoiceItems(doc, invoice);

            return invoiceRepository.save(invoice);

        } catch (IOException e) {
            logger.error("Error fetching invoice from URL: " + invoiceUrl, e);
            throw new RuntimeException("Error fetching invoice details", e);
        }
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
            String productName = extractText(row, "span.txtTit");
            String quantityText = extractText(row, "span.Rqtd").replace("Qtde.:", "").trim();
            String unit = extractText(row, "span.RUN").replace("UN:", "").trim();
            String unitPriceText = extractText(row, "span.RvlUnit").replace("Vl. Unit.:", "").trim();
            String totalPriceText = extractText(row, "span.valor");

            invoiceItem.setUnit(unit);
            invoiceItem.setQuantity(parseDouble(quantityText));
            invoiceItem.setUnitPrice(parseDouble(unitPriceText));
            invoiceItem.setTotalPrice(parseDouble(totalPriceText));

            Optional<Product> product = productService.findByName(productName);
            if (product.isEmpty()) {
                Product newProduct = new Product();
                newProduct.setName(productName);
                newProduct = productService.save(newProduct);
                invoiceItem.setProduct(newProduct);
            } else {
                invoiceItem.setProduct(product.get());
            }

            invoice.addInvoiceItem(invoiceItem);
        }
    }

    private String extractText(Element row, String selector) {
        Element element = row.selectFirst(selector);
        return element != null ? element.text() : "";
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value.replace(",", "."));
        } catch (NumberFormatException e) {
            logger.warn("Failed to parse double value: " + value);
            return 0.0; // or consider throwing an exception depending on your needs
        }
    }
}