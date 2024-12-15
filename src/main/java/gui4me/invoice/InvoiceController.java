package gui4me.invoice;

import gui4me.custom_user_details.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

        if (authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            CustomUserDetails user = customUserDetails;
            Invoice invoice = invoiceService.save(invoiceUrl, user);
            System.out.println(invoice.getId());
        }

        return "redirect:/dashboard";
    }
}
