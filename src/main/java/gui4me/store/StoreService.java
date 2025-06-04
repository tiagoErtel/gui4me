package gui4me.store;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gui4me.store.dto.ReceitaWsResponse;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    RestTemplate restTemplate;

    public Store save(Store store) {
        return storeRepository.save(store);
    }

    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public Optional<Store> findByDocument(String document) {
        return storeRepository.findByDocument(document);
    }

    public Store saveStoreFromReceitaWs(String cnpj) {
        Store store = fetchStoreFromReceitaWs(cnpj);

        return save(store);
    }

    public Store fetchStoreFromReceitaWs(String cnpj) {
        String url = "https://receitaws.com.br/v1/cnpj/" + cnpj.replaceAll("[^\\d]", "");

        ReceitaWsResponse response = restTemplate.getForObject(url, ReceitaWsResponse.class);

        if (response == null || response.getNome() == null || response.getCnpj() == null) {
            throw new RuntimeException("Failed to fetch or parse data from ReceitaWS");
        }

        Optional<Store> optionalStore = findByDocument(cnpj);

        Store store;
        if (optionalStore.isPresent()) {
            store = optionalStore.get();
        } else {
            store = new Store();
            store.setDocument(response.getCnpj());
        }

        store.setName(response.getNome());
        store.setFantasyName(response.getFantasia());
        store.setType(response.getTipo());
        store.setSize(response.getPorte());

        Address address = new Address();
        address.setStreet(response.getLogradouro());
        address.setNumber(response.getNumero());
        address.setComplement(response.getComplemento());
        address.setNeighborhood(response.getBairro());
        address.setCity(response.getMunicipio());
        address.setState(response.getUf());
        address.setZipCode(response.getCep());
        store.setAddress(address);

        Contact contact = new Contact();
        contact.setEmail(response.getEmail());
        contact.setPhone(response.getTelefone());
        store.setContact(contact);

        return store;
    }
}
