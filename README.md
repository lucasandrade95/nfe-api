# API de Gerenciamento de Notas Fiscais e Produtos

Esta é uma API RESTful desenvolvida com Spring Boot com foco em autenticação via JWT, manipulação de dados com JPA e documentação via Swagger.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- H2 Database (memória)
- Bean Validation
- Swagger UI (SpringDoc OpenAPI 3)

---

## 📦 Funcionalidades

### 🔐 Autenticação
- Registro de usuário: `POST /auth/register`
- Login: `POST /auth/login` (retorna access e refresh token)
- Refresh Token: `POST /auth/refresh`
- Logout: `POST /auth/logout`

### 📄 Notas Fiscais
- Criar: `POST /notas`
- Buscar por ID: `GET /notas/{id}`
- Listar todas: `GET /notas`
- Atualizar: `PUT /notas/{id}`
- Deletar: `DELETE /notas/{id}`

### 📦 Produtos (vinculados às notas)
- Adicionar: `POST /notas/{idNota}/produtos`
- Listar: `GET /notas/{idNota}/produtos`
- Atualizar: `PUT /produtos/{id}`
- Deletar: `DELETE /produtos/{id}`

---

## H2 Console

1. Url para o banco:

```bash
http://localhost:8080/h2-console
```
2. Para acessar o banco de dados:

```bash
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password:  (deixe em branco)

```
## ⚙️ Como Executar

1. Inicie o projeto com o comando:

```bash
./mvnw spring-boot:run

