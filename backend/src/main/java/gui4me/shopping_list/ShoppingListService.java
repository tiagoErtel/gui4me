package gui4me.shopping_list;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gui4me.user.User;

@Service
public class ShoppingListService {

    @Autowired
    ShoppingListRepository shoppingListRepository;

    public List<ShoppingList> findByUser(User user) {
        return shoppingListRepository.findByUser(user);
    }

    public void createShoppingList(String name, User user) {
        ShoppingList list = new ShoppingList();
        list.setName(name);
        list.setUser(user);
        shoppingListRepository.save(list);
    }

    public Optional<ShoppingList> findById(String id) {
        return shoppingListRepository.findById(id);
    }

    public void delete(ShoppingList shoppingList) {
        shoppingListRepository.delete(shoppingList);
    }
}
