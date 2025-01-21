package com.forumhub.api.forumhubChallenge3.controller.resposta;

import com.forumhub.api.forumhubChallenge3.domain.resposta.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/resposta")
public class RespostaController {
    private final RespostaService respostaService;
    private final RespostaRepository respostaRepository;

    @Autowired
    public RespostaController(RespostaService respostaService, RespostaRepository respostaRepository) {
        this.respostaService = respostaService;
        this.respostaRepository = respostaRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<List<RespostaDetalhadaDTO>> buscarRespostas(@PathVariable Long id) {
        return ResponseEntity.ok(respostaService.buscarRespostas(id));
    }

    @PostMapping
    public ResponseEntity<RespostaDetalhadaDTO> cadastrarResposta(@Valid RespostaDTO resposta, UriComponentsBuilder uriBuilder) {
        Resposta respostaCadastrada =  respostaService.cadastrarResposta(resposta);
        URI location = URI.create("/resposta/" + respostaCadastrada.getId());


        return ResponseEntity.created(location).body(new RespostaDetalhadaDTO(respostaCadastrada));
    }
    @PatchMapping("{id}")
    public ResponseEntity<RespostaDetalhadaDTO> atualizarResposta(@PathVariable Long id, @Valid RespostaAtualizandoDTO resposta) {
        RespostaDetalhadaDTO respostaAtualizada = respostaService.atualizarResposta(resposta, id);

        return ResponseEntity.ok(respostaAtualizada);
    }

    @PatchMapping("{id}/solucao")
    public ResponseEntity<Void> marcarRespostaComoSolucao(@PathVariable long id, boolean isSolucao) {
        //Podem haver múltiplas soluções.
        //Não será possível desmarcar uma opção como solução.
        respostaService.marcarRespostaComoSolucao(id, true);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarResposta(@PathVariable Long id) {
        respostaService.deletarResposta(id);
        return ResponseEntity.noContent().build();
    }
}
