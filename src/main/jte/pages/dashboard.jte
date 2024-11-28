@import org.springframework.security.core.GrantedAuthority
@import java.util.Collection

@param String username = ""
@param String email = null

@template.layout.main(
navbar = @`
    <nav class="bg-white shadow-sm">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div class="flex justify-between h-16">
                <div class="flex items-center">
                    <h1 class="text-xl font-semibold">Dashboard</h1>
                </div>
                <div class="flex items-center space-x-4">
                    <span class="text-gray-700">Welcome, ${username}</span>
                    @if(email != null)
                        <span class="text-gray-500 text-sm">${email}</span>
                    @endif
                    <form action="/logout" method="post">
                        <%-- ${csrfHiddenInput} --%>
                        <button type="submit"
                                class="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700">
                            Logout
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </nav>
`,

content = @`
    <main class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
        <h1>Hello</h1>
    </main>
`)