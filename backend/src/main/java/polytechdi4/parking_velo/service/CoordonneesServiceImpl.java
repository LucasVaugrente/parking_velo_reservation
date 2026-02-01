package polytechdi4.parking_velo.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polytechdi4.parking_velo.dto.CoordonneesDTO;
import polytechdi4.parking_velo.mapper.CoordonneesMapper;
import polytechdi4.parking_velo.model.Coordonnees;
import polytechdi4.parking_velo.repository.CoordonneesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordonneesServiceImpl implements CoordonneesService {

    private final CoordonneesRepository repository;
    private final CoordonneesMapper mapper;

    @Override
    public List<CoordonneesDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public CoordonneesDTO findById(Integer id) {
        Coordonnees c = repository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Coordonnees not found"));
        return mapper.toDTO(c);
    }

    @Override
    public CoordonneesDTO create(CoordonneesDTO dto) {
        Coordonnees entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public CoordonneesDTO update(Integer id, CoordonneesDTO dto) {
        Coordonnees entity = repository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Coordonnees not found"));

        mapper.partialUpdate(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(Long.valueOf(id));
    }
}