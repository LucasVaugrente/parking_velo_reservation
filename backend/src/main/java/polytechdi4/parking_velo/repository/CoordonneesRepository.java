package polytechdi4.parking_velo.repository;

import polytechdi4.parking_velo.model.Coordonnees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordonneesRepository extends JpaRepository<Coordonnees, Long> {
}
