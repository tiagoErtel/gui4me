package gui4me.invoice_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import gui4me.user.User;
import java.util.List;

@RestController
@RequestMapping("/api/invoice/item")
public class InvoiceItemController {

    @Autowired
    private InvoiceItemService invoiceItemService;

    @GetMapping("/list")
    public ResponseEntity<List<InvoiceItem>> list(
            @RequestParam String invoiceId,
            @AuthenticationPrincipal User currentUser) {

        List<InvoiceItem> invoiceItemsList = invoiceItemService.findAllByInvoiceId(invoiceId);

        return ResponseEntity.ok(invoiceItemsList);
    }
}
