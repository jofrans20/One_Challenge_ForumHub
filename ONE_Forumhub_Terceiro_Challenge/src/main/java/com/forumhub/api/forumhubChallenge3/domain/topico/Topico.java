package com.forumhub.api.forumhubChallenge3.domain.topico;

import com.forumhub.api.forumhubChallenge3.domain.usuario.Usuario;
import com.forumhub.api.forumhubChallenge3.domain.cursos.Cursos;
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
@Entity(name = "Topico")
@Table(name = "topicos")

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String titulo;

    @Setter
    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Setter
    private TopicoStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario autor;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso")
    private Cursos cursos;
}