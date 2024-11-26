@import gui4me.user.User
@import java.util.List

@param List<User> users

@template.layout.main(content = @`
    <div class="container mx-auto p-4">
        <div class="flex justify-between items-center mb-4">
            <h1 class="text-2xl font-semibold text-white">User List</h1>
            <a href="/users/create" class="bg-gray-700 text-white px-4 py-2 rounded hover:bg-gray-600">Create User</a>
        </div>
        <table class="min-w-full table-auto text-gray-300">
            <thead class="bg-gray-800">
            <tr>
                <th class="px-4 py-2">ID</th>
                <th class="px-4 py-2">Username</th>
                <th class="px-4 py-2">Language</th>
                <th class="px-4 py-2">Currency</th>
                <th class="px-4 py-2">Actions</th>
            </tr>
            </thead>
            <tbody class="bg-gray-900">
            @for(User user : users)
                <tr class="hover:bg-gray-800">
                    <td class="px-4 py-2">${user.getId()}</td>
                    <td class="px-4 py-2">${user.getUsername()}</td>
                    <td class="px-4 py-2">${user.getLanguage()}</td>
                    <td class="px-4 py-2">${user.getCurrency()}</td>
                    <td class="px-4 py-2">
                        <a href="/users/edit/${user.getId()}" class="bg-gray-600 text-white px-4 py-2 rounded hover:bg-gray-500">Edit</a>
                        <a href="/users/delete/${user.getId()}" class="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-500">Delete</a>
                    </td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`)
