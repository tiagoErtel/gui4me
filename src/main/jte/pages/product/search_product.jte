@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import gui4me.product.Product
@import java.util.List

@param Message message = null
@param String username = ""
@param String email = null
@param CsrfHiddenInput csrfHiddenInput
@param List<Product> productList = null

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
username = username,
content = @`
<div class="min-h-screen bg-gray-100">
    <main class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
        <div class="bg-white shadow rounded-lg p-6">
            <h2 class="text-2xl font-semibold text-gray-800 mb-6">Search Products</h2>

            <form action="/product/search" method="GET" class="flex space-x-4">
                <input
                        type="text"
                        name="productName"
                        placeholder="Search by product name..."
                        class="flex-1 border-gray-300 border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
                />
                <button
                        type="submit"
                        class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400"
                >
                    Search
                </button>
            </form>

            <div class="mt-6">
                <!-- Results Section -->
                <table class="w-full text-left border-collapse">
                    <thead>
                    <tr>
                        <th class="border-b py-2 px-4">Product Name</th>
                        <th class="border-b py-2 px-4">Unit Price</th>
                        <th class="border-b py-2 px-4">Store</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Example rows (replace with JTE logic) -->
                    <tr>
                        <td class="py-2 px-4">Product A</td>
                        <td class="py-2 px-4">$12.99</td>
                        <td class="py-2 px-4">Store X</td>
                    </tr>
                    <!-- End Example Rows -->
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</div>
`)
