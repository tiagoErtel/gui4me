package gg.jte.generated.ondemand.layout;
public final class JtemainGenerated {
	public static final String JTE_NAME = "layout/main.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,15,15,21,31,40,48,50,50,50,53,53,53,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, gg.jte.Content content) {
		jteOutput.writeContent("\r\n<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n    <title>Gui4Me</title>\r\n    <link href=\"src/main/resources/static/css/styles.css\" rel=\"stylesheet\">\r\n    <style>\r\nbody {\r\n  margin: 0;\r\n  font-family: Arial, Helvetica, sans-serif;\r\n}\r\n\r\n");
		jteOutput.writeContent("\r\n.navbar {\r\n    background-color: #333;\r\n    overflow: hidden;\r\n  }\r\n  \r\n  ");
		jteOutput.writeContent("\r\n  .navbar a {\r\n    float: left;\r\n    color: #f2f2f2;\r\n    text-align: center;\r\n    padding: 14px 16px;\r\n    text-decoration: none;\r\n    font-size: 17px;\r\n  }\r\n  \r\n  ");
		jteOutput.writeContent("\r\n  .navbar a:hover {\r\n    background-color: #ddd;\r\n    color: black;\r\n  }\r\n    </style>\r\n</head>\r\n<body>\r\n\r\n    ");
		jteOutput.writeContent("\r\n    <div class=\"navbar\">\r\n        <a href=\"/\">Home</a>\r\n        <a href=\"/users\">Users</a>\r\n        <a href=\"/products\">Products</a>\r\n        <a href=\"/market\">Market</a>\r\n    </div>\r\n\r\n    ");
		jteOutput.writeContent("\r\n    <main>\r\n        ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\r\n    </main>\r\n</body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		gg.jte.Content content = (gg.jte.Content)params.get("content");
		render(jteOutput, jteHtmlInterceptor, content);
	}
}
