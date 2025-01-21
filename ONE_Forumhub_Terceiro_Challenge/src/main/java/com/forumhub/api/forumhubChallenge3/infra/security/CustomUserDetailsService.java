package com.forumhub.api.forumhubChallenge3.infra.security;

import com.forumhub.api.forumhubChallenge3.domain.usuario.Usuario;
import com.forumhub.api.forumhubChallenge3.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Usuario> usuario= usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            return new User(
                    usuario.get().getEmail(),
                    usuario.get().getSenha(),
                    Collections.emptyList() // No authorities roles are being used.
            );
        }
        throw new UsernameNotFoundException("o Email não foi encontrado.");
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}
