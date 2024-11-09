# Projeto Spring Boot com Keycloak e OAuth2

Este é um projeto de exemplo que utiliza **Spring Boot**, **OAuth2**, e **Keycloak** para implementar autenticação e autorização em uma aplicação web. Ele permite o acesso a rotas públicas e privadas, demonstrando a integração com o Keycloak para autenticação via JWT.

## Tecnologias Utilizadas

- **Spring Boot**
- **Spring Security**
- **OAuth2**
- **Keycloak** (como servidor de identidade)

## Configuração do Keycloak

### Pré-requisitos

1. **Keycloak** precisa estar em execução. Se estiver usando o Keycloak local, a URL padrão é `http://localhost:8080`.
2. Configurar um **realm** chamado `luizakuze`.
3. Criar um **cliente OAuth2** no Keycloak:
   - `client-id`: `spring-security-keycloak`
   - `client-secret`: `eK0jsvSGkI5KeHZMUeyPp9OD5BSo9oNr`
   - Scope: `openid, profile, email`
4. Configurar as credenciais do cliente e o URI do emissor.

## Estrutura do Projeto

### 1. `DemoApplication.java`
Classe principal que inicializa a aplicação.

### 2. `HttpController.java`
Controller com rotas de exemplo:
- `/public` - Rota pública acessível por qualquer pessoa.
- `/private` - Rota privada acessível apenas para usuários autenticados.
- `/cookie` - Exibe informações do token OAuth2.
- `/jwt` - Exibe o JWT e claims do token.

### 3. `SecurityConfig.java`
Configuração de segurança para a aplicação:
- Permite acesso público às rotas `/public` e `/logout`.
- Exige autenticação para todas as outras rotas.
- Integra o login via OAuth2 e autenticação JWT como Resource Server.

## Como Executar

1. Certifique-se de que o Keycloak está configurado e em execução conforme descrito acima.
2. Execute a aplicação Spring Boot com o comando:

   ```bash
   ./gradlew run
   ```
3. Acesse as rotas para testar:
Rota pública: http://localhost:9090/public
Rota privada (necessário login): http://localhost:9090/private
Rota com informações do token OAuth2: http://localhost:9090/cookie
Rota com JWT: http://localhost:9090/jwt

## Observações
- Para utilizar o Keycloak como servidor de autenticação, é necessário ter um cliente configurado com client-id e client-secret no Keycloak.
- A integração com OAuth2 permite autenticação via Keycloak usando tokens JWT.
- As rotas privadas exigem que o usuário esteja autenticado e autorizado.