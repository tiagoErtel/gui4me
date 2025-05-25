package gui4me.invoice;

import gui4me.custom_user_details.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    Optional<Invoice> findByKey(String key);

    List<Invoice> findAllByUser(CustomUserDetails user);
}
