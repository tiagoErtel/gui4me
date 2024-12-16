package gui4me.invoice;

import gui4me.custom_user_details.CustomUserDetails;
import gui4me.exceptions.InvoiceAlreadyProcessedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/register")
    public String register(Authentication authentication, @RequestParam String invoiceUrl, RedirectAttributes redirectAttributes){
        try {

            if (authentication == null || !authentication.isAuthenticated()) {
                return "redirect:/login"; // Redirect to log in if not authenticated
            }

            if (authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
                CustomUserDetails user = customUserDetails;
                Invoice invoice = invoiceService.save(invoiceUrl, user);
            }

            redirectAttributes.addAttribute("success", true);
            redirectAttributes.addAttribute("successMessage", "Invoice registered!");
            return "redirect:/dashboard";
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", true);
            redirectAttributes.addAttribute("errorMessage", e.getMessage());
            return "redirect:/dashboard";
        }
    }
}
