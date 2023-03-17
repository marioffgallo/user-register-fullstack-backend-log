# User Register Fullstack - Backend Log
Projeto Backend feito em Java 11 com o CRUD que gerencia logs num banco de dados MYSQL, fica escutando a mensageria Active MQ do microserviço User Register Fullstack - Backend para fazer a gravação dos logs.

# Gerar imagem no Docker
Necessário descomentar a primeira linha no application.properties para o maven utilizar as configurações do perfil docker.

No momento é necessário descomentar a variavel brokerUrl no JMSConfig.java pois a porta tcp deve ser outro valor e a anotação @Value do spring não funcionou nesse caso.

Após isso gerar a imagem com o comando:

docker build -t user-register-fullstack-backend-log .

Depois integrar o container no network do MySQL e utilizar a porta 9192:9192 para executar o container. O comando abaixo executa tudo isso de uma vez:

docker run --network springboot-mysql-net --name user-register-backend-log -p 9192:9192 user-register-fullstack-backend-log

# Swagger
Acessar url:
http://localhost:9192/swagger-ui/

# Javadoc
Documentação feita em javadoc, usar o comando mvn javadoc:javadoc para gerar na pasta target\sites

# MySQL no Docker

Passos para criar o banco de dados, o network e as tabelas do MySQL no docker.

------------------------------------------------------------------------

1 -Criar MySQL no Docker

docker pull mysql

------------------------------------------------------------------------

2- Criar Network no Docker

docker network create springboot-mysql-net

------------------------------------------------------------------------

3 - Criar Container do DB do MySQL

docker run --name mysqldb --network springboot-mysql-net -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=users_db -d mysql

-------------------------------------------------------------------------

4 - Acessar MYSQL Docker

docker exec -it mysqldb bash
mysql -u root -p
Senha: root

5 - Após acessar o terminal bash

CREATE TABLE id_log_seq(
next_val BIGINT
);

INSERT id_log_seq SET next_val=1;

CREATE TABLE id_user_seq(
next_val BIGINT
);

INSERT id_user_seq SET next_val=1;

CREATE TABLE logs(
id INT NOT NULL,
action VARCHAR(255),
payload VARCHAR(255),
date DATETIME
);

CREATE TABLE users(
id INT NOT NULL,
age INT NOT NULL,
email VARCHAR(255),
name VARCHAR(255),
birth_date DATETIME
);