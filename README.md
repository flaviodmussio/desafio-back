Desafio Back-end - BRQ
====

[![Actions Status](https://github.com/GabsOda/desafio-back/workflows/build-java-application/badge.svg)](https://github.com/GabsOda/desafio-back/actions)
[![DockerHub Image](https://img.shields.io/badge/docker--image-desafio--back--app-blue)](https://hub.docker.com/r/gissamuoda/desafio-back-app)

====

## Descrição do desafio:

A BRQ possui a necessidade de disponibilizar uma api restfull para realizar o crud de clientes e dos pacote de tarifas contratados.

 ### Regras de Negócio

     Deverá ser realizado o CRUD de cliente e pacote de tarifas
     O cadastro de cliente deverá ter os campos: Nome, CPF e Data de Nascimento
     O cadastro do pacote de tarifas deverá ter os campos: Nome, Valor Minimo, Valor Maximo
     Cada cliente deverá possuir 1 pacote de tarifas contratado
     Não poderá ser cadastrado mais de um cliente para o mesmo CPF

 ### Features

     Deverá haver um endpoint para listagem de todos os clientes 
    
     Deverá haver um endpoint para listagem dos clientes, filtrando por nome, id ou cpf

     Deverá haver um endpoint para criação de um cliente

     Deverá haver um endpoint para atualização de um cliente

     Deverá haver um endpoint para exclusão de um cliente
    
    
     Deverá haver um endpoint para listagem de todos os pacotes 
    
     Deverá haver um endpoint para listagem dos pacotes, filtrando por cliente ou id

     Deverá haver um endpoint para criação de um pacote

     Deverá haver um endpoint para atualização de um pacote

     Deverá haver um endpoint para exclusão de um pacote

 ### Requisitos

     A API deverá ter um swagger
     Teste unitário
     Utilizar uma estrutura de dados a sua escolha para simular a base de dados em memória
     Utilizar a linguagem Java, versão 11, Maven
     Subir a aplicação utilizando o Docker
     Aplicação deverá conter um Readme contendo instruções de como realizar o build e rodar a Aplicação

### Diferencial

     No readme separe uma sessão para explicar a arquitetura da api
     Tenha em mente conceitos de SOLID e clean architecture
     Esteira de CI/CD no github (exemplo Travis CI)

 ### Como funciona?

     Fork deste repositório
     Clonar a partir do repositório que foi criada na sua conta
     Procure fazer o máximo de commits com todas as suas decisões
     Abra um Pull Request para o repositório principal com seu teste

# Build 
## 1. Gerando build da aplicação 

``` shell 
$ mvn clean package
```
Será utilizado o maven para realizar o build da aplicação para um executável ``.jar`` na pasta ``target/``. Com será criado a imagem docker da aplicação para que seja possível executá-la utilizando containers docker. 

## 2. Gerando imagem da aplicação 

``` shell
$ docker build -t g_issamu/desafio-back-app:0.0.1-SNAPSHOT .
```
*pode ser escolhido o nome da imagem que quiser, esse é apenas a que eu usei. 

## 3. Rodando a aplicação através do Docker

``` shell
docker run -p 8080:8080 g_issamu/desafio-back-app:0.0.1-SNAPSHOT
```
É utilizado o comando ```docker run``` passando como argumento a porta em que a aplicação estará rodando em sua máquina, por padrão é utilizado a porta em que o Tomcat utiliza para subir. 

Logo após já será possível utilizar a aplicação, podendo-se acessar o swagger presente na aplicação para realizar consultas.
Ele estará disponível na url: 
``` 
http://localhost:8080/swagger-ui.html
```

### 3.1 Console do H2
Como no projeto é utilizado o banco de dados em memória H2, é possível utilizar sua interface gráfica para visualizar os dados presente neste banco através do endpoint ```/h2-console```. 

1. na tela de login do console do H2 será necessário atualizar o campo ```JDBC URL``` para: 
    ```
    jdbc:h2:mem:mydb
    ```

2. nos demais campos pode deixar padrão, contendo como ```User Name:``` o valor ```sa``` e o campo ```Passoword:``` vazio

Com essas configuração será possível acessar o console h2 da aplicação. 
