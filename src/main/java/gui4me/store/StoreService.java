package gui4me.store;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gui4me.store.dto.ReceitaWsResponse;
import gui4me.store.dto.TomTomGeocodeResponse;
import gui4me.store.dto.TomTomGeocodeResponse.Result;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ReceitaWsService receitaWsService;

    @Autowired
    private TomTomService tomTomService;

    public Store save(Store store) {
        return storeRepository.save(store);
    }

    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public Optional<Store> findByDocument(String document) {
        return storeRepository.findByDocument(document);
    }

    public Store fetchAndSaveByCnpj(String cnpj) {
        Store store = findByDocument(cnpj)
                .orElseGet(() -> createFromReceitaWs(cnpj));

        return save(store);
    }

    private Store createFromReceitaWs(String cnpj) {
        ReceitaWsResponse response = receitaWsService.fetchByCnpj(cnpj);

        Store store = new Store();
        store.setDocument(response.getCnpj());
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
        address = getCoordinatesFromAddress(address);
        store.setAddress(address);

        Contact contact = new Contact();
        contact.setEmail(response.getEmail());
        contact.setPhone(response.getTelefone());
        store.setContact(contact);

        return store;
    }

    private Address getCoordinatesFromAddress(Address address) {

        String stringAddress = String.join(" ", address.getNumber(), address.getStreet(), address.getStreet(),
                address.getNeighborhood(), address.getCity(), address.getState(), address.getZipCode());

        TomTomGeocodeResponse response = tomTomService.geocode(stringAddress);

        Result result = response.getTheBestMatchResut();

        address.setLatitude(result.getPosition().getLat());
        address.setLongitude(result.getPosition().getLon());

        return address;

    }

}
