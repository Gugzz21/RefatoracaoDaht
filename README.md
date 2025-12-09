# DAHT - Agenda Gamificada para Criação de Hábitos

Bem-vindo ao repositório do projeto **DAHT** (Agenda Gamificada para Criação de Hábitos). Este projeto é uma API RESTful desenvolvida com Java e Spring Boot, focada em ajudar usuários a criar e manter hábitos através de gamificação.

## 🚀 Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias e ferramentas:

*   **Java 21**: Linguagem de programação.
*   **Spring Boot 3.4.0**: Framework para desenvolvimento rápido de aplicações Java.
*   **Spring Data JPA**: Abstração para persistência de dados.
*   **MySQL**: Banco de dados relacional (produção/desenvolvimento).
*   **H2 Database**: Banco de dados em memória (testes/desenvolvimento rápido).
*   **Spring Security**: Framework de autenticação e controle de acesso.
*   **Java JWT (Auth0)**: Biblioteca para criação e verificação de JSON Web Tokens.
*   **OpenAPI (Swagger UI)**: Documentação interativa da API.
*   **Maven**: Gerenciamento de dependências e build.
*   **Docker**: Containerização da aplicação.
*   **Jenkins**: Automação de CI/CD (Pipeline configurado no `Jenkinsfile`).

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

*   [Java JDK 21](https://adoptium.net/)
*   [Maven](https://maven.apache.org/)
*   [Docker](https://www.docker.com/) (Opcional, mas recomendado)

## 🔧 Como Executar

### 1. Clonar o Repositório

```bash
git clone <URL_DO_SEU_REPOSITORIO>
cd RefatoracaoDaht
```

### 2. Executar Localmente com Maven

Você pode rodar a aplicação diretamente usando o Maven Wrapper incluído no projeto:

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8412/daht` (devido à configuração `server.servlet.context-path=/daht`).

> **Nota:** Verifique o arquivo `application.properties` ou `application.yml` para configurar as credenciais do banco de dados MySQL.

### 3. Executar com Docker

Para construir e rodar a imagem Docker:

```bash
# Construir a imagem
docker build -t daht-api .

# Rodar o container
docker run -p 8412:8412 daht-api
```

## 📖 Documentação da API

A documentação interativa da API (Swagger UI) pode ser acessada após iniciar a aplicação em:

```
http://localhost:8412/daht/swagger-ui.html
```

## 📂 Estrutura do Projeto

A estrutura de diretórios segue o padrão Maven/Spring Boot:

*   `src/main/java`: Código fonte da aplicação.
    *   `com.senac.daht.agenda`: Pacote raiz.
        *   `config`: Configurações do projeto (Security, Swagger, etc).
        *   `controller`: Controladores REST.
        *   `dto`: Objetos de Transferência de Dados.
        *   `entity`: Entidades JPA.
        *   `repository`: Repositórios de dados.
        *   `service`: Regras de negócio.
*   `src/test`: Testes unitários e de integração.
*   `Dockerfile`: Configuração para build da imagem Docker.
*   `Jenkinsfile`: Pipeline de CI/CD.

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

---
Desenvolvido por Gugzz21
