@import gui4me.shopping_list.ShoppingList
@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import java.util.List

@param List<ShoppingList> shoppingLists = null
@param Message message = null
@param String username = ""
@param String email = null
@param CsrfHiddenInput csrfHiddenInput

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
username = username,
content = @`
    <div class="py-6 px-4 sm:px-6 lg:px-8 bg-gray-100">
        <div class="w-full max-w-5xl mx-auto p-6 bg-white rounded-lg shadow-md">
            <div class="mb-6 text-center">
                <h2 class="text-2xl font-bold text-gray-900">Shopping List</h2>
                <p class="text-sm text-gray-600">Your Shopping Lists</p>
            </div>

            <!-- Back to Dashboard -->
            <div class="mb-6">
                <a href="/dashboard"
                   class="inline-block bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600 text-sm">
                    ← Back to Dashboard
                </a>
            </div>

            <!-- Create New Shopping List -->
            <div class="mb-8 border border-gray-200 rounded-lg p-4 bg-gray-50 shadow-sm">
                <h3 class="text-lg font-semibold text-gray-800 mb-3">Create a New Shopping List</h3>
                <form action="/shopping-list/create" method="post" class="flex flex-wrap gap-4 items-center">
                    ${csrfHiddenInput}
                    <input name="name" type="text" required
                           placeholder="Give your shopping list a name"
                           class="flex-grow border-gray-300 text-gray-900 rounded-lg px-4 py-2
                      focus:ring-blue-500 focus:border-blue-500 text-sm shadow-sm border focus:outline-none" />

                    <button type="submit"
                            class="bg-blue-500 text-white px-6 py-2 rounded hover:bg-blue-600 text-sm font-medium whitespace-nowrap">
                        Create
                    </button>
                </form>
            </div>

            <!-- List Table -->
            <div class="overflow-x-auto">
                <table class="w-full text-sm text-left border-collapse">
                    <thead class="bg-gray-50 text-gray-700 uppercase">
                    <tr>
                        <th class="px-4 py-3 w-3/4">Name</th>
                        <th class="px-4 py-3 text-center w-1/4">Actions</th>
                    </tr>
                    </thead>
                    <tbody class="text-gray-700">
                    @if (shoppingLists == null || shoppingLists.isEmpty())
                        <tr>
                            <td colspan="2" class="text-center py-6 text-gray-500">
                                No shopping list created yet.
                            </td>
                        </tr>
                    @else
                        @for (ShoppingList shoppingList : shoppingLists)
                            <tr class="border-t">
                                <td class="px-4 py-2">${shoppingList.getName()}</td>
                                <td class="px-4 py-2">
                                    <div class="flex justify-center items-center gap-2">
                                        <a href="/shopping-list/${shoppingList.getId()}/item"
                                           class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 text-sm shadow-sm transition-all">
                                            Manage
                                        </a>
                                        <form action="/shopping-list/delete" method="POST">
                                            ${csrfHiddenInput}
                                            <input type="hidden" name="shoppingList" value="${shoppingList.getId()}" />
                                            <button type="submit"
                                                    class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 text-sm shadow-sm transition-all">
                                                Remove
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        @endfor
                    @endif
                    </tbody>
                </table>
            </div>
        </div>
    </div>
`)