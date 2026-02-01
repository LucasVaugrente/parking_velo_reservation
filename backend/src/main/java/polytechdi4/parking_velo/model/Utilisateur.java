package polytechdi4.parking_velo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100) private String nom;
    @Column(nullable = false, length = 100) private String prenom;
    @Column(nullable = false, length = 100) private String mail;

    @JsonIgnore
    @Column(nullable = false, length = 100) private String password;

    @Column(nullable = false, length = 100) private String username;

    @OneToMany(mappedBy = "utilisateur", orphanRemoval = true)
    @JsonIgnore
    private Set<Reservation> reservations = new LinkedHashSet<>();
}
