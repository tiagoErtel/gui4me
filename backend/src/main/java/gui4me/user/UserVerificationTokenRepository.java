package gui4me.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerificationTokenRepository extends JpaRepository<UserVerificationToken, String> {

    Optional<UserVerificationToken> findByToken(String token);

}
