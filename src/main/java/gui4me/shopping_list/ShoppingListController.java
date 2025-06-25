package gui4me.shopping_list;

import gui4me.user.User;
import gui4me.utils.Message;
import gui4me.utils.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/shopping-list")
public class ShoppingListController {

    @Autowired
    ShoppingListService shoppingListService;

    @GetMapping
    public String list(Model model, Authentication auth) {
        if (auth.getPrincipal() instanceof User user) {
            List<ShoppingList> lists = shoppingListService.findByUser(user);
            model.addAttribute("shoppingLists", lists);
        }
        return "pages/shopping_list/list";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam String name,
            Authentication auth,
            RedirectAttributes redirectAttributes) {
        if (auth.getPrincipal() instanceof User user) {
            shoppingListService.createShoppingList(name, user);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "List created!"));
        }
        return "redirect:/shopping-list";
    }

    @PostMapping("/delete")
    public String delete(
            @ModelAttribute ShoppingList shoppingList,
            RedirectAttributes redirectAttributes) {
        shoppingListService.delete(shoppingList);

        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Shopping list deleted!"));

        return "redirect:/shopping-list";
    }
}
