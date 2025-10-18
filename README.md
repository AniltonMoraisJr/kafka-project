
# Sistema de Processamento de Pedidos e Pagamentos

## Visão Geral
Este é um sistema distribuído composto por múltiplos microserviços que se comunicam através do Apache Kafka. O sistema gerencia pedidos, processa pagamentos e envia notificações.
Este projeto foi construído durante o curso de Quarkus na pós Java Elite da Unipds.

## Arquitetura

O projeto é composto pelos seguintes módulos:

### 1. Order Service (Serviço de Pedidos)
- Responsável pelo gerenciamento de pedidos
- Localização: `/order-service`
- Banco de dados dedicado em `/order_db_data`

### 2. Payment Service (Serviço de Pagamentos)
- Processa pagamentos relacionados aos pedidos
- Localização: `/payment-service`
- Banco de dados dedicado em `/payment_db_data`
- Produz eventos para o tópico `confirmedPayments` quando um pagamento é confirmado

### 3. Notification Service (Serviço de Notificações)
- Gerencia o envio de notificações
- Localização: `/notification-service`
- Consome eventos do tópico `notifyOrders`

### 4. Kafka Persistence (Persistência Kafka)
- Armazena dados do Kafka
- Localização: `/kafka-persistence`
- Gerencia a persistência das mensagens entre os serviços

## Comunicação via Kafka

### Tópicos do Kafka

1. **confirmedPayments**
    - Produtor: Payment Service
    - Consumidor: Order Service
    - Finalidade: Notificação de pagamentos confirmados

2. **notifyOrders**
    - Produtor: Order Service
    - Consumidor: Notification Service
    - Finalidade: Gatilho para envio de notificações relacionadas a pedidos

## Configuração do Ambiente

### Pré-requisitos
- Java SDK 21
- Docker e Docker Compose
- Quarkus Framework
- Jakarta EE

### Como Executar

1. Clone o repositório

2. Inicie os serviços usando Docker Compose:
```shell
bash docker-compose up -d
```

3. Os serviços estarão disponíveis nas seguintes portas:
    - Order Service: [porta a ser definida]
    - Payment Service: [porta a ser definida]
    - Notification Service: [porta a ser definida]

## Fluxo de Dados

1. Um pedido é criado no Order Service
2. O Payment Service processa o pagamento
3. Após confirmação do pagamento, um evento é enviado para o tópico `confirmedPayments`
4. O Order Service atualiza o status do pedido
5. Um evento é enviado para o tópico `notifyOrders`
6. O Notification Service envia as notificações apropriadas

## Tecnologias Utilizadas

- Jakarta EE
- Quarkus
- Apache Kafka
- Docker
- Java 21 

## TODOS
- Separar melhor as responsabilidades nos controllers
- Criar testes unitários e de integração
- Aplicar conceitos de tolerancia a falhas