package gg.jte.generated.ondemand.pages.user;
import gui4me.user.User;
import gui4me.user.Currency;
import gui4me.user.Language;
import java.util.List;
public final class Jtecreate_userGenerated {
	public static final String JTE_NAME = "pages/user/create_user.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,9,9,9,9,17,17,19,19,21,21,29,29,29,29,29,29,29,29,29,33,33,33,33,33,33,33,33,33,41,41,41,41,41,41,41,41,41,43,43,44,44,44,44,44,44,44,44,44,45,45,50,50,50,50,50,50,50,50,50,52,52,53,53,53,53,53,53,53,53,53,54,54,63,63,63,63,63,5,6,7,7,7,7};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, User user, List<Currency> currencies, List<Language> languages) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n\t<div class=\"sidebar\">\r\n\t\t<a class=\"active\" href=\"#General\">General</a>\r\n\t\t<a href=\"#Preferences\">Preferences</a>\r\n\t</div>\r\n\r\n\t<div class=\"content\">\r\n        <h1>\r\n            ");
				if (user.getId() == null) {
					jteOutput.writeContent("\r\n                Create User\r\n            ");
				} else {
					jteOutput.writeContent("\r\n                Edit User\r\n            ");
				}
				jteOutput.writeContent("\r\n        </h1>\r\n\t\t<form action=\"/users/create\" method=\"post\">\r\n\t\t\t<div id=\"general\">\r\n\t\t\t\t<h2>General</h2>\r\n\r\n\t\t\t\t<div>\r\n\t\t\t\t\t<label for=\"username\">Username</label>\r\n\t\t\t\t\t<input type=\"text\" id=\"username\" name=\"username\"");
				var __jte_html_attribute_0 = user.getUsername();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" required>\r\n\t\t\t\t</div>\r\n\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t<label for=\"password\">Password</label>\r\n\t\t\t\t\t<input type=\"password\" id=\"password\" name=\"password\"");
				var __jte_html_attribute_1 = user.getPassword();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\t\t\t\r\n\t\t\t<div class=\"section\" id=\"preferences\">\r\n\t\t\t\t<h2>Preferences</h2>\r\n\t\t\t\t<div>\r\n\t\t\t\t\t<label for=\"currency\">Currency</label>\r\n\t\t\t\t\t<select id=\"currency\" name=\"currency\"");
				var __jte_html_attribute_2 = user.getCurrency();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("select", "value");
					jteOutput.writeUserContent(__jte_html_attribute_2);
					jteOutput.setContext("select", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" required>\r\n\t\t\t\t\t\t<option value=\"\" disabled selected>Select Currency</option>\r\n\t\t\t\t\t\t");
				for (Currency currency : currencies) {
					jteOutput.writeContent("\r\n\t\t\t\t\t\t\t<option");
					var __jte_html_attribute_3 = currency;
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("option", "value");
						jteOutput.writeUserContent(__jte_html_attribute_3);
						jteOutput.setContext("option", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent("></option>\r\n\t\t\t\t\t\t");
				}
				jteOutput.writeContent("\r\n\t\t\t\t\t</select>\r\n\t\t\t\t</div>\r\n\t\t\t\t<div>\r\n\t\t\t\t\t<label for=\"language\">Language</label>\r\n\t\t\t\t\t<select id=\"language\" name=\"language\"");
				var __jte_html_attribute_4 = user.getLanguage();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_4)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("select", "value");
					jteOutput.writeUserContent(__jte_html_attribute_4);
					jteOutput.setContext("select", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" required>\r\n\t\t\t\t\t\t<option value=\"\" disabled selected>Select Language</option>\r\n\t\t\t\t\t\t");
				for (Language language : languages) {
					jteOutput.writeContent("\r\n\t\t\t\t\t\t\t<option");
					var __jte_html_attribute_5 = language;
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_5)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("option", "value");
						jteOutput.writeUserContent(__jte_html_attribute_5);
						jteOutput.setContext("option", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent("></option>\r\n\t\t\t\t\t\t");
				}
				jteOutput.writeContent("\r\n\t\t\t\t\t</select>\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\r\n\t\t\t<button type=\"submit\">Create User</button>\r\n\t\t\t<a href=\"/users\">Cancel</a>\r\n\t\t</form>\r\n\t</div>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		User user = (User)params.get("user");
		List<Currency> currencies = (List<Currency>)params.get("currencies");
		List<Language> languages = (List<Language>)params.get("languages");
		render(jteOutput, jteHtmlInterceptor, user, currencies, languages);
	}
}
