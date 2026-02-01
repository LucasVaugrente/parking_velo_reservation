package polytechdi4.parking_velo.service;

import polytechdi4.parking_velo.dto.CoordonneesDTO;

import java.util.List;

public interface CoordonneesService {
    List<CoordonneesDTO> findAll();
    CoordonneesDTO findById(Integer id);
    CoordonneesDTO create(CoordonneesDTO dto);
    CoordonneesDTO update(Integer id, CoordonneesDTO dto);
    void delete(Integer id);
}