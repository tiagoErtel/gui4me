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
    @template.partials.back-button(href = "/dashboard")

    <div class="list-header">
        <h2 class="title">Shopping Lists</h2>
        <p class="subtitle">Your saved shopping lists</p>
    </div>

    <div id="show-form-btn" class="btn btn-primary">
        <i class="fas fa-plus"></i>
    </div>

    <div id="new-list-form" class="panel form-card hidden">
        <div id="cancel-form-btn" class="back-button">
            <i class="fas fa-times"></i>
        </div>

        <h4 class="title">Create a New Shopping List</h4>

        <form action="/shopping-list/create" method="post">
            ${csrfHiddenInput}
            <input name="name" type="text" required placeholder="Give your shopping list a name" />
            <button type="submit" class="btn btn-primary">Create</button>
        </form>
    </div>

    @if (shoppingLists == null || shoppingLists.isEmpty())
        <div class="no-items">
            <p>No shopping lists created yet.</p>
        </div>
    @else
        <div class="card-list">
            @for (ShoppingList shoppingList : shoppingLists)

                <div class="card-item">
                    <div class="card-content" onclick="location.assign('/shopping-list/${shoppingList.getId()}/item')">
                        <h3 class="card-title">${shoppingList.getName()}</h3>
                    </div>

                    <details class="dropdown">
                        <summary class="dropdown-toggle">&#x22EE;</summary>
                        <ul class="dropdown-menu">
                            <li>Rename</li>
                            <li>
                                <form id="delete-${shoppingList.getId()}" action="/shopping-list/delete" method="post">
                                    ${csrfHiddenInput}
                                    <input type="hidden" name="shoppingList" value="${shoppingList.getId()}">
                                    <span onclick="document.getElementById('delete-${shoppingList.getId()}').submit()">Delete</span>
                                </form>
                            </li>
                        </ul>
                    </details>
                </div>

            @endfor
        </div>
    @endif

    <script>
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
