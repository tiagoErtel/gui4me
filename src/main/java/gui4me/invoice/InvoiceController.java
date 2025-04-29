package gui4me.invoice;

import gui4me.custom_user_details.CustomUserDetails;
import gui4me.exceptions.InvoiceAlreadyProcessedException;
import gui4me.utils.Message;
import gui4me.utils.MessageType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/register")
    public String register() {
        return "pages/invoice/register";
    }

    @PostMapping("/register")
    public String register(
            Authentication authentication,
            @RequestParam String invoiceUrl,
            RedirectAttributes redirectAttributes
    ) {
        try {

            if (authentication == null || !authentication.isAuthenticated()) {
                return "redirect:/login"; // Redirect to log in if not authenticated
            }

            if (authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
                Invoice invoice = invoiceService.save(invoiceUrl, customUserDetails);
            }

            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Invoice registered!"));

            return "redirect:/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.ERROR, e.getMessage()));

            return "redirect:/dashboard";
        }
    }

    @GetMapping("/list")
    public String list(
            Model model,
            Authentication auth,
            @ModelAttribute("currentUser") CustomUserDetails currentUser
    ) {
        List<Invoice> invoiceList = invoiceService.findAllByUser(currentUser);

        model.addAttribute("invoiceList", invoiceList);

        return "pages/invoice/list";
    }
}
