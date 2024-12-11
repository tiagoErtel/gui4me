package gui4me.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/register")
    public String register(Authentication authentication, @RequestParam String invoiceUrl){

        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Redirect to log in if not authenticated
        }

        Invoice invoice = invoiceService.save(invoiceUrl);
        System.out.println(invoice.getId());
        return "redirect:/dashboard";
    }
}
