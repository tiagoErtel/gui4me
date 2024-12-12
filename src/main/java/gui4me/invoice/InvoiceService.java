package gui4me.invoice;

import gui4me.store.Store;
import gui4me.store.StoreRepository;
import gui4me.store.StoreService;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import java.io.IOException;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    StoreService storeService;

    public Invoice save(String invoiceUrl){
        try{
            Document doc = Jsoup.connect(invoiceUrl).get();

            String invoiceChave = doc.getElementsByClass("chave").text();

            String storeName = doc.getElementsByClass("txtTopo").text();

            String storeDocument = doc.getElementsContainingOwnText("CNPJ:").text().replace("CNPJ:", "").strip();

            Store store = new Store();
            store.setDocument(storeDocument);
            store.setName(storeName);
            store = storeService.checkStoreDocAndSave(store);

            Invoice invoice = new Invoice();
            invoice.setChave(invoiceChave);
            invoice.setHtml(doc.html());
            invoice.setStore(store);
            return invoiceRepository.save(invoice);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
