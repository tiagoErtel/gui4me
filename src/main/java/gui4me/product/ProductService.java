package gui4me.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	
    @Autowired
    ProductRepository productRepository;

    public Optional<Product> findByName(String name){
        return productRepository.findByName(name);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public List<ProductSearchResultDTO> findAllByName(String name){
        try {
            List<ProductSearchResultDTO> pr = productRepository.findAllByName(name);
            return pr;

        }
        catch(Exception ex) {
            return null;
        }
    }
}
