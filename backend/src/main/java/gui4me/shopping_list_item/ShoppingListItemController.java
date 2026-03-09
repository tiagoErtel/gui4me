package gui4me.shopping_list_item;

import gui4me.product.Product;
import gui4me.product.ProductService;
import gui4me.shopping_list.ShoppingList;
import gui4me.shopping_list.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shopping-list/{shoppingListId}/item")
public class ShoppingListItemController {

    @Autowired
    private ShoppingListItemRepository shoppingListItemRepository;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingListItemService shoppingListItemService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> list(@PathVariable String shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("Shopping list not found"));

        List<ShoppingListItem> shoppingListItems = shoppingListItemRepository.findAllByShoppingListId(shoppingListId);
        List<Product> productList = productService.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("shoppingList", shoppingList);
        response.put("shoppingListItems", shoppingListItems);
        response.put("productList", productList);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> addItem(
            @PathVariable String shoppingListId,
            @RequestBody ShoppingListItem shoppingListItem) {

        ShoppingList list = shoppingListRepository.findById(shoppingListId).orElseThrow();
        shoppingListItem.setShoppingList(list);

        String message = (shoppingListItem.getId() == null) ? "Item added to shopping list." : "Item updated.";

        shoppingListItemService.save(shoppingListItem);

        return ResponseEntity.ok(Map.of("message", message));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteItem(@RequestBody ShoppingListItem shoppingListItem) {
        shoppingListItemService.delete(shoppingListItem);
        return ResponseEntity.ok(Map.of("message", "Item removed from shopping list."));
    }
}
