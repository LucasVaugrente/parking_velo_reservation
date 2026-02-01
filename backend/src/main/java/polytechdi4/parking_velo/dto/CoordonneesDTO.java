package polytechdi4.parking_velo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordonneesDTO {
    private Integer id;
    private String latitude;
    private String longitude;

}