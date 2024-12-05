package gui4me.customUserDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomUserDetailsRepository extends JpaRepository<CustomUserDetails, String>{

    Optional<CustomUserDetails> findByEmail(String email);
}
