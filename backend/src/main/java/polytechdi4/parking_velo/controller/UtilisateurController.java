package polytechdi4.parking_velo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import polytechdi4.parking_velo.dto.UtilisateurCreateDTO;
import polytechdi4.parking_velo.dto.UtilisateurResponseDTO;
import polytechdi4.parking_velo.dto.UtilisateurUpdateDTO;
import polytechdi4.parking_velo.service.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UtilisateurResponseDTO create(@Valid @RequestBody UtilisateurCreateDTO dto) {
        return utilisateurService.create(dto);
    }

    @GetMapping
    public List<UtilisateurResponseDTO> getAll() {
        return utilisateurService.list();
    }

    @GetMapping("/{id}")
    public UtilisateurResponseDTO getById(@PathVariable Integer id) {
        return utilisateurService.get(id);
    }

    @PutMapping("/{id}")
    public UtilisateurResponseDTO update(@PathVariable Integer id,
            @Valid @RequestBody UtilisateurUpdateDTO dto) {
        return utilisateurService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        utilisateurService.delete(id);
    }
}
