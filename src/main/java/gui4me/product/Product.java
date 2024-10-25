package gui4me.product;

import java.util.List;

import gui4me.market_product.MarketProduct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "product")
    private List<MarketProduct> marketProduct;
}