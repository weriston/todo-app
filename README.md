# Projeto TODO App

Este é um projeto de aplicativo de gerenciamento de tarefas (TODO) desenvolvido em Java com Spring Boot. O objetivo é fornecer uma API REST para que os usuários possam criar, atualizar, excluir e listar suas tarefas.

## Funcionalidades

- Cadastro de usuários
- Login de usuários
- Criação de tarefas
- Atualização de tarefas
- Exclusão de tarefas
- Listagem de tarefas pendentes
- Filtragem de tarefas por prioridade

## Regras de Uso

- Apenas usuários autenticados podem acessar as funcionalidades do aplicativo, exceto o cadastro e o login.
- Cada usuário só pode gerenciar suas próprias tarefas.
- As senhas dos usuários são armazenadas de forma criptografada.
- A autenticação é realizada por meio de token JWT (JSON Web Token).
- O token de acesso expira após 15 minutos de inatividade.

## Pré-requisitos

- JDK 17
- Maven 3.x
- Git
- Banco de dados H2 (em memória)

## Como Baixar e Executar o Projeto

### Clonando o Repositório via Linha de Comando

1. Abra o terminal ou prompt de comando.
2. Execute o seguinte comando para clonar o repositório:
   ```bash
   git clone https://github.com/weriston/todo-app.git 
3. Navegue até o diretório do projeto:
   ```bash
   cd todo-app 

### Baixando o Projeto via IntelliJ IDEA

1. Abra o IntelliJ IDEA.
2. Clique em "File" -> "New" -> "Project from Version Control".
3. Selecione "Git" como o sistema de controle de versão.
4. Insira a URL do repositório: `https://github.com/weriston/todo-app.git`
5. Escolha o diretório onde deseja salvar o projeto.
6. Clique em "Clone".

### Executando o Projeto

1. Abra o projeto no IntelliJ IDEA.
2. Aguarde o download das dependências do Maven.
3. Localize a classe `TodoApplication` no pacote `com.example.todo`.
4. Clique com o botão direito do mouse na classe e selecione "Run 'TodoApplication'".
5. O aplicativo será iniciado e estará disponível em `http://localhost:8080`.

## Segurança

- A autenticação é realizada por meio de token JWT.
- As senhas dos usuários são armazenadas de forma criptografada usando o algoritmo BCrypt.
- Apenas usuários autenticados podem acessar as funcionalidades protegidas da API.
- O token de acesso expira após 15 minutos de inatividade, exigindo que o usuário faça login novamente.

## Banco de Dados

- O projeto utiliza o banco de dados H2, que é um banco de dados em memória.
- As tabelas e os dados iniciais são criados automaticamente ao iniciar a aplicação.
- Não é necessário configurar um banco de dados externo.

## Usando o Docker (Opcional)

1. Certifique-se de ter o Docker instalado em sua máquina.
2. Abra o terminal na raiz do projeto.
3. Execute o seguinte comando para criar a imagem Docker:
   ```bash
   docker build -t todo-app . 
4. Após a conclusão do build, execute o seguinte comando para iniciar o contêiner:
   ```bash
   docker run -p 8080:8080 todo-app
5. A aplicação estará disponível em `http://localhost:8080`.

## Testando a Aplicação

Para testar as funcionalidades da aplicação, você pode usar ferramentas como o Postman ou o cURL. Aqui estão alguns exemplos de requisições:

### Cadastro de Usuário
- POST /users Content-Type: application/json
   ```bash
   { "name": "João Silva", "email": "joao.silva@example.com", "password": "Password@123" }

### Login
- POST /auth/login Content-Type: application/json
   ```bash
   { "email": "joao.silva@example.com", "password": "Password@123" }
- O login retornará um token JWT que deve ser incluído no cabeçalho `Authorization` das requisições subsequentes.

### Criação de Tarefa
- POST /tasks Content-Type: application/json Authorization: Bearer {token}
   ```bash
   { "description": "Comprar leite", "priority": "ALTA" }

### Listagem de Tarefas Pendentes
- GET /tasks?completed=false Authorization: Bearer {token}

### Marcar Tarefa como Concluída
- PUT /tasks/{id}/complete Authorization: Bearer {token}
- Substitua `{id}` pelo ID da tarefa que deseja marcar como concluída.
- Esses são apenas alguns exemplos de requisições. Para obter a lista completa de endpoints disponíveis, consulte a documentação da API em `http://localhost:8080/swagger-ui.html`.

## Contribuindo
- Se você deseja contribuir para o projeto ToDo App, siga estas etapas:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature ou correção de bug: `git checkout -b minha-feature`.
3. Faça as alterações necessárias e adicione os commits: `git commit -m 'Minha nova feature'`.
4. Envie as alterações para o seu fork: `git push origin minha-feature`.
5. Abra um pull request no repositório original.

Agradecemos antecipadamente por suas contribuições!

## Documentação da API

- A documentação da API é gerada automaticamente usando o Swagger.
- Após iniciar a aplicação, acesse `http://localhost:8080/swagger-ui.html` para visualizar a documentação.

Esperamos que este projeto atenda às suas expectativas e demonstre nossas habilidades como desenvolvedores backend. Estamos à disposição para quaisquer esclarecimentos adicionais.

## Licença

**Este projeto está licenciado sob a MIT License. Maiores informações entre em contato com: weristonsp@gmail.com**
