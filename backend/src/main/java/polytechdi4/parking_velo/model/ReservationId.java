package polytechdi4.parking_velo.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReservationId implements Serializable {
    private Integer utilisateurId;
    private Integer veloId;

    public ReservationId() {}
    public ReservationId(Integer utilisateurId, Integer veloId) {
        this.utilisateurId = utilisateurId; this.veloId = veloId;
    }

    public Integer getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(Integer utilisateurId) { this.utilisateurId = utilisateurId; }
    public Integer getVeloId() { return veloId; }
    public void setVeloId(Integer veloId) { this.veloId = veloId; }

    @Override public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof ReservationId that)) return false;
        return Objects.equals(utilisateurId, that.utilisateurId)
                && Objects.equals(veloId, that.veloId);
    }
    @Override public int hashCode(){ return Objects.hash(utilisateurId, veloId); }
}
