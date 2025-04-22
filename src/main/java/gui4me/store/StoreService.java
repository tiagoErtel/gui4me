package gui4me.store;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public Store checkStoreDocAndSave(Store newStore){
        Optional<Store> store = storeRepository.findByDocument(newStore.getDocument());

        if (store.isEmpty()){
            return storeRepository.save(newStore);
        } else {
            return store.get();
        }
    }

    private final RestTemplate restTemplate = new RestTemplate();

    public String getFullAddressFromCnpj(String cnpj) {
        String url = "https://receitaws.com.br/v1/cnpj/" + cnpj;

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, String> body = response.getBody();
            return body.get("logradouro") + ", " + body.get("numero") + ", " +
                    body.get("municipio") + " - " + body.get("uf") + ", " + body.get("cep");
        }
        return null;
    }

    public Point geocodeAddress(String address) {
        String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + UriUtils.encode(address, StandardCharsets.UTF_8);

        ResponseEntity<GeocodeResponse[]> response = restTemplate.getForEntity(url, GeocodeResponse[].class);
        if (response.getBody() != null && response.getBody().length > 0) {
            GeocodeResponse geo = response.getBody()[0];
            double lat = Double.parseDouble(geo.lat);
            double lon = Double.parseDouble(geo.lon);

            GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
            return geometryFactory.createPoint(new Coordinate(lon, lat));
        }

        return null;
    }
}
