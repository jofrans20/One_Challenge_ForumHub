package com.forumhub.api.forumhubChallenge3.domain.perfil;

import com.forumhub.api.forumhubChallenge3.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

//tabelas e entidades do banco de dados
@Entity(name = "Perfil")
@Table(name = "perfis")


public class Perfil {

    //criando caracteristicas do perfil e seu nome que sera usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @NotBlank
    String nome;


    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "usuario")
    Usuario usuario;
}