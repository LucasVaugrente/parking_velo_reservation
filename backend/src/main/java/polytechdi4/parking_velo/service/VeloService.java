package polytechdi4.parking_velo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import polytechdi4.parking_velo.dto.VeloCreateDTO;
import polytechdi4.parking_velo.dto.VeloResponseDTO;
import polytechdi4.parking_velo.dto.VeloUpdateDTO;
import polytechdi4.parking_velo.exception.NotFoundException;
import polytechdi4.parking_velo.mapper.VeloMapper;
import polytechdi4.parking_velo.model.Coordonnees;
import polytechdi4.parking_velo.model.Velo;
import polytechdi4.parking_velo.repository.CoordonneesRepository;
import polytechdi4.parking_velo.repository.VeloRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VeloService {

    private final VeloRepository veloRepository;
    private final CoordonneesRepository coordonneesRepository;
    private final VeloMapper veloMapper;

    public VeloResponseDTO create(VeloCreateDTO dto) {
        Coordonnees coord = new Coordonnees();
        coord.setLatitude(dto.getLatitude());
        coord.setLongitude(dto.getLongitude());
        Coordonnees savedCoord = coordonneesRepository.save(coord);

        Velo velo = new Velo();
        velo.setNom(dto.getNom());
        velo.setQuantite(dto.getQuantite());
        velo.setDescription(dto.getDescription());
        velo.setCoordonnees(savedCoord);

        Velo saved = veloRepository.save(velo);
        return veloMapper.toResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public VeloResponseDTO get(Integer id) {
        Velo velo = veloRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Vélo " + id + " introuvable"));
        return veloMapper.toResponseDto(velo);
    }

    @Transactional(readOnly = true)
    public List<VeloResponseDTO> list() {
        return veloMapper.toResponseDtoList(veloRepository.findAll());
    }

    public VeloResponseDTO update(Integer id, VeloUpdateDTO dto) {
        Velo existing = veloRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Vélo " + id + " introuvable"));

        existing.setNom(dto.getNom());
        existing.setQuantite(dto.getQuantite());
        existing.setDescription(dto.getDescription());

        Coordonnees coord = coordonneesRepository.findById(Long.valueOf(dto.getCoordonneesId()))
                .orElseThrow(() -> new NotFoundException("Coordonnées " + dto.getCoordonneesId() + " introuvables"));

        coord.setLatitude(dto.getLatitude());
        coord.setLongitude(dto.getLongitude());

        Coordonnees savedCoord = coordonneesRepository.save(coord);
        existing.setCoordonnees(savedCoord);

        Velo saved = veloRepository.save(existing);
        return veloMapper.toResponseDto(saved);
    }

    public void delete(Integer id) {
        if (!veloRepository.existsById(Long.valueOf(id))) {
            throw new NotFoundException("Vélo " + id + " introuvable");
        }
        veloRepository.deleteById(Long.valueOf(id));
    }
}
