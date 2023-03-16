# User Register Fullstack - Backend Log
Projeto Backend feito em Java 11 com o CRUD que gerencia logs num banco de dados MYSQL, fica escutando a mensageria Active MQ do microserviço User Register Fullstack - Backend para fazer a gravação dos logs.

# Gerar imagem no Docker
Necessário descomentar a primeira linha no application.properties para o maven utilizar as configurações do perfil docker.

No momento é necessário descomentar a variavel brokerUrl no JMSConfig.java pois a porta tcp deve ser outro valor e a anotação @Value do spring não funcionou nesse caso.

Após isso gerar a imagem e utilizar a porta 9192:9192 para executar o container.