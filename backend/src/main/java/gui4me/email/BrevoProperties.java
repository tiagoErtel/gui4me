package gui4me.email;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("brevo")
public class BrevoProperties {

    private String key;
    private String url;
    private boolean enabled;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
