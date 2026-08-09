# Spring Boot + NATS JetStream

Демонстрационный проект с:

- NATS JetStream Stream `ORDERS`;
- REST producer: `POST /orders?orderId=42`;
- durable pull consumer `orders-worker`;
- explicit ACK после успешной обработки;
- NAK и redelivery при ошибке;
- дедупликацией публикаций через `Nats-Msg-Id`;
- примером идемпотентного consumer.

## Запуск

```bash
docker compose up -d
mvn spring-boot:run
```

В другом терминале:

```bash
curl -X POST 'http://localhost:8989/orders?orderId=42'
```

Сообщение будет сохранено в JetStream Stream и обработано durable consumer.

## Проверка JetStream

Если установлен NATS CLI:

```bash
nats stream ls
nats stream info ORDERS
nats consumer ls ORDERS
nats consumer info ORDERS orders-worker
```

## Как обрабатываются ошибки

1. Producer получает `PublishAck` от JetStream.
2. Consumer выполняет бизнес-операцию.
3. При успехе отправляется `message.ack()`.
4. При ошибке отправляется `message.nak()`.
5. Если ACK не пришёл за 30 секунд, JetStream повторно доставляет сообщение.
6. После пяти доставок сообщение остаётся в Stream для анализа и отдельной обработки.

Для production вместо in-memory `processedEvents` следует использовать таблицу `processed_events` в той же транзакции, что и бизнес-изменения.

## Схема

```text
HTTP client
    |
    v
OrderController -> OrderPublisher -> ORDERS Stream
                                      |
                                      v
                            orders-worker consumer
                                      |
                                      v
                              business operation
                                      |
                              ACK / NAK
```
