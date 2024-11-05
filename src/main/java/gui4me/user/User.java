package gui4me.user;

import java.util.List;

import gui4me.user.shopping_list.ShoppingList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private UserLanguage userLanguage;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private UserCurrency userCurrency;
	
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
	

	public UserLanguage getUserLanguage() {
		return userLanguage;
	}

	public void setUserLanguage(UserLanguage userLanguage) {
		this.userLanguage = userLanguage;
	}

	public UserCurrency getUserCurrency() {
		return userCurrency;
	}

	public void setUserCurrency(UserCurrency userCurrency) {
		this.userCurrency = userCurrency;
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
    
}