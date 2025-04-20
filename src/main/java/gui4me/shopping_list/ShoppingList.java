package gui4me.shopping_list;

import gui4me.custom_user_details.CustomUserDetails;
import jakarta.persistence.*;

@Entity
@Table(name = "shopping_lists")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private CustomUserDetails user;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomUserDetails getUser() {
        return user;
    }

    public void setUser(CustomUserDetails user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
