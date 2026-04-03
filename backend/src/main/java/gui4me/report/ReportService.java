package gui4me.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gui4me.report.dto.StoreReportDTO;
import gui4me.user.User;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    public List<StoreReportDTO> getInvoicesByStore(User user) {
        return reportRepository.getInvoicesByStore(user);
    }
}
