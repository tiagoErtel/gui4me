package gui4me.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gui4me.store.dto.ReceitaWsResponse;
import gui4me.store.dto.TomTomGeocodeResponse;
import gui4me.store.dto.TomTomGeocodeResponse.Result;

class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private ReceitaWsService receitaWsService;

    @Mock
    private TomTomService tomTomService;

    @InjectMocks
    private StoreService storeService;

    private final String CNPJ = "12345678000199";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldDelegateToRepository() {
        Store store = new Store();
        when(storeRepository.save(store)).thenReturn(store);

        Store result = storeService.save(store);

        verify(storeRepository).save(store);
        assertEquals(store, result);
    }

    @Test
    void findAll_shouldReturnAllStores() {
        List<Store> stores = List.of(new Store(), new Store());
        when(storeRepository.findAll()).thenReturn(stores);

        List<Store> result = storeService.findAll();

        verify(storeRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void findByDocument_shouldReturnStoreIfFound() {
        Store store = new Store();
        when(storeRepository.findByDocument(CNPJ)).thenReturn(Optional.of(store));

        Optional<Store> result = storeService.findByDocument(CNPJ);

        verify(storeRepository).findByDocument(CNPJ);
        assertTrue(result.isPresent());
        assertEquals(store, result.get());
    }

    @Test
    void fetchAndSaveByCnpj_whenStoreExists_shouldSaveAndReturnExisting() {
        Store existingStore = new Store();
        when(storeRepository.findByDocument(CNPJ)).thenReturn(Optional.of(existingStore));
        when(storeRepository.save(existingStore)).thenReturn(existingStore);

        Store result = storeService.fetchAndSaveByCnpj(CNPJ);

        verify(storeRepository).findByDocument(CNPJ);
        verify(storeRepository).save(existingStore);
        assertEquals(existingStore, result);
    }

    @Test
    void fetchAndSaveByCnpj_whenStoreDoesNotExist_shouldFetchFromReceitaAndSave() {
        when(storeRepository.findByDocument(CNPJ)).thenReturn(Optional.empty());

        ReceitaWsResponse wsResponse = mock(ReceitaWsResponse.class);
        when(receitaWsService.fetchByCnpj(CNPJ)).thenReturn(wsResponse);

        when(wsResponse.getCnpj()).thenReturn(CNPJ);
        when(wsResponse.getNome()).thenReturn("Store Name");
        when(wsResponse.getFantasia()).thenReturn("Fantasy Name");
        when(wsResponse.getTipo()).thenReturn("Type");
        when(wsResponse.getPorte()).thenReturn("Size");
        when(wsResponse.getLogradouro()).thenReturn("Street");
        when(wsResponse.getNumero()).thenReturn("123");
        when(wsResponse.getComplemento()).thenReturn("Complement");
        when(wsResponse.getBairro()).thenReturn("Neighborhood");
        when(wsResponse.getMunicipio()).thenReturn("City");
        when(wsResponse.getUf()).thenReturn("ST");
        when(wsResponse.getCep()).thenReturn("12345-678");
        when(wsResponse.getEmail()).thenReturn("email@store.com");
        when(wsResponse.getTelefone()).thenReturn("123456789");

        TomTomGeocodeResponse tomTomResponse = mock(TomTomGeocodeResponse.class);
        Result resultMock = mock(Result.class);
        TomTomGeocodeResponse.Position position = mock(TomTomGeocodeResponse.Position.class);
        when(tomTomResponse.getTheBestMatchResut()).thenReturn(resultMock);
        when(resultMock.getPosition()).thenReturn(position);
        when(position.getLat()).thenReturn(10.0);
        when(position.getLon()).thenReturn(20.0);

        when(tomTomService.geocode(anyString())).thenReturn(tomTomResponse);

        ArgumentCaptor<Store> captor = ArgumentCaptor.forClass(Store.class);
        when(storeRepository.save(captor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        Store result = storeService.fetchAndSaveByCnpj(CNPJ);

        verify(storeRepository).findByDocument(CNPJ);
        verify(receitaWsService).fetchByCnpj(CNPJ);
        verify(tomTomService).geocode(anyString());
        verify(storeRepository).save(any(Store.class));

        Store savedStore = captor.getValue();
        assertEquals(CNPJ, savedStore.getDocument());
        assertEquals(result.getName(), savedStore.getName());
        assertEquals(result.getFantasyName(), savedStore.getFantasyName());
        assertEquals(result.getType(), savedStore.getType());
        assertEquals(result.getSize(), savedStore.getSize());
        assertNotNull(savedStore.getLastUpdate());
        assertNotNull(savedStore.getAddress());
        assertEquals(result.getAddress().getLatitude(), savedStore.getAddress().getLatitude());
        assertEquals(result.getAddress().getLongitude(), savedStore.getAddress().getLongitude());
        assertEquals(result.getContact().getEmail(), savedStore.getContact().getEmail());
        assertEquals(result.getContact().getPhone(), savedStore.getContact().getPhone());
    }
}
