@import gui4me.user.User
@import gui4me.user.Currency
@import gui4me.user.Language
@import java.util.List

@param User user
@param List<Currency> currencies
@param List<Language> languages

@template.layout.main(content = @`
	<div class="sidebar">
		<a class="active" href="#General">General</a>
		<a href="#Preferences">Preferences</a>
	</div>

	<div class="content">
        <h1>
            @if(user.getId() == null)
                Create User
            @else
                Edit User
            @endif
        </h1>
		<form action="/users/create" method="post">
			<div id="general">
				<h2>General</h2>

				<div>
					<label for="username">Username</label>
					<input type="text" id="username" name="username" value="${user.getUsername()}" required>
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" id="password" name="password" value="${user.getPassword()}">
				</div>
			</div>
			
			<div class="section" id="preferences">
				<h2>Preferences</h2>
				<div>
					<label for="currency">Currency</label>
					<select id="currency" name="currency" value="${user.getCurrency()}" required>
						<option value="" disabled selected>Select Currency</option>
						@for(Currency currency : currencies)
							<option value="${currency}"></option>
						@endfor
					</select>
				</div>
				<div>
					<label for="language">Language</label>
					<select id="language" name="language" value="${user.getLanguage()}" required>
						<option value="" disabled selected>Select Language</option>
						@for(Language language : languages)
							<option value="${language}"></option>
						@endfor
					</select>
				</div>
			</div>

			<button type="submit">Create User</button>
			<a href="/users">Cancel</a>
		</form>
	</div>
`)