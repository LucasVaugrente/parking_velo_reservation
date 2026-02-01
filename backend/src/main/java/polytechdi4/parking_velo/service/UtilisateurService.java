package polytechdi4.parking_velo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import polytechdi4.parking_velo.dto.UtilisateurCreateDTO;
import polytechdi4.parking_velo.dto.UtilisateurResponseDTO;
import polytechdi4.parking_velo.dto.UtilisateurUpdateDTO;
import polytechdi4.parking_velo.exception.ConflictException;
import polytechdi4.parking_velo.exception.NotFoundException;
import polytechdi4.parking_velo.hashPassword.HashPwd;
import polytechdi4.parking_velo.mapper.UtilisateurMapper;
import polytechdi4.parking_velo.model.Utilisateur;
import polytechdi4.parking_velo.repository.UtilisateurRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UtilisateurService {

    private final UtilisateurRepository repo;
    private final UtilisateurMapper mapper;

    public UtilisateurResponseDTO create(UtilisateurCreateDTO dto) {
        if (repo.existsByMail(dto.getMail())) {
            throw new ConflictException("Email déjà utilisé : " + dto.getMail());
        }
        if (repo.existsByUsername(dto.getUsername())) {
            throw new ConflictException("Username déjà utilisé : " + dto.getUsername());
        }

        Utilisateur entity = mapper.toEntity(dto);

        entity.setPassword(HashPwd.sha256(dto.getPassword()));
        Utilisateur saved = repo.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public UtilisateurResponseDTO get(Integer id) {
        Utilisateur u = repo.findById(Integer.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Utilisateur " + id + " introuvable"));
        return mapper.toResponseDto(u);
    }

    @Transactional(readOnly = true)
    public List<UtilisateurResponseDTO> list() {
        return mapper.toResponseDtoList(repo.findAll());
    }

    public UtilisateurResponseDTO update(Integer id, UtilisateurUpdateDTO dto) {
        Utilisateur existing = repo.findById(Integer.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Utilisateur " + id + " introuvable"));

        if (repo.existsByMailAndIdNot(dto.getMail(), id)) {
            throw new ConflictException("Email déjà utilisé : " + dto.getMail());
        }
        if (repo.existsByUsernameAndIdNot(dto.getUsername(), id)) {
            throw new ConflictException("Username déjà utilisé : " + dto.getUsername());
        }

        existing.setNom(dto.getNom());
        existing.setPrenom(dto.getPrenom());
        existing.setMail(dto.getMail());
        existing.setUsername(dto.getUsername());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existing.setPassword(HashPwd.sha256(dto.getPassword()));
        }

        Utilisateur saved = repo.save(existing);
        return mapper.toResponseDto(saved);
    }

    public void delete(Integer id) {
        if (!repo.existsById(Integer.valueOf(id))) {
            throw new NotFoundException("Utilisateur " + id + " introuvable");
        }
        repo.deleteById(Integer.valueOf(id));
    }
}
