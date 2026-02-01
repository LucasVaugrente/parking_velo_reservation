package polytechdi4.parking_velo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import polytechdi4.parking_velo.dto.VeloCreateDTO;
import polytechdi4.parking_velo.dto.VeloResponseDTO;
import polytechdi4.parking_velo.dto.VeloUpdateDTO;
import polytechdi4.parking_velo.service.VeloService;

import java.util.List;

@RestController
@RequestMapping("/api/velos")
@RequiredArgsConstructor
public class VeloController {

    private final VeloService veloService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeloResponseDTO create(@Valid @RequestBody VeloCreateDTO dto) {
        return veloService.create(dto);
    }

    @GetMapping
    public List<VeloResponseDTO> getAll() {
        return veloService.list();
    }

    @GetMapping("/{id}")
    public VeloResponseDTO getById(@PathVariable Integer id) {
        return veloService.get(id);
    }

    @PutMapping("/{id}")
    public VeloResponseDTO update(@PathVariable Integer id, @Valid @RequestBody VeloUpdateDTO dto) {
        return veloService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        veloService.delete(id);
    }
}
