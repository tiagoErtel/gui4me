package gui4me.invoice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gui4me.custom_user_details.CustomUserDetails;
import gui4me.utils.Message;
import gui4me.utils.MessageType;

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
            @ModelAttribute("currentUser") CustomUserDetails currentUser,
            @RequestParam String invoiceUrl,
            RedirectAttributes redirectAttributes) {

        invoiceService.save(invoiceUrl, currentUser);

        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Invoice registered!"));

        return "redirect:/dashboard";
    }

    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "issuanceDate,desc") String sort, Model model,
            @ModelAttribute("currentUser") CustomUserDetails currentUser) {

        String[] sortParts = sort.split(",");
        String sortBy = sortParts[0];
        Sort.Direction direction = Sort.Direction.fromString(sortParts[1]);

        List<Invoice> invoices = invoiceService.findAllByUser(currentUser, Sort.by(direction, sortBy));
        model.addAttribute("invoiceList", invoices);
        model.addAttribute("sort", sort);

        return "pages/invoice/list";
    }
}
