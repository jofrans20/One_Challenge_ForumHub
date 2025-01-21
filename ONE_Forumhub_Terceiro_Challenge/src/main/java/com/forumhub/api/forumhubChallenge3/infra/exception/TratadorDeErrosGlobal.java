package com.forumhub.api.forumhubChallenge3.infra.exception;

import com.forumhub.api.forumhubChallenge3.domain.resposta.RespostaNaoEncontradaException;
import com.forumhub.api.forumhubChallenge3.domain.registro.EmailJaExistenteException;
import com.forumhub.api.forumhubChallenge3.domain.cursos.CursoSNaoEncontradosException;
import com.forumhub.api.forumhubChallenge3.domain.topico.TopicoNaoEncontradoException;
import com.forumhub.api.forumhubChallenge3.domain.topico.validacoes.cadastro.TopicoDuplicadoException;
import com.forumhub.api.forumhubChallenge3.domain.usuario.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErrosGlobal {
    @ExceptionHandler(TopicoDuplicadoException.class)
    public ResponseEntity<TopicoDuplicadoException> tratarErroTopicoDuplicado(TopicoDuplicadoException e) {
        return ResponseEntity.badRequest().body(e);
    }


    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<UsuarioNaoEncontradoException> tratarErroUsuarioNaoEncontrado(UsuarioNaoEncontradoException e) {
        return ResponseEntity.badRequest().body(e);
    }


    @ExceptionHandler(CursoSNaoEncontradosException.class)
    public ResponseEntity<CursoSNaoEncontradosException> tratarErroCursoNaoEncontrado(CursoSNaoEncontradosException e) {
        return ResponseEntity.badRequest().body(e);
    }


    @ExceptionHandler(TopicoNaoEncontradoException.class)
    public ResponseEntity<TopicoNaoEncontradoException> tratarErroTopicoNaoEncontrado(TopicoNaoEncontradoException e) {
        return ResponseEntity.badRequest().body(e);
    }


    @ExceptionHandler(EmailJaExistenteException.class)
    public ResponseEntity<EmailJaExistenteException> tratarErroEmailJaExistente(EmailJaExistenteException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(e);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AccessDeniedException> tratarErroAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(e);
    }


    @ExceptionHandler(RespostaNaoEncontradaException.class)
    public ResponseEntity<RespostaNaoEncontradaException> tratarRespostaNaoEncontradaException(RespostaNaoEncontradaException e) {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<String> mensagensDeErro = e.getBindingResult().getFieldErrors().stream()
                .map(erro -> String.format("Campo '%s': %s", erro.getField(), erro.getDefaultMessage()))
                .toList();

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Erro de validação");
        resposta.put("erros", mensagensDeErro);

        return ResponseEntity.badRequest().body(resposta);
    }

}
