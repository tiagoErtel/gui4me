package gui4me.invoice;

import gui4me.custom_user_details.CustomUserDetails;
import gui4me.invoice_item.InvoiceItem;
import gui4me.store.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Column(unique = true)
    private String chave;

    @NotNull
    private LocalDateTime issuanceDate;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUserDetails user;

    public CustomUserDetails getUser() {
        return user;
    }

    public void setUser(CustomUserDetails user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getChave() {
        return chave;
    }

    public LocalDateTime getIssuanceDate() { return issuanceDate; }

    public void setId(String id) {
        this.id = id;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setIssuanceDate(LocalDateTime issuanceDate) { this.issuanceDate = issuanceDate; }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public void addInvoiceItem(InvoiceItem invoiceItem){
        this.invoiceItems.add(invoiceItem);
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
