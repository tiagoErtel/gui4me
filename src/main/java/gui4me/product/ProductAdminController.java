package gui4me.product;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/recalculate-normalized-names")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> recalc() {
        productService.normalizeAllProductNames();
        return ResponseEntity.ok("Normalized names recalculated");
    }
}
