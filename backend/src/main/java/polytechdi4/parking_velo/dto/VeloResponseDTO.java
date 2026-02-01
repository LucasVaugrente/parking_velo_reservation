package polytechdi4.parking_velo.dto;

import lombok.Data;

@Data
public class VeloResponseDTO {

    private Integer id;
    private String nom;
    private Integer quantite;
    private String description;

    private Integer coordonneesId;
}
