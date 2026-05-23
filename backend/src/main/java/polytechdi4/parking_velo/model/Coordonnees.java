package polytechdi4.parking_velo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "coordonnees")
public class Coordonnees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String latitude;
    @Column(nullable = false, length = 100)
    private String longitude;

    @OneToMany(mappedBy = "coordonnees")
    private Set<Velo> velos = new LinkedHashSet<>();
}
