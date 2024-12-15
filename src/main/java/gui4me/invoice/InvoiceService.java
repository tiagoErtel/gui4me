package gui4me.invoice;

import gui4me.custom_user_details.CustomUserDetails;
import gui4me.invoice_item.InvoiceItem;
import gui4me.product.Product;
import gui4me.product.ProductService;
import gui4me.store.Store;
import gui4me.store.StoreRepository;
import gui4me.store.StoreService;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.*;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    StoreService storeService;

    @Autowired
    ProductService productService;

    public Invoice save(String invoiceUrl, CustomUserDetails user) {
        try{
            // Get the invoice html
            Document doc = Jsoup.connect(invoiceUrl).get();

            // Store
            String storeName = doc.getElementsByClass("txtTopo").text();

            String storeDocument = doc.getElementsContainingOwnText("CNPJ:").text().replace("CNPJ:", "").strip();

            Store store = new Store();
            store.setDocument(storeDocument);
            store.setName(storeName);
            store = storeService.checkStoreDocAndSave(store);

            // Invoice
            Invoice invoice = new Invoice();
            // Select all table rows under the table with id 'tabResult'
            Elements rows = doc.select("table#tabResult tr");

            // Loop through each row and extract details
            for (Element row : rows) {
                InvoiceItem invoiceItem = new InvoiceItem();
                // Extract product name
                String productName = row.selectFirst("span.txtTit") != null
                        ? row.selectFirst("span.txtTit").text()
                        : "";

                // Extract quantity
                String quantity = row.selectFirst("span.Rqtd") != null
                        ? row.selectFirst("span.Rqtd").text().replace("Qtde.:", "").trim()
                        : "";

                // Extract unit
                String unit = row.selectFirst("span.RUN") != null
                        ? row.selectFirst("span.RUN").text().replace("UN:", "").trim()
                        : "";

                // Extract unit price
                String unitPrice = row.selectFirst("span.RvlUnit") != null
                        ? row.selectFirst("span.RvlUnit").text().replace("Vl. Unit.:", "").trim()
                        : "";

                // Extract total price
                String totalPrice = row.selectFirst("span.valor") != null
                        ? row.selectFirst("span.valor").text()
                        : "";

                invoiceItem.setUnit(unit);
                invoiceItem.setQuantity(Double.parseDouble(quantity.replace(",", ".")));
                invoiceItem.setUnitPrice(Double.parseDouble(unitPrice.replace(",", ".")));
                invoiceItem.setTotalPrice(Double.parseDouble(totalPrice.replace(",", ".")));
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

                // Print the extracted values
                System.out.println("Product Name: " + productName);
                System.out.println("Quantity: " + quantity);
                System.out.println("Unit: " + unit);
                System.out.println("Unit Price: " + unitPrice);
                System.out.println("Total Price: " + totalPrice);
                System.out.println("--------------------------------");
            }

            // Invoice
            String invoiceChave = doc.getElementsByClass("chave").text();


            invoice.setChave(invoiceChave);
            invoice.setHtml(doc.html());
            invoice.setStore(store);
            invoice.setUser(user);
            return invoiceRepository.save(invoice);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
