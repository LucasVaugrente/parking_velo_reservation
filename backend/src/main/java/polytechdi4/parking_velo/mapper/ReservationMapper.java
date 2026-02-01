package polytechdi4.parking_velo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import polytechdi4.parking_velo.dto.ReservationResponseDTO;
import polytechdi4.parking_velo.model.Reservation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "utilisateurId", source = "utilisateur.id")
    @Mapping(target = "veloId", source = "velo.id")
    @Mapping(target = "utilisateurUsername", source = "utilisateur.username")
    @Mapping(target = "veloNom", source = "velo.nom")
    ReservationResponseDTO toResponseDto(Reservation entity);

    List<ReservationResponseDTO> toResponseDtoList(List<Reservation> entities);
}