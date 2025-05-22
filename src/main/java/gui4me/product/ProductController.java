package gui4me.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/search")
    public String searchProduct(Model model, String productName) {
        if (productName != null && !productName.isBlank()) {
            List<ProductSearchResultDTO> pr = productService.findLatestProductByNameForAllStores(productName);
            model.addAttribute("productList", pr);
        }

        return "pages/product/search";
    }
}
