package polytechdi4.parking_velo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import polytechdi4.parking_velo.dto.ReservationCreateDTO;
import polytechdi4.parking_velo.dto.ReservationResponseDTO;
import polytechdi4.parking_velo.service.ReservationService;

import java.util.List;
import java.util.Map;

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

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgument(IllegalArgumentException ex) {
        return Map.of("message", ex.getMessage());
    }

}
