package gui4me.invoice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gui4me.user.User;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    Optional<Invoice> findByKey(String key);

    List<Invoice> findAllByUser(User user, Sort sort);
}
