package polytechdi4.parking_velo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polytechdi4.parking_velo.model.Reservation;
import polytechdi4.parking_velo.model.ReservationId;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
    List<Reservation> findByUtilisateur_Id(Integer utilisateurId);
    List<Reservation> findByVelo_Id(Integer veloId);
}