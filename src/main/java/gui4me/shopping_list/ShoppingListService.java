package gui4me.shopping_list;

import gui4me.custom_user_details.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {

    @Autowired
    ShoppingListRepository shoppingListRepository;

    public List<ShoppingList> findByUser(CustomUserDetails user) {
        return shoppingListRepository.findByUser(user);
    }

    public void createShoppingList(String name, CustomUserDetails user) {
        ShoppingList list = new ShoppingList();
        list.setName(name);
        list.setUser(user);
        shoppingListRepository.save(list);
    }

    public ShoppingList findById(String id) {
        return shoppingListRepository.findById(id).orElseThrow();
    }

    public void delete(ShoppingList shoppingList) {
        shoppingListRepository.delete(shoppingList);
    }
}
