package polytechdi4.parking_velo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import polytechdi4.parking_velo.dto.ReservationCreateDTO;
import polytechdi4.parking_velo.dto.ReservationResponseDTO;
import polytechdi4.parking_velo.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO create(@Valid @RequestBody ReservationCreateDTO dto) {
        return reservationService.create(dto);
    }

    @GetMapping
    public List<ReservationResponseDTO> getAll() {
        return reservationService.list();
    }

    @GetMapping("/utilisateur/{utilisateurId}/velo/{veloId}")
    public ReservationResponseDTO getById(@PathVariable Integer utilisateurId,
            @PathVariable Integer veloId) {
        return reservationService.get(utilisateurId, veloId);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<ReservationResponseDTO> getByUtilisateur(@PathVariable Integer utilisateurId) {
        return reservationService.listByUtilisateur(utilisateurId);
    }

    @GetMapping("/velo/{veloId}")
    public List<ReservationResponseDTO> getByVelo(@PathVariable Integer veloId) {
        return reservationService.listByVelo(veloId);
    }

    @PutMapping("/utilisateur/{utilisateurId}/velo/{veloId}")
    public ReservationResponseDTO update(@PathVariable Integer utilisateurId,
            @PathVariable Integer veloId,
            @Valid @RequestBody ReservationCreateDTO dto) {
        return reservationService.update(utilisateurId, veloId, dto);
    }

    @DeleteMapping("/utilisateur/{utilisateurId}/velo/{veloId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer utilisateurId,
            @PathVariable Integer veloId) {
        reservationService.delete(utilisateurId, veloId);
    }
}
