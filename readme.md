# Setup do Ambiente com Docker e Spring Boot

## Descrição
Este projeto configura um ambiente com RabbitMQ e PostgreSQL utilizando `docker-compose`. Após configurar os containers, você pode executar a aplicação Spring Boot e interagir com os endpoints expostos.

## Pré-requisitos
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- JDK 17+ instalado para executar o servidor Spring Boot

## Passos para Configuração

1. Clone o repositório e navegue até o diretório do projeto:
   ```bash
   git clone <URL-DO-REPOSITORIO>
   cd <DIRETORIO-DO-PROJETO>
   ```

2. Suba os containers do RabbitMQ e PostgreSQL:
   ```bash
   docker-compose up -d
   ```

3. Inicie o servidor Spring Boot. Certifique-se de que o PostgreSQL está acessível em `localhost:5432` e o RabbitMQ em `localhost:5672`. Use o comando:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Pronto! O ambiente está configurado e o servidor está pronto para receber requisições.

## Exemplo de Requisição
Você pode testar o endpoint com o seguinte comando `curl`:

```bash
curl --location 'http://localhost:8080/transactions' \
--header 'accept: */*' \
--header 'Content-Type: application/json' \
--data '{
  "totalAmount": 10,
  "type": "CASH",
  "mcc": "5411",
  "merchant": "LAYANNE FOOD",
  "account": "123"
}'
```

## Estrutura do Banco de Dados
Durante a inicialização, as tabelas necessárias serão criadas automaticamente no PostgreSQL e populadas com dados de exemplo, conforme definido no arquivo `init.sql`.

## Debugging
- Para verificar os logs do PostgreSQL:
  ```bash
  docker logs postgres
  ```

- Para verificar os logs do RabbitMQ:
  ```bash
  docker logs rabbitmq
  ```

- Para verificar se os containers estão em execução:
  ```bash
  docker ps
  ```

## Encerrando os Containers
Para encerrar e remover os containers, use:
```bash
docker-compose down
