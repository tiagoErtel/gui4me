package gui4me.shopping_list_item;

import gui4me.product.Product;
import gui4me.product.ProductService;
import gui4me.shopping_list.ShoppingList;
import gui4me.shopping_list.ShoppingListRepository;
import gui4me.utils.Message;
import gui4me.utils.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/shopping-list/{shoppingListId}/item")
public class ShoppingListItemController {

    @Autowired
    ShoppingListItemRepository shoppingListItemRepository;

    @Autowired
    ShoppingListRepository shoppingListRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingListItemService shoppingListItemService;

    @GetMapping
    public String list(
            Model model,
            @PathVariable String shoppingListId
    ) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow();
        List<ShoppingListItem> shoppingListItems = shoppingListItemRepository.findAllByShoppingListId(shoppingListId);
        List<Product> productList = productService.findAll();

        model.addAttribute("shoppingList", shoppingList);
        model.addAttribute("shoppingListItems", shoppingListItems);
        model.addAttribute("productList", productList);

        return "pages/shopping_list/item/list";
    }

    @PostMapping("/save")
    public String addItem(
            @PathVariable String shoppingListId,
            @ModelAttribute ShoppingListItem shoppingListItem,
            RedirectAttributes redirectAttributes
    ) {
        String message = (shoppingListItem.getId() == null) ?
                "Item added to shopping list." : "Item updated.";

        shoppingListItemService.save(shoppingListItem);

        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, message));

        return "redirect:/shopping-list/" + shoppingListId + "/item";
    }

    @PostMapping("/delete")
    public String deleteItem(
            @PathVariable String shoppingListId,
            @ModelAttribute ShoppingListItem shoppingListItem,
            RedirectAttributes redirectAttributes
    ) {
        shoppingListItemService.delete(shoppingListItem);

        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Item removed from shopping list."));

        return "redirect:/shopping-list/" + shoppingListId + "/item";
    }
}
