
## se estiver usando o docker ao invés do mysql instalado direto no PC, abra outra janela do terminal
## sudo docker exec -it timesheet_mysql bash -l
## mysql -uroot -p
## senha: root



### DROP DATABASE IF EXISTS timesheet;

## \! clear

CREATE DATABASE IF NOT EXISTS timesheet
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE timesheet;


##usuario
##tipo INTEGER,
##empresa_id BIGINT NOT NULL,
##FOREIGN KEY (empresa_id) REFERENCES empresas(id),

CREATE TABLE IF NOT EXISTS timesheet.usuario (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,    
    nome VARCHAR(255),
    email VARCHAR(255),
    senha VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS timesheet.empresa (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    cnpj VARCHAR(255),
    razao_social VARCHAR(255),
    usuario_id BIGINT UNSIGNED,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE timesheet.usuario ADD empresa_id BIGINT UNSIGNED;
ALTER TABLE timesheet.usuario ADD CONSTRAINT fk_empresa_id FOREIGN KEY (empresa_id) REFERENCES empresa(id);

CREATE TABLE IF NOT EXISTS timesheet.folha_ponto (
    usuario_id BIGINT UNSIGNED PRIMARY KEY,
    data_ponto DATE NOT NULL,
    razao_social VARCHAR(255),
    hora_entrada TIME,
    hora_fim_almoco TIME,
    hora_inicio_almoco TIME,
    hora_saida TIME,
    foreign key (usuario_id) references usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
  

CREATE TABLE IF NOT EXISTS timesheet.autorizacao (
	id BIGINT UNSIGNED NOT NULL PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS timesheet.usuario_autorizacao (
	id_usuario BIGINT UNSIGNED NOT NULL,
	id_autorizacao BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_autorizacao) REFERENCES autorizacao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


## Senha: admin
INSERT INTO timesheet.usuario (id, nome, email, senha)
    values (1, 'Administrador', 'admin@gmail.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');

## Senha: guilherme
INSERT INTO timesheet.usuario (id, nome, email, senha)
    values (2, 'Guilherme Dantas', 'guilherme@gmail.com', '$2a$10$GWk8v9s1R5xIzQpat6zF/eTVtaI92OrQkP8pYOlfo76m1G8YPqosO');
    
## Senha: israel
INSERT INTO timesheet.usuario (id, nome, email, senha)
    values (3, 'Israel Ferreira', 'israel@gmail.com', '$2a$10$9pB.cNnrF9uQTeySsURlS.BjcNVSuUogwom1WrH4DIe2H7gGJb1Yq');


INSERT INTO timesheet.autorizacao (id, descricao) values (1, 'ROLE_ADMINISTRADOR');
INSERT INTO timesheet.autorizacao (id, descricao) values (2, 'ROLE_FUNCIONARIO');

## Administrador - 'ROLE_ADMINISTRADOR'
INSERT INTO timesheet.usuario_autorizacao (id_usuario, id_autorizacao) values (1, 1);

## Guilherme - 'ROLE_FUNCIONARIO'
INSERT INTO timesheet.usuario_autorizacao (id_usuario, id_autorizacao) values (2, 2);

## Israel - 'ROLE_FUNCIONARIO'
INSERT INTO timesheet.usuario_autorizacao (id_usuario, id_autorizacao) values (3, 2);

INSERT INTO  timesheet.empresa (cnpj, razao_social, usuario_id) VALUES ('78.001.298/0001-41', 'Empresa Test', 3);

