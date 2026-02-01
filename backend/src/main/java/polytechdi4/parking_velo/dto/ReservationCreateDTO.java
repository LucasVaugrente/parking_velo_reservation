package polytechdi4.parking_velo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationCreateDTO {

    @NotNull
    private Integer utilisateurId;

    @NotNull
    private Integer veloId;

    @NotNull
    private Integer reservation;
}
