package gui4me.store;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gui4me.store.dto.TomTomGeocodeResponse;

@Service
public class TomTomService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${tomtom.api-key}")
    private String apiKey;

    public TomTomGeocodeResponse geocode(String address) {

        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String url = "https://api.tomtom.com/search/2/geocode/" + encodedAddress + ".json?key=" + apiKey;

        TomTomGeocodeResponse response = restTemplate.getForObject(url, TomTomGeocodeResponse.class);

        return response;
    }
}
