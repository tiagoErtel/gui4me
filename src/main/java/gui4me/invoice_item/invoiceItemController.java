package gui4me.invoice_item;

import gui4me.invoice.Invoice;
import gui4me.utils.Message;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/invoice/item")
public class invoiceItemController {

    @Autowired
    InvoiceItemService invoiceItemService;

    @GetMapping("/list")
    String list(Authentication authentication, HttpServletRequest request, Model model, Message message, @RequestParam String invoiceId) {

        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("authorities", userDetails.getAuthorities());
        }

        model.addAttribute("message", message);

        // Add CSRF token
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
            model.addAttribute("csrf", csrf);
        }

        List<InvoiceItem> invoiceItemsList = invoiceItemService.findAllByInvoiceId(invoiceId);

        model.addAttribute("invoiceItemsList", invoiceItemsList);
        return "pages/invoice/item/list";
    }
}
