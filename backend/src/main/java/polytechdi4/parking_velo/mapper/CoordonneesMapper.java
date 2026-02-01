package polytechdi4.parking_velo.mapper;

import org.mapstruct.*;
import polytechdi4.parking_velo.dto.CoordonneesDTO;
import polytechdi4.parking_velo.model.Coordonnees;

@Mapper(componentModel = "spring")
public interface CoordonneesMapper {
    CoordonneesDTO toDTO(Coordonnees coordonnees);

    @Mapping(target = "velos", ignore = true)
    Coordonnees toEntity(CoordonneesDTO coordonneesDTO);

    @Mapping(target = "velos", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Coordonnees coordonnees, CoordonneesDTO coordonneesDTO);

    @Mapping(target = "velos", ignore = true)
    void fullUpdate(@MappingTarget Coordonnees coordonnees, CoordonneesDTO coordonneesDTO);
}