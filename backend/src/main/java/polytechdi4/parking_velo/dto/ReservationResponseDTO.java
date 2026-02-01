package polytechdi4.parking_velo.dto;

import lombok.Data;

@Data
public class ReservationResponseDTO {
    private Integer utilisateurId;
    private String utilisateurUsername;

    private Integer veloId;
    private String veloNom;

    private Integer reservation;
}
