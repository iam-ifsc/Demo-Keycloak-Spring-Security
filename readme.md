# Projeto Spring Boot com Keycloak e OAuth2 🌱

Este é um projeto de exemplo que utiliza **Spring Boot**, **OAuth2**, e **Keycloak** para implementar autenticação e autorização em uma aplicação web. 

O Keycloak atua como authorization server, ele vai autorizar aplicações parceiras à acessar recursos protegidos da aplicação tuilizando o OAuth2 e o OIDC.

## Tecnologias Utilizadas

- **Spring Boot**
- **Spring Security**
- **OAuth2**
- **Keycloak** (como servidor de identidade)

## Configuração do Keycloak

### Pré-requisitos

1. **Keycloak** precisa estar em execução:
```bash
docker run -p 8080:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.0.5 start-dev
```
2. Configurar um **realm**, nesse caso foi utilizado o nome `ifsc`. Essa realm vai atuar como um tenent, um grupo de usuários que compartilham um sistema comum.

   ![alt text](./src/main/resources/imgs/realme.png)

3. Crie um usuário para logar na aplicação:
   > Note o seguinte: Está sendo utilizado usuário próprio. Também é possível colocar o Google como provedor de identidade

   3.1. Na aba **Users**, preencha com as seguintes informações e depois clique em _Create_.

   ![alt text](./src/main/resources/imgs/userKeycloak.png)

   3.2. Ainda na aba **Users**, clique em **Credentials** e configure uma senha para este usuário. Ao final, clique em _Save_.

   ![alt text](./src/main/resources/imgs/passwordKeycloak.png)

3. Criar um **cliente OAuth2** no Keycloak:

   A ideia do OAuth é autorizar terceiros à acessar um recurso protegido, então esses terceiros também devem ser conhecidos, são os chamados clientes.

   Não basta autenticar o usuário, a aplicação também deve ser conhecida.

   ![alt text](./src/main/resources/imgs/criarCliente.png)

   Após pressionar "Create cliente", vamos ter as seguintes opções a serem preenchidas:

   ![alt text](./src/main/resources/imgs/CreateClient01.png)

   - `client-id`: `spring-security-keycloak`


   Habilitar a opção "Client Authentication" em que faz ser gerada uma credencial para o client id, o chamado "client secret".

   ![alt text](./src/main/resources/imgs/CreateClient02.png)

   Em valid redirect uri tem que colocar o uri da aplicação, pois o fluxo do authorization code durante o redirecionamento para obter o token a partir do code a gente reconheça o endereço da aplicação é válido e é desse cliente.
 
   ![alt text](./src/main/resources/imgs/CreateClient03.png)


   Após isso, clicar em salvar. 

   Ao acessar a aba Credentials, ainda em Clients, podemos verificar a client secret

   Copiar ela para adicionar essa configuração no application.yml.
    
4. Configurar as credenciais do cliente e o URI do emissor no aaplication.yml.
 
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