package gui4me.invoice;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import java.io.IOException;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    public Invoice save(String invoiceUrl){
        Invoice invoice = new Invoice();
        Document doc = getHtmlInvoice(invoiceUrl);
        Element chave = doc.selectFirst(".chave");
        invoice.setChave(chave.text());
        invoice.setHtml(doc.html());
        return invoiceRepository.save(invoice);
    }

    public Document getHtmlInvoice(String invoiceUrl){
        try {
            return Jsoup.connect(invoiceUrl).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
