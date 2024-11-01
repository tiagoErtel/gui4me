package gui4me.market;

import java.util.List;

import gui4me.market_product.MarketProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Market(@NotEmpty String name, @NotEmpty String cnpj) {
		super();
		this.name = name;
		this.cnpj = cnpj;
	}

}
