package gg.jte.generated.ondemand.pages.user;
import gui4me.user.User;
import java.util.List;
public final class Jtelist_usersGenerated {
	public static final String JTE_NAME = "pages/user/list_users.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,5,5,5,5,21,21,23,23,23,24,24,24,25,25,25,26,26,26,28,28,28,28,29,29,29,29,32,32,36,36,36,36,36,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<User> users) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div>\r\n        <h1>User List</h1>\r\n        <a href=\"/users/create\">Create New User</a>\r\n\r\n        <table>\r\n            <thead>\r\n                <tr>\r\n                    <th>ID</th>\r\n                    <th>Username</th>\r\n                    <th>Language</th>\r\n                    <th>Currency</th>\r\n                    <th>Actions</th>\r\n                </tr>\r\n            </thead>\r\n            <tbody>\r\n                ");
				for (User user : users) {
					jteOutput.writeContent("\r\n                <tr>\r\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(user.getId());
					jteOutput.writeContent("</td>\r\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(user.getUsername());
					jteOutput.writeContent("</td>\r\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(user.getUserLanguage());
					jteOutput.writeContent("</td>\r\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(user.getUserCurrency());
					jteOutput.writeContent("</td>\r\n                    <td>\r\n                        <a href=\"/users/edit/");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(user.getId());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\">Edit</a>\r\n                        <a href=\"/users/delete/");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(user.getId());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\">Delete</a>\r\n                    </td>\r\n                </tr>\r\n                ");
				}
				jteOutput.writeContent("\r\n            </tbody>\r\n        </table>\r\n    </div>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		List<User> users = (List<User>)params.get("users");
		render(jteOutput, jteHtmlInterceptor, users);
	}
}
