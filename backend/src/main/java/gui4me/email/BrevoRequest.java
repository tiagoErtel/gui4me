package gui4me.email;

import java.util.List;

public class BrevoRequest {

    private final BrevoTemplate template;
    private final List<String> receivers;

    public BrevoRequest(BrevoTemplate template, List<String> receivers) {
        this.template = template;
        this.receivers = receivers;
    }

    public BrevoTemplate getTemplate() {
        return template;
    }

    public List<String> getReceivers() {
        return receivers;
    }
}
