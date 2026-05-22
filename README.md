# 🎮 Game API

API REST desenvolvida com Spring Boot para gerenciamento de jogos e plataformas, utilizando autenticação JWT, Spring Security, Swagger/OpenAPI e HATEOAS.

---

# 📌 Objetivo do Projeto

O objetivo da aplicação é permitir o gerenciamento de:

- Jogos
- Plataformas
- Usuários

A API possui:
- CRUD completo
- Segurança com JWT
- Controle de acesso por perfil
- Documentação Swagger
- Navegação HATEOAS
- Tratamento global de exceções

---

# 🚀 Tecnologias Utilizadas

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 3.x |
| Spring Web | ✓ |
| Spring Data JPA | ✓ |
| Spring Security | ✓ |
| JWT | ✓ |
| Swagger/OpenAPI | ✓ |
| HATEOAS | ✓ |
| Gradle | ✓ |

---

# 📂 Estrutura do Projeto

```txt
src/main/java/br/com/fiap/gameapi

├── config
├── controller
├── dto
│   ├── Request
│   └── Response
├── exception
├── mapper
├── model
├── repository
├── security
└── service
```

---

# 📦 Dependências Utilizadas

```gradle
dependencies {

    implementation 'org.springframework.boot:spring-boot-starter-web'

    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

    implementation 'org.springframework.boot:spring-boot-starter-security'

    implementation 'org.springframework.boot:spring-boot-starter-validation'

    implementation 'org.springframework.boot:spring-boot-starter-hateoas'

    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9'

    implementation 'com.auth0:java-jwt:4.4.0'
}
```

---

# 🔐 Segurança JWT

A API utiliza autenticação JWT para proteger endpoints privados e garantir maior segurança na comunicação entre cliente e servidor.

---

# 📚 Swagger/OpenAPI

A aplicação possui documentação automática utilizando Swagger/OpenAPI, permitindo testes e visualização dos endpoints de forma prática e organizada.

---

# 🔗 HATEOAS

A aplicação utiliza HATEOAS para fornecer links navegáveis nos retornos da API, permitindo uma navegação mais intuitiva entre os recursos disponíveis.

---

# 📌 Funcionalidades da API

## 🎮 Jogos

- Criar jogo
- Listar jogos
- Buscar jogo por ID
- Atualizar jogo
- Remover jogo

---

## 🕹️ Plataformas

- Criar plataforma
- Listar plataformas
- Buscar plataforma por ID
- Atualizar plataforma
- Remover plataforma

---

## 👤 Usuários

- Cadastro de usuário
- Login JWT

---

# ⚠️ Tratamento Global de Exceções

A aplicação possui tratamento global de exceções para retornar mensagens de erro mais organizadas e amigáveis ao usuário.

---

# ✅ Validações

A API utiliza Bean Validation com anotações para validação automática dos dados recebidos.

Principais validações utilizadas:

- @NotBlank
- @Size
- @Pattern
- @Min
- @Max
- @DecimalMin

---

# 🧪 Testes da API

Os testes da aplicação podem ser realizados utilizando Swagger/OpenAPI.

A aplicação permite:

- Testes de autenticação
- Testes de CRUD
- Testes de segurança
- Testes de validação
- Testes de HATEOAS

---

# 👨‍💻 Autor

## Maria Luiza Alves de Aquino

RM561802

---

# 📌 Status do Projeto

✅ Finalizado  
✅ JWT funcionando  
✅ Swagger funcionando  
✅ HATEOAS implementado  
