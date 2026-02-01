package polytechdi4.parking_velo.service;

import org.springframework.stereotype.Service;
import polytechdi4.parking_velo.hashPassword.HashPwd;
import polytechdi4.parking_velo.repository.UtilisateurRepository;

@Service
public class AuthService {
    private final UtilisateurRepository repo;

    public AuthService(UtilisateurRepository repo) {
        this.repo = repo;
    }

    public boolean login(String mail, String password) {

        String hashed = HashPwd.sha256(password);

        return repo.findByMail(mail)
                .map(u -> u.getPassword().equals(hashed))
                .orElse(false);
    }
}
