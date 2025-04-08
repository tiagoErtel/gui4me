package gui4me.product;

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

import java.util.List;
import java.util.Optional;

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
