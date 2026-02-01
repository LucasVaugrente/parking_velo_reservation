package polytechdi4.parking_velo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "coordonnees")
public class Coordonnees {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100) private String latitude;
    @Column(nullable = false, length = 100) private String longitude;

    @OneToMany(mappedBy = "coordonnees")
    private Set<Velo> velos = new LinkedHashSet<>();
}
