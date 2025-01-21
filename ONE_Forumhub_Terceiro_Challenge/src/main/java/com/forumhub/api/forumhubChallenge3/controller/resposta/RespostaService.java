package com.forumhub.api.forumhubChallenge3.controller.resposta;

import com.forumhub.api.forumhubChallenge3.domain.resposta.*;
import com.forumhub.api.forumhubChallenge3.domain.topico.Topico;
import com.forumhub.api.forumhubChallenge3.domain.topico.TopicoNaoEncontradoException;
import com.forumhub.api.forumhubChallenge3.domain.topico.TopicoRepository;
import com.forumhub.api.forumhubChallenge3.domain.topico.TopicoStatus;
import com.forumhub.api.forumhubChallenge3.domain.usuario.Usuario;
import com.forumhub.api.forumhubChallenge3.domain.usuario.UsuarioRepository;
import com.forumhub.api.forumhubChallenge3.infra.security.OwnerValidators.RespostaOwnerValidator;
import com.forumhub.api.forumhubChallenge3.infra.security.SecurityContexHolderAccess;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaService implements SecurityContexHolderAccess {
    private final TopicoRepository topicoRepository;
    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final RespostaOwnerValidator validator;

    @Autowired
    public RespostaService(TopicoRepository topicoRepository, RespostaRepository respostaRepository, UsuarioRepository usuarioRepository, RespostaOwnerValidator validator) {
        this.topicoRepository = topicoRepository;
        this.respostaRepository = respostaRepository;
        this.usuarioRepository = usuarioRepository;
        this.validator = validator;
    }



    public List<RespostaDetalhadaDTO> buscarRespostas(Long topicoId) {
        //busca se o topico original existe
        Optional<Topico> topicoOrigem = topicoRepository.findById(topicoId);
        if (topicoOrigem.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }
        return respostaRepository.findAllByTopicoId(topicoId);
    }



    public Resposta cadastrarResposta(RespostaDTO respostaDTO) {
        //mesma coisa do anterior
        Optional<Topico> topicoOrigem = topicoRepository.findById(respostaDTO.idTopico());
        Usuario respostaAutor = usuarioRepository.findById(authContextUserId).get();
        if (topicoOrigem.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }



        //Checando se o tópico está como "não respondido" e alterando seu status.
        if (topicoOrigem.get().getStatus().equals(TopicoStatus.NAO_RESPONDIDO)) {
            topicoOrigem.get().setStatus(TopicoStatus.RESPONDIDO);
        }



        //salva nova Resposta
        Resposta resposta = new Resposta(null, respostaDTO.mensagem(), topicoOrigem.get(), LocalDateTime.now(), respostaAutor, false);
        respostaRepository.save(resposta);
        topicoRepository.save(topicoOrigem.get());
        return resposta;
    }



    public void deletarResposta(Long idResposta) {
        Optional<Resposta> resposta = respostaRepository.findById(idResposta);
        if (resposta.isEmpty()) {
            throw new RespostaNaoEncontradaException();
        }
        //checa se o usuario logado é o mesmo autor da resposta
        validator.validate(resposta.get());
        respostaRepository.deleteById(idResposta);
    }



    public RespostaDetalhadaDTO atualizarResposta(@Valid RespostaAtualizandoDTO resposta, Long idResposta) {
        Optional<Resposta> r = respostaRepository.findById(idResposta);
        if (r.isEmpty()) {
            throw new RespostaNaoEncontradaException();
        }
        //mesmo do anterior
        validator.validate(r.get());
        Resposta respostaAtualizada = new Resposta(null, resposta.mensagem(), r.get().getTopico(), r.get().getDataCriacao(), r.get().getAutor(), r.get().getSolucao());
        respostaRepository.save(respostaAtualizada);
        return new RespostaDetalhadaDTO(respostaAtualizada);
    }

    public void marcarRespostaComoSolucao(long idResposta, boolean isSolucao) {
        Optional<Resposta> r = respostaRepository.findById(idResposta);
        if (r.isEmpty()) {
            throw new RespostaNaoEncontradaException();
        }
        //de novo checa se o usuario é o dono da resposta
        validator.validate(r.get());
        //Marcando resposta como solução
        r.get().setSolucao(isSolucao);
        //Alterando status do tópico para resposta encontrada
        r.get().getTopico().setStatus(TopicoStatus.RESPOSTA_ENCONTRADA);
        respostaRepository.save(r.get());
        topicoRepository.save(r.get().getTopico());
    }
}
