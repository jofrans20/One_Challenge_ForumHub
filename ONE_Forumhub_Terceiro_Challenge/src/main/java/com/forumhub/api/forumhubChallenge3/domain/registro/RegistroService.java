package com.forumhub.api.forumhubChallenge3.domain.registro;

import com.forumhub.api.forumhubChallenge3.domain.usuario.Usuario;
import com.forumhub.api.forumhubChallenge3.domain.usuario.UsuarioRepository;
import com.forumhub.api.forumhubChallenge3.domain.usuario.UsuarioStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public RegistroService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //encripta a senha
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public void registrarUsuario(@Valid RegistroDTO usuario) {
        String encodedPassword = encoder.encode(usuario.senha());

    //verifica se o email e senha do usuario ja foi registrado
        try {
            verificarEmailJaRegistrado(usuario.email());
            usuarioRepository.save(new Usuario(null, usuario.nome(), usuario.email(), encodedPassword, UsuarioStatus.ATIVO));
        }


        catch (EmailJaExistenteException e) {
            throw new EmailJaExistenteException();
        }


        catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }



    public void verificarEmailJaRegistrado(String email) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new EmailJaExistenteException();
        }
    }
}
