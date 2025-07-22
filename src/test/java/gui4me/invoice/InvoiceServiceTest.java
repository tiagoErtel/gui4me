package gui4me.invoice;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import gui4me.exceptions.invoice.InvoiceAlreadyProcessedException;
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
    public void shouldThrowIfInvoiceAlreadyProcessed() throws IOException {
        String invoiceUrl = "https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx?p=123";
        Document doc = Jsoup
                .parse("""
                            <!DOCTYPE html PUBLIC " -//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                            <html>
                                <head>
                                <meta http-equiv="X-UA-Compatible" content="IE=9, IE=edge">
                                <meta charset="utf-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1">
                                <link href="../Content/Estilos/Nfce/QrCode/css/jquery.mobile-1.4.5.min.css" rel="stylesheet" type="text/css">
                                <link href="../Content/Estilos/Nfce/QrCode/css/nfceMob.css" rel="stylesheet" type="text/css">
                                <link href="../Content/Estilos/Nfce/QrCode/css/nfceMob_ie.css" rel="stylesheet" type="text/css">
                                </head>
                                <body>
                                <div data-role="header" xmlns:n="http://www.portalfiscal.inf.br/nfe" xmlns:chave="http://exslt.org/chaveacesso" xmlns:r="http://www.serpro.gov.br/nfe/remessanfe.xsd">
                                <h1 class="tit"><img src="../Content/Estilos/Nfce/QrCode/images/logoNFCe.png" width="90" height="64" alt="NFC-e">
                                <p>DOCUMENTO AUXILIAR DA NOTA FISCAL DE CONSUMIDOR ELETRÔNICA</p>
                                <p></p></h1>
                                </div>
                                <div data-role="content" xmlns:n="http://www.portalfiscal.inf.br/nfe" xmlns:chave="http://exslt.org/chaveacesso" xmlns:r="http://www.serpro.gov.br/nfe/remessanfe.xsd">
                                <div id="conteudo">
                                <div class="txtCenter">
                                    <div id="u20" class="txtTopo">
                                    COOPERATIVA SANTA CLARA LTDA
                                    </div>
                                    <div class="text">
                                    CNPJ: 88.587.357/0050-47
                                    </div>
                                    <div class="text">
                                    RUA JULIO DE CASTILHOS , 1 , , CENTRO , CARLOS BARBOSA , RS
                                    </div>
                                </div>
                                <table border="0" align="center" cellpadding="0" cellspacing="0" id="tabResult" data-filter="true">
                                    <tbody>
                                    <tr id="Item + 1">
                                    <td valign="top"><span class="txtTit">Isotonico Gatorade M</span><span class="RCod"> (Código: 611035 ) </span><br><span class="Rqtd"><strong>Qtde.:</strong>3</span><span class="RUN"><strong>UN: </strong>Un</span><span class="RvlUnit"><strong>Vl. Unit.:</strong> &nbsp; 5,19</span></td>
                                    <td align="right" valign="top" class="txtTit noWrap">Vl. Total <br><span class="valor">15,57</span></td>
                                    </tr>
                                    <tr id="Item + 2">
                                    <td valign="top"><span class="txtTit">Isotonico Gatorade F</span><span class="RCod"> (Código: 608094 ) </span><br><span class="Rqtd"><strong>Qtde.:</strong>2</span><span class="RUN"><strong>UN: </strong>Un</span><span class="RvlUnit"><strong>Vl. Unit.:</strong> &nbsp; 5,19</span></td>
                                    <td align="right" valign="top" class="txtTit noWrap">Vl. Total <br><span class="valor">10,38</span></td>
                                    </tr>
                                    <tr id="Item + 3">
                                    <td valign="top"><span class="txtTit">Isotonico Gatorade L</span><span class="RCod"> (Código: 611034 ) </span><br><span class="Rqtd"><strong>Qtde.:</strong>2</span><span class="RUN"><strong>UN: </strong>Un</span><span class="RvlUnit"><strong>Vl. Unit.:</strong> &nbsp; 5,19</span></td>
                                    <td align="right" valign="top" class="txtTit noWrap">Vl. Total <br><span class="valor">10,38</span></td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div id="totalNota" class="txtRight">
                                    <div id="linhaTotal">
                                    <label>Qtd. total de itens:</label><span class="totalNumb">3</span>
                                    </div>
                                    <div id="linhaTotal" class="linhaShade">
                                    <label>Valor a pagar R$:</label><span class="totalNumb txtMax">36,33</span>
                                    </div>
                                    <div id="linhaForma">
                                    <label>Forma de pagamento:</label><span class="totalNumb txtTitR">Valor pago R$:</span>
                                    </div>
                                    <div id="linhaTotal">
                                    <label class="tx"> Cartão de Débito </label><span class="totalNumb">36,33</span>
                                    </div>
                                    <div id="linhaTotal"></div>
                                    <div id="linhaTotal" class="spcTop">
                                    <label class="txtObs">Informação dos Tributos Totais Incidentes (Lei Federal 12.741/2012)&nbsp;R$</label><span class="totalNumb txtObs">11,03</span>
                                    </div>
                                </div>
                                </div>
                                <div id="infos" class="txtCenter">
                                <div data-role="collapsible" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-collapsed="false">
                                    <h4>Informações gerais da Nota</h4>
                                    <ul data-role="listview" data-inset="false">
                                    <li><strong>EMISSÃO NORMAL</strong><br><br><strong>Número: </strong>568375<strong> Série: </strong>61<strong> Emissão: </strong>29/03/2025 13:26:54 - Via Consumidor 2 <br><br><strong>Protocolo de Autorização: </strong>243250597624953 29/03/2025 às 13:27:52<br><br><strong> Ambiente de Produção - Versão XML: 4.00 - Versão XSLT: 2.07 </strong></li>
                                    </ul>
                                </div>
                                <div data-role="collapsible" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-collapsed="false">
                                    <h4>Chave de acesso</h4>
                                    <ul data-role="listview" data-inset="false">
                                    <li>Consulte pela Chave de Acesso em www.sefaz.rs.gov.br/nfce/consulta<br><br><strong>Chave de acesso:</strong><br><span class="chave">FAKE-INVOICE-KEY</span></li>
                                    </ul>
                                </div>
                                <div data-role="collapsible" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-collapsed="false">
                                    <h4>Consumidor</h4>
                                    <ul data-role="listview" data-inset="false">
                                    <li><strong>Consumidor não identificado</strong></li>
                                    </ul>
                                </div>
                                </div>
                                </div>
                                <script src="../Content/Estilos/Nfce/QrCode/js/jquery.js"></script>
                                <script src="../Content/Estilos/Nfce/QrCode/js/jqueryui.js"></script>
                                <script src="../Content/Estilos/Nfce/QrCode/js/jquery.mobile-1.4.5.min.js"></script>
                                <script src="../Content/Estilos/Nfce/QrCode/js/index.js"></script>
                                </body>
                            </html>
                        """);

        try (MockedStatic<Jsoup> jsoupMockedStatic = mockStatic(Jsoup.class)) {
            Connection mockConnection = mock(Connection.class);
            jsoupMockedStatic.when(() -> Jsoup.connect(invoiceUrl)).thenReturn(mockConnection);
            when(mockConnection.get()).thenReturn(doc);

            when(invoiceRepository.findByKey("FAKE-INVOICE-KEY")).thenReturn(Optional.of(new Invoice()));

            User user = new User();

            assertThrows(InvoiceAlreadyProcessedException.class, () -> {
                invoiceService.save(invoiceUrl, user);
            });
        }
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
