package gui4me.invoice;

import gui4me.invoice_item.InvoiceItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String chave;

    @NotBlank
    private String html;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items;

    public String getId() {
        return id;
    }

    public String getChave() {
        return chave;
    }

    public String getHtml() {
        return html;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }
}
