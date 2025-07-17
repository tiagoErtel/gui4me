package gui4me.invoice;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gui4me.exceptions.invoice.InvoiceParseErrorException;
import gui4me.exceptions.invoice.InvoiceUrlIsNotQrCode;
import gui4me.invoice_item.InvoiceItemRepository;
import gui4me.product.ProductService;
import gui4me.store.StoreService;
import gui4me.user.User;

class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private InvoiceItemRepository invoiceItemRepository;

    @Mock
    private StoreService storeService;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldThrowIfMalformedUrl() {
        String url = "https://www.sefaz.gov.br/nfe";
        User user = new User();

        assertThrows(InvoiceParseErrorException.class, () -> {
            invoiceService.save(url, user);
        });
    }

    @Test
    public void shouldThrowIfUrlIsNotQrCode() {
        String url = "https://www.sefaz.gov.br/nfe?chaveNFe=12345678901234567890123456789012345678901234";
        User user = new User();

        assertThrows(InvoiceUrlIsNotQrCode.class, () -> {
            invoiceService.save(url, user);
        });
    }
}
