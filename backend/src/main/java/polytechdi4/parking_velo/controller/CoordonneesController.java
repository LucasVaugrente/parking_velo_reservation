package polytechdi4.parking_velo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polytechdi4.parking_velo.dto.CoordonneesDTO;
import polytechdi4.parking_velo.service.CoordonneesService;

import java.util.List;

@RestController
@RequestMapping("/api/coordonnees")
@RequiredArgsConstructor
public class CoordonneesController {

    private final CoordonneesService service;

    @GetMapping
    public List<CoordonneesDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CoordonneesDTO getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public CoordonneesDTO create(@RequestBody CoordonneesDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CoordonneesDTO update(@PathVariable Integer id, @RequestBody CoordonneesDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}