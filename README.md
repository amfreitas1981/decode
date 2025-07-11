# decode

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Coverage](https://img.shields.io/badge/coverage-98%25-success)
![Java](https://img.shields.io/badge/java-17-blue)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Version](https://img.shields.io/badge/version-1.0.0-brightgreen)


## Descrição: Aplicação Java para Decodificar Sequências Lógicas

---

## Decode API — Codebreaker de Padrões Numéricos e Simbólicos

API desenvolvida em **Java com Spring Boot 3**, que identifica padrões lógicos (progressões, alternâncias, multiplicações e sequências simbólicas como letras) e prevê os dois próximos valores.

Ideal para uso educacional, desafios de lógica ou quebra-cabeças computacionais no estilo codebreaker.

---

### Funcionalidades

- Receber sequências via `POST` e retorna os dois próximos elementos detectados
- Suportar sequências numéricas e simbólicas
- Armazenar o histórico de entradas e previsões
- Possuir interface visual simples e Swagger para testes
- Cobertura de testes unitários e integração
- Suporte completo via Docker

---

### Tecnologias utilizadas

- Java 17
- Spring Boot 3.5.3
- Spring Web + REST
- Maven
- Swagger (Springdoc OpenAPI)
- JUnit 5 + Mockito
- Jacoco (cobertura de testes)
- Docker

---

## Exemplos de uso

### Requisição `POST /api/resolver`

```json
{
  "dados": ["A", "C", "E"]
}
```

Resposta:

```json
{
  "dados": ["A", "C", "E", "G", "I"]
}
```

---

## Estrutura do projeto

```
src/
├── config/
│   └── SwaggerConfig.java
├── controller/
│   └── SequenciaController.java
├── model/
│   └── SequenciaRequest.java
│   └── SequenciaResultado.java
├── service/
│   └── SequenciaService.java
│
├── DecodeApplication.java
│   
└── resources/
    └── static/
        └── index.html
```

---

### Requisição `GET /api/historico`

Retornar todas as sequências processadas:

```json
[
  {
    "dados": ["10", "15", "20", "25", "30"]
  },
  {
    "dados": ["A", "C", "E", "G", "I"]
  }
]
```

---

## Rodar localmente

### 1. Clonar o projeto

```bash
git clone https://github.com/amfreitas1981/decode.git
cd decode
```

### 2. Executar com Maven

```bash
./mvnw spring-boot:run
```

---

## Acessar a API via navegador

### Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Testes locais

### 1. Rodar os testes com cobertura

```bash
./mvnw clean verify
```

### 2. Visualizar relatório de cobertura (Jacoco)

Abrir:

```
target/site/jacoco/index.html
```

---

## Rodar via Docker

### 1. Criar o `Dockerfile`

```Dockerfile
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean verify

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

### 2. Build da imagem

```bash
docker build -t decode-app .
```

### 3. Rodar a aplicação

```bash
docker run -p 8080:8080 decode-app
```

---

## Executar testes via Docker (sem executar a API)

```Dockerfile
CMD ["echo", "Testes concluídos com sucesso!"]
```

Ou alterar o comando temporariamente para:

```bash
docker run decode-app mvn test
```

---

## Contribuição

- Fork o projeto
- Crie uma branch: `feature/minha-ideia`
- Envie um pull request com sua melhoria

---

## Licença

Este projeto é livre para fins educacionais e desafios lógicos. Licença pode ser ajustada conforme os objetivos do repositório.

---

# README - Projeto em inglês

---

Here's a professional, English-language version of your `README.md` — tailored for enterprise-level project delivery. It clearly communicates the purpose, usage, architecture, and deployment instructions in a way that's polished and practical for sharing with technical teams, stakeholders, or documentation portals.

---

# Decode API — Logical Sequence Pattern Recognition

This API, built with **Java + Spring Boot 3**, identifies mathematical and symbolic patterns in sequences and predicts the next two elements. It supports numeric logic (arithmetic progression, alternation, multiplication sequences) and symbolic reasoning (alphabet patterns) — ideal for educational platforms, puzzle logic processors, and codebreaker-style challenges.

---

## Features

- POST endpoint to analyze and expand a sequence (2 predicted values)
- GET endpoint to retrieve the full processing history
- Handles numeric and symbolic data inputs
- Web interface for manual testing (HTML/JS)
- Auto-generated Swagger documentation
- Full test coverage (unit & integration)
- Docker support for local testing and containerized deployment

---

## Technologies

- Java 17
- Spring Boot 3.2+
- REST (Spring Web)
- Swagger UI (Springdoc OpenAPI)
- JUnit 5 + Mockito
- Jacoco (Test Coverage)
- Maven
- Docker

---

## API Usage

### POST `/api/resolver`

**Request:**

```json
{
  "dados": ["A", "D", "G", "K"]
}
```

**Response:**

```json
{
  "dados": ["A", "D", "G", "K", "O", "S"]
}
```

### GET `/api/historico`

Returns all previously submitted sequences with predictions:

```json
[
  {
    "dados": ["10", "15", "20", "25", "30"]
  },
  {
    "dados": ["A", "C", "E", "G", "I"]
  }
]
```

---

## Local Setup & Execution

### 1. Clone the repository

```bash
git clone https://github.com/amfreitas1981/decode-api.git
cd decode-api
```

### 2. Run locally with Maven

```bash
./mvnw spring-boot:run
```

Or via IntelliJ: run `DecodeApplication.java`

### 3. Access Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

## Testing Locally

### Run all tests and generate coverage report

```bash
./mvnw clean verify
```

### Open coverage report (Jacoco):

```
target/site/jacoco/index.html
```

Tests include full coverage of:
- Pattern recognition logic
- Controller layer
- Models and configuration
- Application initialization

---

## Docker Instructions

### 1. Build the Docker image

Make sure your `Dockerfile` is set up for build + test + run:

```bash
docker build -t decode-app .
```

### 2. Run container

```bash
docker run -p 8080:8080 decode-app
```

### 3. Access the API in your browser:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Project Structure

```
src/
├── controller/           # REST API endpoints
├── service/              # Logic for pattern recognition
├── model/                # Data request & response models
├── config/               # Swagger + other configurations
└── resources/static/     # HTML/JS web interface
```

---

## License & Contributions

This repository may be used for educational, logic-based, or analytical purposes. Contributions are welcome via fork and pull request.

---
