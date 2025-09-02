package gui4me.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gui4me.product.dto.ProductAnalyse;
import gui4me.product.dto.ProductAnalyseByStore;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByName(String name);

    @Query("""
            SELECT
                p.id AS id,
                p.name AS name,
                p.normalizedName AS productNormalizedName,
                AVG(ii.unitPrice) AS avgPrice,
                MIN(ii.unitPrice) AS minPrice,
                MAX(ii.unitPrice) AS maxPrice,
                COUNT(ii.id) AS timesSold,
                COUNT(DISTINCT s.id) AS storesCount
            FROM InvoiceItem ii
            JOIN ii.invoice i
            JOIN ii.product p
            JOIN i.store s
            WHERE UNACCENT(LOWER(p.name)) LIKE UNACCENT(LOWER(CONCAT('%', :productName, '%')))
            GROUP BY p.id, p.name, p.normalizedName
            """)
    List<ProductAnalyse> getProductsAnalyse(@Param("productName") String productName);

    @Query("""
            SELECT
                p.name AS productName,
                p.normalizedName AS productNormalizedName,
                s.name AS storeName,
                MIN(ii.unitPrice) AS minPrice,
                MAX(ii.unitPrice) AS maxPrice,
                AVG(ii.unitPrice) AS avgPrice,
                COUNT(ii.id) AS timesSold
            FROM InvoiceItem ii
            JOIN ii.invoice i
            JOIN i.store s
            JOIN ii.product p
            WHERE p.id = :productId
            GROUP BY p.name, p.normalizedName, s.name
            ORDER BY avgPrice ASC
            """)
    List<ProductAnalyseByStore> getProductAnalyseByStores(@Param("productId") String productId);
}
