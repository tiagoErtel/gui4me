package gui4me.user;

import java.util.List;

import gui4me.user.shopping_list.ShoppingList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // This will create a `user_id` foreign key in the ShoppingList table
    private List<ShoppingList> shoppingList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ShoppingList> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(List<ShoppingList> shoppingList) {
		this.shoppingList = shoppingList;
	}

	public User() {
		super();
	}
	
	public User(@NotEmpty String username, @NotEmpty String password, List<ShoppingList> shoppingList) {
		super();
		this.username = username;
		this.password = password;
		this.shoppingList = shoppingList;
	}

	public User(Long id, @NotEmpty String username, @NotEmpty String password, List<ShoppingList> shoppingList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.shoppingList = shoppingList;
	}
    
}