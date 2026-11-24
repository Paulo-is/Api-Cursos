# API de Cursos

## Sobre o projeto

Este projeto consiste no desenvolvimento de uma API REST para gerenciamento de cursos, utilizando Java e Spring Boot.

A aplicação permite cadastrar, consultar, atualizar e excluir cursos, armazenando os dados em um banco de dados PostgreSQL.

O projeto foi desenvolvido como atividade acadêmica da disciplina de Ciência da Computação, contemplando testes automatizados, execução da aplicação em JAR, conteinerização com Docker e monitoramento com Spring Boot Actuator.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4.1.1
- Spring Data JPA
- PostgreSQL 18
- Maven
- JUnit 5
- Mockito
- MockMvc
- Docker
- Docker Compose
- Spring Boot Actuator

## Funcionalidades

A API disponibiliza as seguintes operações:

| Método | Endpoint | Funcionalidade |
|---|---|---|
| GET | `/cursos` | Listar todos os cursos |
| GET | `/cursos/{id}` | Buscar curso por ID |
| POST | `/cursos` | Cadastrar curso |
| PUT | `/cursos/{id}` | Atualizar curso |
| DELETE | `/cursos/{id}` | Excluir curso |

Cada curso possui os seguintes atributos:

- ID
- Nome
- Carga horária
- Descrição
- Modalidade

## Organização do projeto

A aplicação utiliza a separação de responsabilidades:

- **Controller:** recebe as requisições HTTP.
- **Service:** contém as regras de negócio.
- **Repository:** realiza o acesso aos dados.
- **Model:** representa a entidade Curso.

## Testes automatizados

Foram utilizados JUnit e Mockito para testar as regras de negócio da camada Service.

Também foram desenvolvidos testes de Controller utilizando MockMvc para verificar as respostas HTTP.

Os testes contemplam cenários de sucesso e situações em que o curso não é encontrado.

## Execução com Docker

O projeto possui um Dockerfile para construir a imagem da API e um arquivo Docker Compose para executar a aplicação junto ao PostgreSQL.

O banco de dados utiliza volume persistente para preservar os dados.

Para iniciar os containers:

```bash
docker compose up --build -d
```

Para verificar os containers:

```bash
docker compose ps
```

Para encerrar:

```bash
docker compose down
```

## Execução do JAR

Para gerar o arquivo executável:

```bash
./mvnw clean package
```

No Windows:

```powershell
.\mvnw.cmd clean package
```

Para executar:

```bash
java -jar target/Api-Cursos-0.0.1-SNAPSHOT.jar
```

## Monitoramento

A aplicação utiliza Spring Boot Actuator para monitoramento.

Endpoint de saúde:

```text
http://localhost:8080/actuator/health
```

O status `UP` indica que a aplicação está saudável.

## Banco de dados

O projeto utiliza PostgreSQL com Spring Data JPA para persistência dos dados.

As configurações de acesso são fornecidas por propriedades e variáveis de ambiente, evitando deixar credenciais diretamente no repositório.

## Autor

Paulo Israel estudante de Ciência da Computação — UNINASSAU.