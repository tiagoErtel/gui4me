@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import gui4me.user.User

@param Message message = null
@param User currentUser
@param CsrfHiddenInput csrfHiddenInput
@param String invoicesByStore

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
currentUser = currentUser,
content = @`
    @template.partials.message(message = message)
    @template.partials.back-button(href = "/dashboard")

    <h1 class="title">Reports</h1>

    <div class="chart-grid">
        <div class="chart-box">
            <canvas id="invoicesByStoreChart" class="chart"></canvas>
        </div>

        <div class="chart-box">
            <canvas id="totalPriceByStoreChart" class="chart"></canvas>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

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
                            label: 'Invoices per Store',
                            data: data.map(row => row.invoiceCount)
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        x: {
                            ticks: {
                                callback: function(value, index, ticks) {
                                const label = this.getLabelForValue(value);
                                return label.length > 15 ? label.slice(0, 15) + '…' : label; // Truncate long labels
                                }
                            }
                        }
                    },
                    plugins: {
                        title: {
                            text: 'Invoices per Store',
                            display: true
                        }
                    }
                }
            }
        );

        new Chart(
            document.getElementById('totalPriceByStoreChart'),
            {
                type: 'bar',
                data: {
                    labels: data.map(row => row.storeName),
                    datasets: [
                        {
                            label: 'Total Spent per Store',
                            data: data.map(row => row.totalPrice)
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        x: {
                            ticks: {
                                callback: function(value, index, ticks) {
                                const label = this.getLabelForValue(value);
                                return label.length > 15 ? label.slice(0, 15) + '…' : label; // Truncate long labels
                                }
                            }
                        },
                        y: {
                            ticks: {
                                beginAtZero: true,
                                callback: function(value, index, values) {
                                    return 'R$' + value.toString();
                                }
                            }
                        }
                    },
                    plugins: {
                        title: {
                            text: 'Total Spent per Store',
                            display: true
                        }
                    }
                }
            }
        );

    </script>
`)
