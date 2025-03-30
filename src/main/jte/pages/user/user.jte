@import gui4me.custom_user_details.CustomUserDetails
@import gui4me.custom_user_details.Currency
@import gui4me.custom_user_details.Language
@import java.util.List

@param CustomUserDetails user
@param List<Currency> currencies
@param List<Language> languages

@template.layout.main(content = @`
	<div class="container mx-auto p-4 flex">
		<div class="w-1/4 bg-gray-800 text-white p-4">
			<a href="#general" class="block py-2 px-4 hover:bg-gray-700">General</a>
			<a href="#preferences" class="block py-2 px-4 hover:bg-gray-700">Preferences</a>
		</div>

		<div class="w-3/4 p-4 bg-gray-900 shadow-lg rounded-lg">
			<h1 class="text-2xl font-semibold text-white mb-4">
				@if(user.getId() == null)
					Create User
				@else
					Edit User
				@endif
			</h1>

			<form action="/users/save" method="post">
				<input type="hidden" name="id" value="${user.getId()}">

				<!-- General Section -->
				<div id="general" class="mb-6">
					<h2 class="text-xl font-semibold text-white mb-4">General</h2>
					<div class="mb-4">
						<label for="username" class="block text-gray-300 font-medium">Username</label>
						<input type="text" id="username" name="username" value="${user.getUsername()}" class="w-full p-2 mt-1 bg-gray-800 border border-gray-700 rounded focus:outline-none focus:ring-2 focus:ring-gray-600" required>
					</div>
					<div class="mb-4">
						<label for="password" class="block text-gray-300 font-medium">Password</label>
						<input type="password" id="password" name="password" value="${user.getPassword()}" class="w-full p-2 mt-1 bg-gray-800 border border-gray-700 rounded focus:outline-none focus:ring-2 focus:ring-gray-600">
					</div>
				</div>

				<!-- Preferences Section -->
				<div id="preferences" class="mb-6">
					<h2 class="text-xl font-semibold text-white mb-4">Preferences</h2>
					<div class="mb-4">
						<label for="currency" class="block text-gray-300 font-medium">Currency</label>
						<select id="currency" name="currency" class="w-full p-2 mt-1 bg-gray-800 border border-gray-700 rounded focus:outline-none focus:ring-2 focus:ring-gray-600" required>
							<option value="" disabled selected>Select Currency</option>
							@for(Currency currency : currencies)
								<option value="${currency}" selected="${currency.equals(user.getCurrency())}">${currency}</option>
							@endfor
						</select>
					</div>
					<div class="mb-4">
						<label for="language" class="block text-gray-300 font-medium">Language</label>
						<select id="language" name="language" class="w-full p-2 mt-1 bg-gray-800 border border-gray-700 rounded focus:outline-none focus:ring-2 focus:ring-gray-600" required>
							<option value="" disabled selected>Select Language</option>
							@for(Language language : languages)
								<option value="${language}" selected="${language.equals(user.getLanguage())}">${language}</option>
							@endfor
						</select>
					</div>
				</div>

				<div class="flex justify-end">
					<button type="submit" class="bg-gray-700 text-white px-6 py-2 rounded hover:bg-gray-600">Save</button>
					<a href="/users" class="ml-4 bg-gray-500 text-white px-6 py-2 rounded hover:bg-gray-400">Cancel</a>
				</div>
			</form>
		</div>
	</div>
`)
