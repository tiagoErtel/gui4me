package gui4me.product;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gui4me.product.dto.ProductAnalyse;
import gui4me.product.dto.ProductAnalyseByStore;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/search")
    public String searchProduct(Model model, HttpServletRequest request,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) LocalDate minDate,
            @RequestParam(required = false) LocalDate maxDate,
            @RequestParam(required = false) Double distance,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude) {
        if (productName != null && !productName.isBlank()) {
            List<ProductAnalyse> products = productService.getProductsAnalyse(productName, minDate, maxDate, distance,
                    latitude, longitude);
            model.addAttribute("products", products);
        }

        if ("true".equals(request.getHeader("HX-Request"))) {
            return "pages/product/_analyse_card";
        }

        return "pages/product/search";
    }

    @GetMapping("/analyse")
    public String analyseByStore(Model model, @RequestParam String productId) {
        List<ProductAnalyseByStore> products = productService.getProductAnalyseByStores(productId);

        model.addAttribute("products", products);

        return "pages/product/analyse";
    }
}
