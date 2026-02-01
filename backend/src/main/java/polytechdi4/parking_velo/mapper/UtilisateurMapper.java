package polytechdi4.parking_velo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import polytechdi4.parking_velo.dto.UtilisateurCreateDTO;
import polytechdi4.parking_velo.dto.UtilisateurResponseDTO;
import polytechdi4.parking_velo.model.Utilisateur;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurResponseDTO toResponseDto(Utilisateur entity);

    List<UtilisateurResponseDTO> toResponseDtoList(List<Utilisateur> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "password", source = "password") // Add this line
    Utilisateur toEntity(UtilisateurCreateDTO dto);
}
