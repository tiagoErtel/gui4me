package gui4me.shopping_list_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListItemService {

    @Autowired
    ShoppingListItemRepository shoppingListItemRepository;

    public ShoppingListItem save(ShoppingListItem shoppingListItem) {
        return shoppingListItemRepository.save(shoppingListItem);
    }

    public void delete(ShoppingListItem shoppingListItem) {
        shoppingListItemRepository.delete(shoppingListItem);
    }
}
