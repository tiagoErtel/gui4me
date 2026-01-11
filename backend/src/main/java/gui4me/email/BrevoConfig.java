package gui4me.email;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(BrevoProperties.class)
public class BrevoConfig {

    private final BrevoProperties props;

    public BrevoConfig(BrevoProperties props) {
        this.props = props;
    }

    @Bean
    public RestClient client(RestClient.Builder builder) {
        return builder
                .baseUrl(props.getUrl())
                .defaultHeader("api-key", props.getKey())
                .build();
    }
}
