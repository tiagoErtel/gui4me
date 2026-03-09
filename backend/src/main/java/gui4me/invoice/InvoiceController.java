package gui4me.invoice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import gui4me.user.User;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @AuthenticationPrincipal User currentUser,
            @RequestBody Map<String, String> request) {

        String invoiceUrl = request.get("invoiceUrl");
        invoiceService.save(invoiceUrl, currentUser);

        return ResponseEntity.ok(Map.of("message", "Invoice registered successfully!"));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Invoice>> list(
            @RequestParam(defaultValue = "issuanceDate,desc") String sort,
            @AuthenticationPrincipal User currentUser) {

        String[] sortParts = sort.split(",");
        String sortBy = sortParts[0];
        Sort.Direction direction = Sort.Direction.fromString(sortParts[1]);

        List<Invoice> invoices = invoiceService.findAllByUser(currentUser, Sort.by(direction, sortBy));

        return ResponseEntity.ok(invoices);
    }
}
