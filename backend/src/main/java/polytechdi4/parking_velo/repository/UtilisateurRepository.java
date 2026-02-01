package polytechdi4.parking_velo.repository;

import polytechdi4.parking_velo.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    boolean existsByMail(String email);
    boolean existsByMailAndIdNot(String mail, Integer id);
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, Integer id);
    Optional<Utilisateur> findByMail(String mail);

}
