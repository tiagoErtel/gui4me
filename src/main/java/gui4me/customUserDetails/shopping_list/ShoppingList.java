package gui4me.customUserDetails.shopping_list;

import gui4me.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ShoppingList {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "prduct_id")
    private Product product;
    
    private int quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public ShoppingList() {
		super();
	}

	public ShoppingList(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
    
	
    
}
