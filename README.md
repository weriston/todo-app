# Projeto To-Do

Este é um projeto de exemplo para gerenciamento de tarefas (To-Do). O objetivo deste projeto é fornecer uma aplicação web que permita aos usuários gerenciar suas tarefas diárias de maneira eficiente. A aplicação inclui funcionalidades para registro de usuários, autenticação, criação, edição e exclusão de tarefas.

## Funcionalidades

- **Registro de Usuário**: Permite que novos usuários se registrem na aplicação.
- **Autenticação**: Usuários podem fazer login para acessar suas tarefas.
- **Gerenciamento de Tarefas**: Usuários podem criar, visualizar, editar e excluir tarefas.
- **Documentação da API**: A API está documentada utilizando Swagger/OpenAPI.
- **Execução em Ambiente Local e com Docker**: Instruções fornecidas para executar a aplicação localmente ou via Docker.

## Regras de Uso

1. **Registro de Usuário**:
   - Acesse a rota `/users/register` para registrar um novo usuário.
   - Forneça um nome de usuário, senha e outros detalhes solicitados.

2. **Login**:
   - Acesse a rota `/auth/login` para autenticar um usuário registrado.
   - Forneça o nome de usuário e a senha para receber um token JWT.

3. **Gerenciamento de Tarefas**:
   - **Criar Tarefa**: Acesse a rota `/tasks` com o método POST, fornecendo os detalhes da tarefa.
   - **Listar Tarefas**: Acesse a rota `/tasks` com o método GET para listar todas as tarefas do usuário autenticado.
   - **Editar Tarefa**: Acesse a rota `/tasks/{id}` com o método PUT para editar uma tarefa existente.
   - **Excluir Tarefa**: Acesse a rota `/tasks/{id}` com o método DELETE para excluir uma tarefa.

## Pré-requisitos

- Java JDK 17
- Maven 3.x
- Git

## Configuração do Ambiente Local

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/weristonsp/nome-do-repositorio.git
   cd nome-do-repositorio 


## Configuração do Banco de Dados

**O projeto utiliza o banco de dados H2 integrado para ambientes de desenvolvimento. Não são necessárias configurações adicionais para execução local.**
       
## Compilação e Execução

1. **Compile o projeto usando Maven:**

   ```bash
   mvn clean package
    
2. **Execute a aplicação:**

   ```bash
   java -jar target/todo.jar
   
3. **Acesse a API localmente em http://localhost:8080.**
   
## Documentação da API

**A documentação da API está disponível através do Swagger/OpenAPI em http://localhost:8080/swagger-ui.html.**

## Execução com Docker (Opcional)

1. **Caso prefira executar com Docker, certifique-se de ter o Docker instalado e execute o seguinte comando:**

   ```bash
   docker-compose up

**Isso iniciará a aplicação junto com o banco de dados configurado no Docker.**

# Segurança Aplicada
 
**O projeto To-Do utiliza a segurança Spring Security para proteger as rotas da aplicação. A seguir estão os principais pontos da configuração de segurança:**
1. **Autenticação JWT: A aplicação utiliza JSON Web Tokens (JWT) para autenticação. Após o login bem-sucedido, um token JWT é gerado e deve ser incluído no cabeçalho de autorização (Authorization: Bearer <token>) para acessar rotas protegidas.**
2. **Filtros de Segurança: Um filtro JWT personalizado (JwtRequestFilter) é adicionado para interceptar requisições e validar o token JWT antes de permitir o acesso aos recursos.**
3. **Configuração de Segurança: As rotas de autenticação (/auth/**) e registro de usuário (/users/register) são publicamente acessíveis, enquanto todas as outras rotas exigem autenticação. A política de sessão é configurada como STATELESS para garantir que a aplicação não mantenha sessões de usuário no servidor.**
4. **Codificação de Senhas: As senhas dos usuários são codificadas usando BCrypt antes de serem armazenadas no banco de dados, garantindo que as senhas sejam armazenadas de forma segura.**
5. **Cross-Site Request Forgery (CSRF): A proteção CSRF é desativada para simplificação do desenvolvimento, mas pode ser habilitada e configurada conforme necessário em ambientes de produção.**

## Notas Adicionais

**As informações de propriedades tutilizadas estão consolidadas no arquivo application.properties.**


## Licença

**Este projeto está licenciado sob a MIT License. Maiores informações contacte: weristonsp@gmail.com**
