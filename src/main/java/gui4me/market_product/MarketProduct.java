package gui4me.market_product;

import gui4me.market.Market;
import gui4me.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "market_products")
public class MarketProduct {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "market_id")
	private Market market;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(nullable = false)
	@NotNull
	private double price;
}
