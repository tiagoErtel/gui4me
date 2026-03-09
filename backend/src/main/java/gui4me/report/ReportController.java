package gui4me.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gui4me.report.dto.StoreReportDTO;
import gui4me.user.User;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/invoices-by-store")
    public ResponseEntity<List<StoreReportDTO>> invoicesByStore(@AuthenticationPrincipal User user) {

        List<StoreReportDTO> reportData = reportService.getInvoicesByStore(user);

        return ResponseEntity.ok(reportData);
    }
}
