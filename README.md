# Status build Travis CI
	
[![Build Status](https://app.travis-ci.com/MarcosLorencini/desafio-back.svg?branch=main)](https://app.travis-ci.com/MarcosLorencini/desafio-back)

Desafio Back-end - BRQ
====

## Descrição:

A BRQ possui a necessidade de disponibilizar uma api restfull para realizar o crud de clientes e dos pacote de tarifas contratados.

## Regras de Negócio

    Deverá ser realizado o CRUD de cliente e pacote de tarifas
    O cadastro de cliente deverá ter os campos: Nome, CPF e Data de Nascimento
    O cadastro do pacote de tarifas deverá ter os campos: Nome, Valor Minimo, Valor Maximo
    Cada cliente deverá possuir 1 pacote de tarifas contratado
    Não poderá ser cadastrado mais de um cliente para o mesmo CPF

## Features

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

## Requisitos

    A API deverá ter um swagger
    Teste unitário
    Utilizar uma estrutura de dados a sua escolha para simular a base de dados em memória
    Utilizar a linguagem Java, versão 11, Maven
    Subir a aplicação utilizando o Docker
    Aplicação deverá conter um Readme contendo instruções de como realizar o build e rodar a Aplicação

## Diferencial

    No readme separe uma sessão para explicar a arquitetura da api
    Tenha em mente conceitos de SOLID e clean architecture
    Esteira de CI/CD no github (exemplo Travis CI)

## Como submeter?

Deverá ser enviado um PULL REQUEST com o seu teste.

## Como funciona?

    Fork deste repositório
    Clonar a partir do repositório que foi criada na sua conta
    Procure fazer o máximo de commits com todas as suas decisões
    Abra um Pull Request para este repositório
	
## Arquitetura
	A arquiterua do pojeto foi desenvolvido em camadas(Pack by Layer) horizontais, onde cada camada é responsável por uma função espeficifica. 
	
	Web : Camada controladores(Resource) -> Vão compor as APIs(RestFull), responsável tanto por receber requisições como por enviar a resposta ao usuário; 
	Servcie: Camada de serviço(service) -> Todos os serviços onde estão as regras de negócio, validações e o que mais for preciso.
	Data: Camada de acesso a dados(Repositoy) -> Acesso a dados
	Domain: Camada de dominio -> Informação das entidades que contem contrutores, atributos
	
	*As camadas compõe o backend, que disponibiliza a API REST para as aplicações cliente acessarem
	
## Subir a aplicação utilizando o Docker
	Utilize os seguintes comandos na pasta raiz do projeto:
	Faz o build(gera o .jar) da aplicação para gerar a imagem: docker build -t desafio-back .
	Gera o container com base na imagem gerada: docker run -p 8080:8080 desafio-back
	Acessar: http://localhost:8080/swagger-ui.html
	
## Suibr a aplicação local utilizando H2 banco de dados em memória
	Foi utilizado um banco de dados H2 que funciona em memória com um console acessível pelo browser dentro do contexto da aplicação.
	Na classe ClientePacoteTarifasApplication.java foi criado um mock de dados do cliente e pacote de tarifas, estes dados serão inclusas no H2
	no momento que subir a aplicação.
	Após subir a aplicação o acesso é feito por este endereço: http://localhost:8080/h2-console
	Preencher esta url no campo JDBC URL: jdbc:h2:mem:testdb
	User Name e Password não precisam ser preenchidas
	Clique no botão connect.
	

	


	