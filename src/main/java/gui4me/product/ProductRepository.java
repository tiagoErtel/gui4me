package gui4me.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
    Optional<Product> findByName(String name);

    @Query("""
    SELECT new gui4me.product.ProductSearchResultDTO(
        p.name, ii.unitPrice, s.name, i.issuanceDate
    )
    FROM Product p
    JOIN InvoiceItem ii ON p.id = ii.product.id
    JOIN Invoice i ON ii.invoice.id = i.id
    JOIN Store s ON i.store.id = s.id
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
""")
    List<ProductSearchResultDTO> findAllByName(@Param("name") String name);

}
