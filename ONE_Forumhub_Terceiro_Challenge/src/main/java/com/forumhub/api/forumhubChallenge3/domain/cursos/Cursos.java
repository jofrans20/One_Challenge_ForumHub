package com.forumhub.api.forumhubChallenge3.domain.cursos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

//tabelas e entidades do banco de dados
@Entity(name = "Curso")
@Table(name = "cursos")


public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nome;

    @Enumerated(EnumType.STRING)
    CursosCategoria categoria;
}
