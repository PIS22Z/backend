## Wymagania

- JDK 11+
- Docker
- docker-compose

## Uruchomienie aplikacji

Uruchomienie infrastruktury (mongodb + rabbitmq)

```bash
docker-compose up -d
```

Uruchomienie aplikacji

```bash
./gradlew build -x test
java -jar build/libs/backend-0.0.1-SNAPSHOT-all.jar
```
