package gui4me.store;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    public Optional<Store> findByDocument(String document);

    List<Store> findByLastUpdateBefore(LocalDateTime dateTime);
}
