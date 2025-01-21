package com.forumhub.api.forumhubChallenge3.domain.resposta;

import com.forumhub.api.forumhubChallenge3.domain.usuario.Usuario;
import com.forumhub.api.forumhubChallenge3.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor

//tabelas e entidades do banco de dados
@Entity(name = "Resposta")
@Table(name = "respostas")
public class Resposta {

    //criando caracteristicas da resposta como
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String mensagem;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "topico")
    Topico topico;

    @Column(name = "data_criacao")
    LocalDateTime dataCriacao;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "autor", referencedColumnName = "id")
    Usuario autor;

    @Setter
    Boolean solucao;
}