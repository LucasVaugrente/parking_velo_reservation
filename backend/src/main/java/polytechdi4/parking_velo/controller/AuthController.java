package polytechdi4.parking_velo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import polytechdi4.parking_velo.dto.LoginRequestDTO;
import polytechdi4.parking_velo.dto.LoginResponseDTO;
import polytechdi4.parking_velo.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {
        boolean ok = authService.login(dto.getMail(), dto.getPassword());
        return new LoginResponseDTO(ok);
    }
}
