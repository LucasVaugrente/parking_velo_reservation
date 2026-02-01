package polytechdi4.parking_velo.repository;

import polytechdi4.parking_velo.model.Velo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeloRepository extends JpaRepository<Velo, Long> {
}
