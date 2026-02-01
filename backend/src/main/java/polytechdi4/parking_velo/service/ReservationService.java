package polytechdi4.parking_velo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import polytechdi4.parking_velo.dto.ReservationCreateDTO;
import polytechdi4.parking_velo.dto.ReservationResponseDTO;
import polytechdi4.parking_velo.exception.NotFoundException;
import polytechdi4.parking_velo.mapper.ReservationMapper;
import polytechdi4.parking_velo.model.Reservation;
import polytechdi4.parking_velo.model.ReservationId;
import polytechdi4.parking_velo.model.Utilisateur;
import polytechdi4.parking_velo.model.Velo;
import polytechdi4.parking_velo.repository.ReservationRepository;
import polytechdi4.parking_velo.repository.UtilisateurRepository;
import polytechdi4.parking_velo.repository.VeloRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final VeloRepository veloRepository;
    private final ReservationMapper reservationMapper;

    public ReservationResponseDTO create(ReservationCreateDTO dto) {
        Utilisateur u = utilisateurRepository.findById(Integer.valueOf(dto.getUtilisateurId()))
                .orElseThrow(() -> new NotFoundException("Utilisateur " + dto.getUtilisateurId() + " introuvable"));

        Velo v = veloRepository.findById(Long.valueOf(dto.getVeloId()))
                .orElseThrow(() -> new NotFoundException("Vélo " + dto.getVeloId() + " introuvable"));

        ReservationId id = new ReservationId(u.getId(), v.getId());

        if (reservationRepository.existsById(id)) {
            throw new IllegalArgumentException("Une réservation existe déjà pour l'utilisateur "
                    + dto.getUtilisateurId() + " et le vélo " + dto.getVeloId());
        }

        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setUtilisateur(u);
        reservation.setVelo(v);
        reservation.setReservation(dto.getReservation());

        Reservation saved = reservationRepository.save(reservation);
        return reservationMapper.toResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public ReservationResponseDTO get(Integer utilisateurId, Integer veloId) {
        ReservationId id = new ReservationId(utilisateurId, veloId);
        Reservation res = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Réservation introuvable pour utilisateur " + utilisateurId + " et vélo " + veloId));
        return reservationMapper.toResponseDto(res);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> list() {
        return reservationMapper.toResponseDtoList(reservationRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> listByUtilisateur(Integer utilisateurId) {
        return reservationMapper.toResponseDtoList(
                reservationRepository.findByUtilisateur_Id(utilisateurId)
        );
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> listByVelo(Integer veloId) {
        return reservationMapper.toResponseDtoList(
                reservationRepository.findByVelo_Id(veloId)
        );
    }

    public ReservationResponseDTO update(Integer utilisateurId, Integer veloId, ReservationCreateDTO dto) {
        ReservationId id = new ReservationId(utilisateurId, veloId);
        Reservation existing = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Réservation introuvable pour utilisateur " + utilisateurId + " et vélo " + veloId));

        existing.setReservation(dto.getReservation());

        Reservation saved = reservationRepository.save(existing);
        return reservationMapper.toResponseDto(saved);
    }

    public void delete(Integer utilisateurId, Integer veloId) {
        ReservationId id = new ReservationId(utilisateurId, veloId);
        if (!reservationRepository.existsById(id)) {
            throw new NotFoundException(
                    "Réservation introuvable pour utilisateur " + utilisateurId + " et vélo " + veloId);
        }
        reservationRepository.deleteById(id);
    }
}
