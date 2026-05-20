# TaskBoard API - Sistema de Gestão de Tarefas

O **TaskBoard API** é o back-end de um sistema onde equipes podem gerenciar projetos, criar tarefas associadas a esses projetos e acompanhar o status das atividades.

## Tecnologias Utilizadas

* **Java 17**

* **Spring Boot 3** (Web, Validation)

* **Spring Data JPA**

* **MySQL**

* **Swagger (OpenAPI 3)** para documentação

* **JUnit 5 & Mockito** para testes automatizados

* **Maven**

## Funcionalidades

A API foi construída sob a arquitetura REST e possui os seguintes recursos:

* **Gerenciamento de Projetos:** Criação, leitura, atualização e remoção (CRUD).

* **Gerenciamento de Tarefas:** Associação de tarefas aos projetos com controle de status (`PENDING`, `IN\_PROGRESS`, `COMPLETED`).

* **Regras de Negócio:** Proteção de integridade (Não é possível deletar um projeto que possua tarefas ativas).

* **Paginação e Filtros:** Listagem de dados paginada para garantir alta performance e escalabilidade.

* **Validação de Dados:** Uso de `DTOs` (Data Transfer Objects) e `Bean Validation` para barrar dados inconsistentes.

* **Tratamento de Exceções:** Retornos de erro customizados (Global Exception Handler) evitando o vazamento de stack traces.

* **Testes Unitários:** Cobertura de testes na camada de serviços (Regras de Negócio).

## Como Executar o Projeto

1. Clone este repositório em sua máquina local.
2. Certifique-se de ter o **Java 17+** e o **MySQL** instalados.
3. Crie um banco de dados vazio no MySQL chamado `taskboard\_db`:

```sql
CREATE DATABASE taskboard\_db;
```

4. Navegue até a pasta `src/main/resources/` e abra o arquivo `application.properties`. Altere as credenciais para o seu usuário e senha do banco:

```properties
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

5. Execute a classe principal `TaskBoardApplication.java` na sua IDE de preferência. O Spring criará as tabelas automaticamente.

## Documentação (Swagger)

Com a aplicação rodando, acesse a documentação interativa da API pelo seu navegador através do link abaixo:

`http://localhost:8080/swagger-ui/index.html`
