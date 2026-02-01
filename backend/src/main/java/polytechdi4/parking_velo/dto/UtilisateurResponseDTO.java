package polytechdi4.parking_velo.dto;

import lombok.Data;

@Data
public class UtilisateurResponseDTO {
    private Integer id;
    private String nom;
    private String prenom;
    private String mail;
    private String username;
}
