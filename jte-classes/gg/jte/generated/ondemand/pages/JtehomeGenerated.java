package gg.jte.generated.ondemand.pages;
public final class JtehomeGenerated {
	public static final String JTE_NAME = "pages/home.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,0,0,12,12,12,12,12,12,12,12};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"min-h-screen flex items-center justify-center\">\r\n        <div class=\"max-w-md w-full space-y-8 p-8 bg-white rounded-lg shadow-md\">\r\n            <h1 class=\"text-4xl font-bold text-center\">Welcome</h1>\r\n            <div class=\"flex justify-center\">\r\n                <a href=\"/login\"\r\n                   class=\"px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700\">\r\n                    Login\r\n                </a>\r\n            </div>\r\n        </div>\r\n    </div>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
