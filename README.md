
# Projeto Ocorre Map

O Ocorre Map é um sistema desenvolvido em Spring Boot 3 para gerenciar ocorrências de trânsito, utilizando PostgreSQL como banco de dados e Java 17 


## Configuração:

 - Certifique-se de ter o PostgreSQL instalado localmente.
 - Crie um banco de dados com o nome ocorre_map.
 - Após configurar o ambiente, você pode iniciar o servidor de desenvolvimento Angular usando o seguinte comando: 
   ng serve.
 - Verifique se as credenciais de acesso (username e password) estão corretamente configuradas no arquivo application.properties:

   spring.datasource.url=jdbc:postgresql://localhost:5432/ocorre_map

   spring.datasource.username=seu_usuario

   spring.datasource.password=sua_senha

- Utilize Maven para instalar as dependências do projeto. Na raiz do projeto, execute o comando:
  
  mvn clean install

- Executando o Projeto:

  mvn spring-boot:run

- credenciais:
  O arquivo DataBaseSeeder.java automaticamente criará um usuário padrão:

  Email: admin@mail.com
  
  Senha: 123456
