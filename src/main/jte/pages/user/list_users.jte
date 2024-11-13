@import gui4me.user.User
@import java.util.List

@param List<User> users

@template.layout.main(content = @`
    <div>
        <h1>User List</h1>
        <a href="/users/create">Create New User</a>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Language</th>
                    <th>Currency</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                @for(User user : users)
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getUsername()}</td>
                    <td>${user.getUserLanguage()}</td>
                    <td>${user.getUserCurrency()}</td>
                    <td>
                        <a href="/users/edit/${user.getId()}">Edit</a>
                        <a href="/users/delete/${user.getId()}">Delete</a>
                    </td>
                </tr>
                @endfor
            </tbody>
        </table>
    </div>
`)