package gui4me.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gui4me.invoice.Invoice;
import gui4me.report.dto.InvoicesByStore;

@Repository
public interface ReportRepository extends JpaRepository<Invoice, String> {

    @Query("""
                SELECT s.name as storeName, COUNT(*) as invoiceCount, SUM(i.totalPrice) as totalPrice
                FROM Invoice i
                JOIN Store s on i.store.id = s.id
                GROUP BY s.name
            """)
    List<InvoicesByStore> getInvoicesByStore();

}
