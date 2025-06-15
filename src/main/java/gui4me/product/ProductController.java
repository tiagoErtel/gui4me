package gui4me.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gui4me.product.dto.ProductSearchResult;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/search")
    public String searchProduct(Model model, String productName) {
        if (productName != null && !productName.isBlank()) {
            List<ProductSearchResult> pr = productService.findLatestProductByNameForAllStores(productName);
            model.addAttribute("productList", pr);
        }

        return "pages/product/search";
    }
}
