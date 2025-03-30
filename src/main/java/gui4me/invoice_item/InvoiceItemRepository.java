package gui4me.invoice_item;

import gui4me.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, String> {

    List<InvoiceItem> findAllByInvoiceId(String invoiceId);
}
