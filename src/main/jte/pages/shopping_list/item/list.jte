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
    @template.partials.back-button(href = "/shopping-list")

    <div class="list-header">
        <h2 class="title">Shopping List: ${shoppingList.getName()}</h2>
        <p class="subtitle">Manage your items</p>
    </div>

    <div id="show-form-btn" class="btn btn-primary">
        <i class="fas fa-plus"></i>
    </div>

    <div id="new-list-form" class="panel form-card hidden">
        <div id="cancel-form-btn" class="back-button">
            <i class="fas fa-times"></i>
        </div>

        <h4 class="title">Add Item to the List</h4>

        <form action="/shopping-list/${shoppingList.getId()}/item/save" method="POST">
            ${csrfHiddenInput}
            <input type="hidden" name="shoppingList" value="${shoppingList.getId()}"/>

            <select class="tom-select-class" name="product" required>
                <option value="" selected disabled>Select a product</option>
                @for(Product product : productList)
                    <option value="${product.getId()}">${product.getName()}</option>
                @endfor
            </select>

            <input type="number" name="quantity" step="any"
                   placeholder="e.g. 2" required/>

            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>

    @if(shoppingListItems == null || shoppingListItems.isEmpty())
        <div class="no-items">
            <p>No items added yet.</p>
        </div>
    @else
        <div class="card-list">
            @for(ShoppingListItem shoppingListItem : shoppingListItems)
                <div class="card-item">
                    <div class="card-content">
                        <h3 class="card-title">${shoppingListItem.getProduct().getName()}</h3>
                        <p class="card-subtitle">Quantity: ${shoppingListItem.getQuantity()}</p>
                    </div>
                    <details class="dropdown">
                        <summary class="dropdown-toggle">&#x22EE;</summary>
                        <ul class="dropdown-menu">
                            <li>
                                <form action="/shopping-list/${shoppingList.getId()}/item/delete" method="post">
                                    ${csrfHiddenInput}
                                    <input type="hidden" name="shoppingListItem" value="${shoppingListItem.getId()}">
                                    <span onclick="this.closest('form').submit()">Delete</span>
                                </form>
                            </li>
                        </ul>
                    </details>
                </div>
            @endfor
        </div>
    @endif

    <link href="https://cdn.jsdelivr.net/npm/tom-select@2.2.2/dist/css/tom-select.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/tom-select@2.2.2/dist/js/tom-select.complete.min.js"></script>
    <script>
        new TomSelect('.tom-select-class', {
            create: false,
            maxItems: 1,
            placeholder: 'Select a product',
            allowEmptyOption: false
        });

        document.addEventListener('DOMContentLoaded', () => {
            const showBtn = document.getElementById('show-form-btn');
            const cancelBtn = document.getElementById('cancel-form-btn');
            const form = document.getElementById('new-list-form');

            showBtn.addEventListener('click', () => {
                form.classList.remove('hidden');
                showBtn.classList.add('hidden');
            });

            cancelBtn.addEventListener('click', () => {
                form.classList.add('hidden');
                showBtn.classList.remove('hidden');
            });
        });
    </script>
`)
