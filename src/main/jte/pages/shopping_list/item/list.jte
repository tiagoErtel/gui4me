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
<div class="min-h-screen bg-gray-100">
    <main class="max-w-5xl mx-auto py-6 sm:px-6 lg:px-8">
        <div class="bg-white shadow rounded-lg p-6">
            <h2 class="text-2xl font-semibold text-gray-800 text-center mb-4">
                Shopping List: ${shoppingList.getName()}
            </h2>

            <div class="mb-4">
                <a href="/shopping-list"
                   class="inline-block bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600 text-sm">
                    ← Back to Shopping Lists
                </a>
            </div>

            <!-- Add New Item -->
            <div class="mb-6 border border-gray-200 rounded-lg p-4 bg-gray-50 shadow-sm">
                <h3 class="text-lg font-semibold text-gray-800 mb-3">Add Item to the List</h3>
                <form action="/shopping-list/${shoppingList.getId()}/item/save" method="POST" class="flex flex-wrap items-end gap-4 w-full">
                    ${csrfHiddenInput}
                    <input type="hidden" name="shoppingList" value="${shoppingList.getId()}" />
                    <!-- Product Selector -->
                    <div class="flex-1 min-w-[200px]">
                        <label for="product" class="block text-sm font-medium text-gray-700">Product</label>
                        <select id="product" name="product" class="tom-select-class w-full" required>
                            <option value="" selected disabled>Select a product to add to the list</option>
                            @for(Product product : productList)
                                <option value="${product.getId()}">${product.getName()}</option>
                            @endfor
                        </select>
                    </div>

                    <!-- Quantity Input -->
                    <div class="w-32">
                        <label for="quantity" class="block text-sm font-medium text-gray-700 mb-1">Quantity</label>
                        <input type="number" id="quantity" name="quantity" step="any"
                               placeholder="e.g. 2"
                               class="border border-gray-400 bg-white rounded-lg px-3 py-2 text-sm text-gray-800
                          focus:ring-blue-500 focus:border-blue-500 w-full shadow-sm transition-all"
                               required />
                    </div>

                    <!-- Submit Button -->
                    <div>
                        <button type="submit"
                                class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 text-sm">
                            Add Item
                        </button>
                    </div>
                </form>
            </div>

            <!-- Tom Select Scripts -->
            <script src="https://cdn.jsdelivr.net/npm/tom-select@2.2.2/dist/js/tom-select.complete.min.js"></script>
            <link href="https://cdn.jsdelivr.net/npm/tom-select@2.2.2/dist/css/tom-select.min.css" rel="stylesheet" />

            <script>
                new TomSelect('.tom-select-class', {
                    create: false,
                    maxItems: 1,
                    placeholder: 'Select a product to add to the list',
                    allowEmptyOption: false
                });
            </script>

            <!-- Item Table -->
            <div class="overflow-x-auto">
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                    <tr>
                        <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Product</th>
                        <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Quantity</th>
                        <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Actions</th>
                    </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                    @for(ShoppingListItem shoppingListItem : shoppingListItems)
                    <tr>
                        <td class="px-4 py-2 text-sm text-gray-800">${shoppingListItem.getProduct().getName()}</td>
                        <td class="px-4 py-2 text-sm text-gray-800">${shoppingListItem.getQuantity()}</td>
                        <td class="px-4 py-2 text-sm text-gray-800">
                            <form action="/shopping-list/${shoppingList.getId()}/item/delete" method="POST" class="inline">
                                ${csrfHiddenInput}
                                <input type="hidden" name="shoppingListItem" value="${shoppingListItem.getId()}" />
                                <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 text-sm">
                                    Remove
                                </button>
                            </form>
                        </td>
                    </tr>
                    @endfor
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</div>
`)