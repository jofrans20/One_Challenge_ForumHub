package com.forumhub.api.forumhubChallenge3.controller.topicos.services;

import com.forumhub.api.forumhubChallenge3.domain.cursos.Cursos;
import com.forumhub.api.forumhubChallenge3.domain.cursos.CursoSNaoEncontradosException;
import com.forumhub.api.forumhubChallenge3.domain.cursos.CursosRepository;
import com.forumhub.api.forumhubChallenge3.domain.topico.TopicoCadastrandoDTO;
import com.forumhub.api.forumhubChallenge3.domain.topico.Topico;
import com.forumhub.api.forumhubChallenge3.domain.topico.TopicoStatus;
import com.forumhub.api.forumhubChallenge3.domain.topico.validacoes.cadastro.ValidandoTopicoCadastrado;
import com.forumhub.api.forumhubChallenge3.domain.usuario.Usuario;
import com.forumhub.api.forumhubChallenge3.domain.usuario.UsuarioRepository;
import com.forumhub.api.forumhubChallenge3.infra.security.SecurityContexHolderAccess;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


import java.time.LocalDateTime;
import java.util.List;

@Service

public class CadastrarTopicosService implements SecurityContexHolderAccess {
    private final UsuarioRepository usuarioRepository;
    private final CursosRepository cursosRepository;
    private final List<ValidandoTopicoCadastrado> validadores;


    @Autowired
    public CadastrarTopicosService(UsuarioRepository usuarioRepository, CursosRepository cursosRepository, List<ValidandoTopicoCadastrado> validadores) {
        this.usuarioRepository = usuarioRepository;
        this.cursosRepository = cursosRepository;
        this.validadores = validadores;
    }


    public Topico validar(@Valid TopicoCadastrandoDTO dados) {
        Optional<Cursos> cursoSearched = cursosRepository.findById(dados.idCurso());
        Usuario usuarioSearched = usuarioRepository.findById(authContextUserId).get();

        if (cursoSearched.isEmpty()) {
            throw new CursoSNaoEncontradosException();
        }


        // Responsável pela validação.Faz um exception caso apresente alguma falhe.
        validadores.forEach(v -> v.validar(dados));



        return new Topico(null, dados.titulo(), dados.mensagem(), LocalDateTime.now(), TopicoStatus.NAO_RESPONDIDO, usuarioSearched, cursoSearched.get());
    }
}
