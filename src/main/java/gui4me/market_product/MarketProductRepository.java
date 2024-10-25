package gui4me.market_product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface MarketProductRepository extends JpaRepository<MarketProduct, Long>{

}
