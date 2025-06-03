package gui4me.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    private String parseToJson(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            return jsonData;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    public String getInvoicesByStore() {
        return parseToJson(reportRepository.getInvoicesByStore());
    }

}
