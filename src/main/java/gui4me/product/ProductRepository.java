package gui4me.product;

import java.time.LocalDate;
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

    @Query(value = """
            SELECT
                p.id AS id,
                p.name AS name,
                p.normalized_name AS normalizedName,
                AVG(ii.unit_price) AS avgPrice,
                MIN(ii.unit_price) AS minPrice,
                MAX(ii.unit_price) AS maxPrice,
                COUNT(ii.id) AS timesSold,
                COUNT(DISTINCT s.id) AS storesCount
            FROM invoice_items ii
            JOIN invoices i ON ii.invoice_id = i.id
            JOIN products p ON ii.product_id = p.id
            JOIN stores s ON i.store_id = s.id
            WHERE unaccent(lower(p.name)) LIKE unaccent(lower(CONCAT('%', :productName, '%')))
              AND (CAST(:minDate AS TIMESTAMP) IS NULL OR i.issuance_date >= :minDate)
              AND (CAST(:maxDate AS TIMESTAMP) IS NULL OR i.issuance_date <= :maxDate)
              AND (:distance IS NULL OR :latitude IS NULL OR :longitude IS NULL
                   OR ST_DWithin(s.location, ST_MakePoint(:longitude, :latitude)::geography, :distance))
            GROUP BY p.id, p.name, p.normalized_name
            """, nativeQuery = true)
    List<ProductAnalyse> getProductsAnalyse(
            String productName,
            LocalDate minDate,
            LocalDate maxDate,
            Double distance,
            Double latitude,
            Double longitude);

    @Query("""
            SELECT
                p.name AS name,
                p.normalizedName AS normalizedName,
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
