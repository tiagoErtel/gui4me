package gui4me.shopping_list_item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, String> {

    List<ShoppingListItem> findAllByShoppingListId(String shoppingListId);

}
