@import gui4me.product.Product
@import gui4me.security.CsrfHiddenInput
@import gui4me.shopping_list.ShoppingList
@import gui4me.shopping_list_item.ShoppingListItem
@import gui4me.utils.Message
@import java.util.List

@param ShoppingList shoppingList
@param List<ShoppingListItem> shoppingListItems
@param CsrfHiddenInput csrfHiddenInput
@param Message message = null
@param String username = null
@param List<Product> productList

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
username = username,
content = @`
    <h2>
        Shopping List: ${shoppingList.getName()}
    </h2>

    <a href="/shopping-list" class="button back-button btn-purple">
        ←
    </a>

    <br>

    <h3>Add Item to the List</h3>
    <form action="/shopping-list/${shoppingList.getId()}/item/save" method="POST">
        ${csrfHiddenInput}
        <input type="hidden" name="shoppingList" value="${shoppingList.getId()}"/>

        <select id="product" name="product" required>
            <option value="" selected disabled>Select a product to add to the list</option>
            @for(Product product : productList)
                <option value="${product.getId()}">${product.getName()}</option>
            @endfor
        </select>

        <input type="number" id="quantity" name="quantity" step="any"
               placeholder="e.g. 2" required/>

        <button type="submit" class="button btn-purple">
            Add Item
        </button>
    </form>

    <script src="https://cdn.jsdelivr.net/npm/tom-select@2.2.2/dist/js/tom-select.complete.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/tom-select@2.2.2/dist/css/tom-select.min.css" rel="stylesheet"/>

    <script>
        new TomSelect('.tom-select-class', {
            create: false,
            maxItems: 1,
            placeholder: 'Select a product to add to the list',
            allowEmptyOption: false
        });
    </script>

    <table>
        <thead>
        <tr>
            <th>Product</th>
            <th>Quantity</th>
            <th class="actions-column"></th>
        </tr>
        </thead>
        <tbody>
        @if(shoppingListItems == null || shoppingListItems.isEmpty())
            <tr>
                <td colspan="3">
                    No items added yet.
                </td>
            </tr>
        @else
            @for(ShoppingListItem shoppingListItem : shoppingListItems)
                <tr class="table-row">
                    <td>${shoppingListItem.getProduct().getName()}</td>
                    <td>${shoppingListItem.getQuantity()}</td>
                    <td>
                        <details class="dropdown">
                            <summary class="dropdown-toggle">
                                &#x22EE;
                            </summary>
                            <ul class="dropdown-menu">
                                <li class="clickable table-row">
                                    <form id="delete" action="/shopping-list/${shoppingList.getId()}/item/delete" method="post">
                                        ${csrfHiddenInput}
                                        <input type="hidden" name="shoppingListItem" value="${shoppingListItem.getId()}">
                                        <span onclick="this.closest('form').submit()">Delete</span>
                                    </form>
                                </li>
                            </ul>
                        </details>
                    </td>
                </tr>
            @endfor
        @endif
        </tbody>
    </table>
`)