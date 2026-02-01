package polytechdi4.parking_velo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VeloUpdateDTO {
    @NotBlank
    private String nom;
    @NotNull
    private Integer quantite;
    private String description;
    @NotNull
    private Integer coordonneesId;
    @NotBlank
    private String latitude;
    @NotBlank
    private String longitude;
}
