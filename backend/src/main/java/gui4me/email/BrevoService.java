package gui4me.email;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import gui4me.exceptions.email.EmailSendingException;

@Service
public class BrevoService {

    private static final Logger log = LoggerFactory.getLogger(BrevoService.class);

    private final RestClient client;
    private final BrevoProperties props;

    public BrevoService(RestClient client, BrevoProperties props) {
        this.client = client;
        this.props = props;
    }

    public void send(BrevoTemplate template, String... receivers) {
        if (props.isEnabled()) {
            submit(template, receivers);
        } else {
            log(template, receivers);
        }
    }

    private void submit(BrevoTemplate template, String... receivers) {
        List<String> receiverList = Arrays.asList(receivers);

        try {
            client.post()
                    .body(new BrevoRequest(template, receiverList))
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientException e) {
            throw new EmailSendingException("Failed to send email via Brevo", e);
        }
    }

    private void log(BrevoTemplate template, String... receivers) {
        log.info("Sending template {} to {}", template.getTemplateId(), String.join(", ", receivers));
    }
}
