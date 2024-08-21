create database banco;

GRANT ALL PRIVILEGES ON banco.*
TO 'root'@'localhost';

USE banco;
ALTER TABLE aluno ADD COLUMN id BIGINT AUTO_INCREMENT;
CREATE TABLE aluno(

    matricula BIGINT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    idade INT NOT NULL,
    email VARCHAR(50) NOT NULL,

    PRIMARY KEY (matricula)
);