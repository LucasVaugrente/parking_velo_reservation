package polytechdi4.parking_velo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import polytechdi4.parking_velo.dto.VeloResponseDTO;
import polytechdi4.parking_velo.model.Velo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeloMapper {

    @Mapping(target = "coordonneesId", source = "coordonnees.id")
    VeloResponseDTO toResponseDto(Velo entity);

    List<VeloResponseDTO> toResponseDtoList(List<Velo> entities);
}
