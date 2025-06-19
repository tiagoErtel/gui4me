package gui4me.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gui4me.product.dto.ProductAnalyse;
import gui4me.product.dto.ProductAnalyseByStore;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product getOrCreateProduct(String productName) {
        return findByName(productName)
                .orElseGet(() -> save(new Product(productName)));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<ProductAnalyse> getProductAnalyse(String productName) {
        return productRepository.getProductAnalyse(productName);
    }

    public List<ProductAnalyseByStore> getProductAnalyseByStore(String productId) {
        return productRepository.getProductAnalyseByStore(productId);
    }
}
