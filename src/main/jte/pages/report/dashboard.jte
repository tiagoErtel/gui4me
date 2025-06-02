@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message

@param Message message = null
@param String username = ""
@param String email = null
@param CsrfHiddenInput csrfHiddenInput
@param String invoicesByStore

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
username = username,
content = @`
    @template.partials.message(message = message)
    @template.partials.back-button(href = "/dashboard")

    <h1>There is nothing to see here at the moment :(</h1>

    <canvas id="invoicesByStoreChart" width="200" height="200"></canvas>

    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
    <script>
        const data = JSON.parse('${invoicesByStore}');
        
        new Chart(
            document.getElementById('invoicesByStoreChart'),
            {
                type: 'bar',
                data: {
                    labels: data.map(row => row.storeName),
                    datasets: [
                        {
                            label: 'Invoices per store',
                            data: data.map(row => row.invoiceCount)
                        }
                    ]
                }
            }
        );
    </script>
`)
