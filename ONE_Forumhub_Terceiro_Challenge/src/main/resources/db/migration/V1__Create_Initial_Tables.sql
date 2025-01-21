CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    titulo TEXT NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    status VARCHAR(100) NOT NULL,
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL
);

CREATE TABLE respostas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    mensagem TEXT NOT NULL,
    topico BIGINT NOT NULL,
    data_criacao DATETIME NOT NULL,
    autor BIGINT NOT NULL,
    solucao TINYINT(1) NOT NULL
);

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha TEXT NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE perfis (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome VARCHAR(150) NOT NULL,
    usuario BIGINT NOT NULL
);

CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome VARCHAR(150) NOT NULL,
    categoria VARCHAR(100) NOT NULL
);

ALTER TABLE topicos
    ADD CONSTRAINT FK_topicos_autor FOREIGN KEY (autor) REFERENCES usuarios(id);

ALTER TABLE topicos
    ADD CONSTRAINT FK_topicos_curso FOREIGN KEY (curso) REFERENCES cursos(id);

ALTER TABLE respostas
    ADD CONSTRAINT FK_respostas_topico FOREIGN KEY (topico) REFERENCES topicos(id);

ALTER TABLE respostas
    ADD CONSTRAINT FK_respostas_autor FOREIGN KEY (autor) REFERENCES usuarios(id);

ALTER TABLE perfis
    ADD CONSTRAINT  FK_perfis_usuario FOREIGN KEY (usuario) REFERENCES usuarios(id);

