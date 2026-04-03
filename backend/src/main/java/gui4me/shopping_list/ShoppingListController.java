package gui4me.shopping_list;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import gui4me.user.User;

@RestController
@RequestMapping("/api/shopping-list")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping("/list")
    public ResponseEntity<List<ShoppingList>> list(@AuthenticationPrincipal User currentUser) {
        List<ShoppingList> lists = shoppingListService.findByUser(currentUser);
        return ResponseEntity.ok(lists);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody Map<String, String> request,
            @AuthenticationPrincipal User currentUser) {

        String name = request.get("name");
        shoppingListService.createShoppingList(name, currentUser);

        return ResponseEntity.ok(Map.of("message", "List created successfully!"));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Map<String, String> request) {
        String listId = request.get("shoppingList");

        ShoppingList list = shoppingListService.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found"));

        shoppingListService.delete(list);

        return ResponseEntity.ok(Map.of("message", "Shopping list deleted!"));
    }
}
