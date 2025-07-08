package gui4me.email;

import java.util.HashMap;
import java.util.Map;

public class RecoverAccountTemplate implements BrevoTemplate {

    private final String username;
    private final String link;

    public RecoverAccountTemplate(String username, String link) {
        this.username = username;
        this.link = link;
    }

    @Override
    public int getTemplateId() {
        return 2;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("link", link);
        return params;
    }
}
