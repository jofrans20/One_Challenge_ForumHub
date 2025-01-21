package com.forumhub.api.forumhubChallenge3.domain.topico.validacoes.cadastro;

import com.forumhub.api.forumhubChallenge3.domain.topico.TopicoCadastrandoDTO;
import com.forumhub.api.forumhubChallenge3.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidandoTopicoUnico implements ValidandoTopicoCadastrado {
    private final TopicoRepository topicRepository;

    @Autowired
    public ValidandoTopicoUnico(TopicoRepository topicRepository) {
        this.topicRepository = topicRepository;
    }
    
    public void validar(TopicoCadastrandoDTO topico) {
        var duplicadoSearch = topicRepository.findByTituloAndMensagem(topico.titulo(), topico.mensagem());
        if (duplicadoSearch.isPresent() && !duplicadoSearch.get().isEmpty()) {
            throw new TopicoDuplicadoException("Já existe um tópico com o mesmo título e mensagem. Tente utilizar um título ou mensagem diferentes.");
        }
    }
}
