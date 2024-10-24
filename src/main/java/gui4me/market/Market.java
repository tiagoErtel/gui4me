package gui4me.market;

import java.util.List;

import gui4me.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "markets")
public class Market {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String cnpj;
	
    @ManyToMany
    @JoinTable(
    		name = "market_product",
    		joinColumns = @JoinColumn(name = "market_id"),
    		inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
