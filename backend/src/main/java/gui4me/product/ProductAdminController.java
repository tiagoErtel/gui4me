package gui4me.product;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/normalize-all-products-name")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> normalizeAllProducsName() {
        productService.normalizeAllProductsName();
        return ResponseEntity.ok("Successfully normalized products name");
    }
}
