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
    <div>
        <h2>Shopping List</h2>
        <p>Your Shopping Lists</p>
    </div>

    <a href="/dashboard"
       class="button btn-purple back-button">
        ←
    </a>

    <br>

    <h3>Create a New Shopping List</h3>
    <form action="/shopping-list/create" method="post">
        ${csrfHiddenInput}
        <input name="name" type="text" required
               placeholder="Give your shopping list a name"/>

        <button type="submit" class="button btn-purple">
            Create
        </button>
    </form>


    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th class="actions-column"></th>
        </tr>
        </thead>
        <tbody>
        @if (shoppingLists == null || shoppingLists.isEmpty())
            <tr>
                <td colspan="2">
                    No shopping list created yet.
                </td>
            </tr>
        @else
            @for (ShoppingList shoppingList : shoppingLists)

                <tr class="table-row clickable">
                    <td onclick="location.assign('/shopping-list/${shoppingList.getId()}/item')">
                        ${shoppingList.getName()}
                    </td>
                    <td>
                        <details class="dropdown">
                            <summary class="dropdown-toggle">
                                &#x22EE;
                            </summary>
                            <ul class="dropdown-menu">
                                <li>Rename</li>
                                <li class="clickable table-row">
                                    <form id="delete" action="/shopping-list/delete" method="post">
                                        ${csrfHiddenInput}
                                        <input type="hidden" name="shoppingList" value="${shoppingList.getId()}">
                                        <span onclick="document.getElementById('delete').submit()">Delete</span>
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