package gui4me.market_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarketProductService {

    @Autowired
    MarketProductRepository marketProductRepository;

    public List<MarketProduct> getAllMarketProducts() {
        return marketProductRepository.findAll();
    }

    public MarketProduct getMarketProductById(Long id) {
        return marketProductRepository.findById(id).orElse(null);
    }

    public MarketProduct saveMarketProduct(MarketProduct marketProduct) {
        return marketProductRepository.save(marketProduct);
    }

    public void deleteMarketProduct(Long id) {
        marketProductRepository.deleteById(id);
    }
}
