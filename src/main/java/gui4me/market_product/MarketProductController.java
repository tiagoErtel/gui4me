package gui4me.market_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market-products")
public class MarketProductController {

    @Autowired
    private MarketProductService marketProductService;

    @GetMapping
    public List<MarketProduct> getAllMarketProducts() {
        return marketProductService.getAllMarketProducts();
    }

    @GetMapping("/{id}")
    public MarketProduct getMarketProductById(@PathVariable Long id) {
        return marketProductService.getMarketProductById(id);
    }

    @PostMapping
    public MarketProduct saveMarketProduct(@RequestBody MarketProduct marketProduct) {
        return marketProductService.saveMarketProduct(marketProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteMarketProduct(@PathVariable Long id) {
        marketProductService.deleteMarketProduct(id);
    }
}
