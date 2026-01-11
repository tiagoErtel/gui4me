package gui4me.store;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import gui4me.store.dto.ReceitaWsResponse;

@Service
public class ReceitaWsService {

    private final RestTemplate restTemplate;

    public ReceitaWsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ReceitaWsResponse fetchByCnpj(String cnpj) {
        String sanitized = cnpj.replaceAll("[^\\d]", "");
        String url = "https://receitaws.com.br/v1/cnpj/" + sanitized;

        ReceitaWsResponse response = restTemplate.getForObject(url, ReceitaWsResponse.class);

        if (response == null || response.getNome() == null || response.getCnpj() == null) {
            throw new RuntimeException("Failed to fetch or parse data from ReceitaWS");
        }

        return response;
    }
}
