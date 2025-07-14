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

## ⚙️ Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/nfe-api.git
cd nfe-api
