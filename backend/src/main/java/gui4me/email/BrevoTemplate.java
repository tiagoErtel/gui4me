package gui4me.email;

import java.util.Map;

public interface BrevoTemplate {
    int getTemplateId();

    Map<String, Object> getParams();
}
