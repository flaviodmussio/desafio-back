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
