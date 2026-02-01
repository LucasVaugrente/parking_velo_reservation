// src/main/java/polytechdi4/parking_velo/dto/UtilisateurUpdateDTO.java
package polytechdi4.parking_velo.dto;

import lombok.Data;

@Data
public class UtilisateurUpdateDTO {
    private String nom;
    private String prenom;
    private String mail;
    private String username;
    private String password;
}
