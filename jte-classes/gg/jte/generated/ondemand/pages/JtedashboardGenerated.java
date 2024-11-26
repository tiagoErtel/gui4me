package gg.jte.generated.ondemand.pages;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import gui4me.CsrfHiddenInput;
public final class JtedashboardGenerated {
	public static final String JTE_NAME = "pages/dashboard.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,4,4,4,9,9,10,10,19,19,19,20,20,21,21,21,22,22,24,24,24,41,41,41,43,43,47,47,49,49,49,51,51,54,54,55,55,58,58,58,60,60,66,66,66,66,66,4,5,6,7,7,7,7};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String username, String email, Collection<? extends GrantedAuthority> authorities, CsrfHiddenInput csrfHiddenInput) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"min-h-screen bg-gray-100\">\r\n        <nav class=\"bg-white shadow-sm\">\r\n            <div class=\"max-w-7xl mx-auto px-4 sm:px-6 lg:px-8\">\r\n                <div class=\"flex justify-between h-16\">\r\n                    <div class=\"flex items-center\">\r\n                        <h1 class=\"text-xl font-semibold\">Dashboard</h1>\r\n                    </div>\r\n                    <div class=\"flex items-center space-x-4\">\r\n                        <span class=\"text-gray-700\">Welcome, ");
				jteOutput.setContext("span", null);
				jteOutput.writeUserContent(username);
				jteOutput.writeContent("</span>\r\n                        ");
				if (email != null) {
					jteOutput.writeContent("\r\n                            <span class=\"text-gray-500 text-sm\">");
					jteOutput.setContext("span", null);
					jteOutput.writeUserContent(email);
					jteOutput.writeContent("</span>\r\n                        ");
				}
				jteOutput.writeContent("\r\n                        <form action=\"/logout\" method=\"post\">\r\n                            ");
				jteOutput.setContext("form", null);
				jteOutput.writeUserContent(csrfHiddenInput);
				jteOutput.writeContent("\r\n                            <button type=\"submit\"\r\n                                    class=\"px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700\">\r\n                                Logout\r\n                            </button>\r\n                        </form>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </nav>\r\n        <main class=\"max-w-7xl mx-auto py-6 sm:px-6 lg:px-8\">\r\n            <div class=\"px-4 py-6 sm:px-0\">\r\n                <div class=\"bg-white shadow rounded-lg p-6\">\r\n                    <h2 class=\"text-2xl font-semibold text-gray-800 mb-4\">User Information</h2>\r\n                    <div class=\"space-y-4\">\r\n                        <div>\r\n                            <p class=\"text-sm font-medium text-gray-500\">Username</p>\r\n                            <p class=\"mt-1 text-lg text-gray-900\">");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(username);
				jteOutput.writeContent("</p>\r\n                        </div>\r\n                        ");
				if (authorities != null && !authorities.isEmpty()) {
					jteOutput.writeContent("\r\n                            <div>\r\n                                <p class=\"text-sm font-medium text-gray-500\">Roles</p>\r\n                                <div class=\"mt-1 flex flex-wrap gap-2\">\r\n                                    ");
					for (var authority : authorities) {
						jteOutput.writeContent("\r\n                                        <span class=\"inline-flex items-center px-2.5 py-0.5 rounded-full text-sm font-medium bg-blue-100 text-blue-800\">\r\n                                        ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(authority.getAuthority());
						jteOutput.writeContent("\r\n                                    </span>\r\n                                    ");
					}
					jteOutput.writeContent("\r\n                                </div>\r\n                            </div>\r\n                        ");
				}
				jteOutput.writeContent("\r\n                        ");
				if (email != null) {
					jteOutput.writeContent("\r\n                            <div>\r\n                                <p class=\"text-sm font-medium text-gray-500\">Email</p>\r\n                                <p class=\"mt-1 text-lg text-gray-900\">");
					jteOutput.setContext("p", null);
					jteOutput.writeUserContent(email);
					jteOutput.writeContent("</p>\r\n                            </div>\r\n                        ");
				}
				jteOutput.writeContent("\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </main>\r\n    </div>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String username = (String)params.getOrDefault("username", "");
		String email = (String)params.getOrDefault("email", null);
		Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>)params.getOrDefault("authorities", null);
		CsrfHiddenInput csrfHiddenInput = (CsrfHiddenInput)params.get("csrfHiddenInput");
		render(jteOutput, jteHtmlInterceptor, username, email, authorities, csrfHiddenInput);
	}
}
