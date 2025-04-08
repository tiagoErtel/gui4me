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
    String list(Model model, @RequestParam String invoiceId) {
        List<InvoiceItem> invoiceItemsList = invoiceItemService.findAllByInvoiceId(invoiceId);

        model.addAttribute("invoiceItemsList", invoiceItemsList);
        return "pages/invoice/item/list";
    }
}
