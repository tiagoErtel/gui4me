package gui4me.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarketService {

    @Autowired
    MarketRepository marketRepository;

    public List<Market> getAllMarkets() {
        return marketRepository.findAll();
    }

    public Market getMarketById(Long id) {
        return marketRepository.findById(id).orElse(null);
    }

    public Market saveMarket(Market market) {
        return marketRepository.save(market);
    }

    public void deleteMarket(Long id) {
        marketRepository.deleteById(id);
    }
}
