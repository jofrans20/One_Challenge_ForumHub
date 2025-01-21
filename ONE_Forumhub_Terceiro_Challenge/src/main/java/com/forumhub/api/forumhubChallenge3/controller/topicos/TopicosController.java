package com.forumhub.api.forumhubChallenge3.controller.topicos;

import com.forumhub.api.forumhubChallenge3.controller.topicos.services.AtualizarTopicosService;
import com.forumhub.api.forumhubChallenge3.controller.topicos.services.CadastrarTopicosService;
import com.forumhub.api.forumhubChallenge3.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.data.domain.Pageable;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class TopicosController {
    @Autowired
    private TopicoRepository repository;

    @Autowired
    CadastrarTopicosService cadastrarTopicosService;

    @Autowired
    AtualizarTopicosService atualizarTopicosService;

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<TopicoDetalhamentoDTO> detalharTopico(@PathVariable @NotNull Long id) {
        Optional<Topico> topico = repository.findById(id);
        if (topico.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }
        return ResponseEntity.ok(new TopicoDetalhamentoDTO(topico.get()));
    }

    @GetMapping
    public Page<TopicoDetalhamentoDTO> listarTopicos(Pageable pageable) {
        Page<Topico> topicos = repository.findAll(pageable);
        return topicos.map(TopicoDetalhamentoDTO::new); // Transform each Topico into TopicoDTO
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDetalhamentoDTO> cadastrarTopico(@Valid TopicoCadastrandoDTO dados, UriComponentsBuilder uriBuilder) {
        Topico topicoCriado = cadastrarTopicosService.validar(dados);
        repository.save(topicoCriado);
        URI location = URI.create("/topicos/" + topicoCriado.getId());

        return ResponseEntity.created(location).body(new TopicoDetalhamentoDTO(topicoCriado));
    }

    @PatchMapping("{id}")
    @Transactional
    public ResponseEntity<TopicoDetalhamentoDTO> atualizarTopico(@PathVariable Long id, @Valid TopicoAtualizandoDTO dados) {
        TopicoDetalhamentoDTO topicoAtualizado = atualizarTopicosService.atualizar(id, dados);
        return ResponseEntity.ok(topicoAtualizado);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        Optional<Topico> topico = repository.findById(id);
        if (topico.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}