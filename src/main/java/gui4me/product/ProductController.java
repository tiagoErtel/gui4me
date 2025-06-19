package gui4me.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gui4me.product.dto.ProductAnalyse;
import gui4me.product.dto.ProductAnalyseByStore;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/search")
    public String searchProduct(Model model, @RequestParam(required = false) String productName) {
        if (productName != null && !productName.isBlank()) {
            List<ProductAnalyse> products = productService.getProductAnalyse(productName);
            model.addAttribute("products", products);
        }

        return "pages/product/search";
    }

    @GetMapping("/analyse")
    public String analyseByStore(Model model, @RequestParam String productId) {
        List<ProductAnalyseByStore> products = productService.getProductAnalyseByStore(productId);

        model.addAttribute("products", products);

        return "pages/product/analyse";
    }
}
