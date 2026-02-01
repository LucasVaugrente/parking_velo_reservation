package polytechdi4.parking_velo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtilisateurCreateDTO {
    @NotBlank private String nom;
    @NotBlank private String prenom;
    @Email @NotBlank private String mail;
    @NotBlank private String username;
    @NotBlank private String password;
}
