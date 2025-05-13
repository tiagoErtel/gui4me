package gui4me.invoice_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceItemService {

    @Autowired
    InvoiceItemRepository invoiceItemRepository;

    public List<InvoiceItem> findAllByInvoiceId(String invoiceId) {
        return invoiceItemRepository.findAllByInvoiceId(invoiceId);
    }
}
