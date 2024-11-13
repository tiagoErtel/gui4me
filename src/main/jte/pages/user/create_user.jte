@import gui4me.user.User

@param User users

@template.layout.main(content = @`
	<div class="sidebar">
		<a class="active" href="#General">General</a>
		<a href="#Preferences">Preferences</a>
	</div>

	<div class="content">
        <h1>
            @if(user.getId().isEmpty())
                Create User
            @else
                Edit User
            @endif
        </h1>
		<form action="${user.id != null} ? @{/users/edit} : @{/users}" method="post">
			<div class="section" id="general">
				<h2>General</h2>

				<div class="form-group">
					<label for="username">Username</label>
					<input type="text" id="username" th:field="*{username}" class="form-control" required>
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" id="password" th:field="*{password}" class="form-control"
						th:disabled="${user.id != null}">
				</div>
			</div>

			<div class="section" id="preferences">
				<h2>Preferences</h2>
				<div class="form-group">
					<label for="currency">Currency</label>
					<select id="currency" th:field="*{userCurrency}" class="form-control" required>
						<option value="" disabled selected>Select Currency</option>
						<option th:each="curr : ${currencies}" th:value="${curr}" th:text="${curr}"></option>
					</select>
				</div>
				<div class="form-group">
					<label for="language">Language</label>
					<select id="language" th:field="*{userLanguage}" class="form-control" required>
						<option value="" disabled selected>Select Language</option>
						<option th:each="lang : ${languages}" th:value="${lang}" th:text="${lang}"></option>
					</select>
				</div>
			</div>

			<button type="submit" class="btn btn-primary"
				th:text="${user.id != null} ? 'Save Changes' : 'Create User'">Create User</button>
			<a th:href="@{/users}" class="btn btn-secondary">Cancel</a>
		</form>
	</div>
`)