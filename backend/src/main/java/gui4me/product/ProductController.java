package gui4me.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gui4me.product.dto.ProductAnalyse;
import gui4me.product.dto.ProductAnalyseByStore;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductAnalyse>> searchProduct(@RequestParam(required = false) String productName) {
        if (productName == null || productName.isBlank()) {
            return ResponseEntity.ok(List.of());
        }

        List<ProductAnalyse> products = productService.getProductsAnalyse(productName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/analyse/{productId}")
    public ResponseEntity<List<ProductAnalyseByStore>> analyseByStore(@PathVariable String productId) {
        List<ProductAnalyseByStore> products = productService.getProductAnalyseByStores(productId);

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }
}
