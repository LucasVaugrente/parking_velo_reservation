package polytechdi4.parking_velo.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

    @EmbeddedId
    private ReservationId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("utilisateurId")
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("veloId")
    @JoinColumn(name = "velo_id", nullable = false)
    private Velo velo;

    // colonne 'reservation' (quantité / nombre de places réservées)
    @Column(name = "reservation", nullable = false)
    private Integer reservation;

    public Reservation() {
    }

    public Reservation(Utilisateur u, Velo v, Integer reservation) {
        this.utilisateur = u;
        this.velo = v;
        this.id = new ReservationId(u.getId(), v.getId());
        this.reservation = reservation;
    }
}
