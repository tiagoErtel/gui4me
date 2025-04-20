package gui4me.shopping_list;

import gui4me.custom_user_details.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, String> {
    List<ShoppingList> findByUser(CustomUserDetails user);
}
