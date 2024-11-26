package gg.jte.generated.ondemand.pages.user;
import gui4me.user.User;
import java.util.List;
public final class Jtelist_usersGenerated {
	public static final String JTE_NAME = "pages/user/list_users.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,5,5,5,5,22,22,24,24,24,25,25,25,26,26,26,27,27,27,29,29,29,29,30,30,30,30,33,33,37,37,37,38,38,38,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<User> users) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"container mx-auto p-4\">\r\n        <div class=\"flex justify-between items-center mb-4\">\r\n            <h1 class=\"text-2xl font-semibold text-white\">User List</h1>\r\n            <a href=\"/users/create\" class=\"bg-gray-700 text-white px-4 py-2 rounded hover:bg-gray-600\">Create User</a>\r\n        </div>\r\n        <table class=\"min-w-full table-auto text-gray-300\">\r\n            <thead class=\"bg-gray-800\">\r\n            <tr>\r\n                <th class=\"px-4 py-2\">ID</th>\r\n                <th class=\"px-4 py-2\">Username</th>\r\n                <th class=\"px-4 py-2\">Language</th>\r\n                <th class=\"px-4 py-2\">Currency</th>\r\n                <th class=\"px-4 py-2\">Actions</th>\r\n            </tr>\r\n            </thead>\r\n            <tbody class=\"bg-gray-900\">\r\n            ");
				for (User user : users) {
					jteOutput.writeContent("\r\n                <tr class=\"hover:bg-gray-800\">\r\n                    <td class=\"px-4 py-2\">");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(user.getId());
					jteOutput.writeContent("</td>\r\n                    <td class=\"px-4 py-2\">");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(user.getUsername());
					jteOutput.writeContent("</td>\r\n                    <td class=\"px-4 py-2\">");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(user.getLanguage());
					jteOutput.writeContent("</td>\r\n                    <td class=\"px-4 py-2\">");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(user.getCurrency());
					jteOutput.writeContent("</td>\r\n                    <td class=\"px-4 py-2\">\r\n                        <a href=\"/users/edit/");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(user.getId());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\" class=\"bg-gray-600 text-white px-4 py-2 rounded hover:bg-gray-500\">Edit</a>\r\n                        <a href=\"/users/delete/");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(user.getId());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\" class=\"bg-red-600 text-white px-4 py-2 rounded hover:bg-red-500\">Delete</a>\r\n                    </td>\r\n                </tr>\r\n            ");
				}
				jteOutput.writeContent("\r\n            </tbody>\r\n        </table>\r\n    </div>\r\n");
			}
		});
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		List<User> users = (List<User>)params.get("users");
		render(jteOutput, jteHtmlInterceptor, users);
	}
}
