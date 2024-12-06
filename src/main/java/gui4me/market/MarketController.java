package gui4me.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/markets")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @GetMapping
    public List<Market> getAllMarkets() {
        List<Market> market = marketService.getAllMarkets();
        return market;
    }

    @GetMapping("/{id}")
    public Market getMarketById(@PathVariable Long id) {
        return marketService.getMarketById(id);
    }

    @PostMapping
    public Market saveMarket(@RequestBody Market market) {
        return marketService.saveMarket(market);
    }

    @DeleteMapping("/{id}")
    public void deleteMarket(@PathVariable Long id) {
        marketService.deleteMarket(id);
    }
}
