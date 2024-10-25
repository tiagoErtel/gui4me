package gui4me.market;

import java.util.List;

import gui4me.market_product.MarketProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
    @OneToMany(mappedBy = "market")
    private List<MarketProduct> marketProduct;

}
