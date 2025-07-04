package gui4me.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import gui4me.user.User;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/dashboard")
    public String dashboard(Model model,
            @ModelAttribute("currentUser") User user) {
        model.addAttribute("invoicesByStore", reportService.getInvoicesByStore(user));
        return "pages/report/dashboard";
    }

}
