## 1. Клонировать репозиторий

Склонируйте проект с гитхаба: 

```shell
git clone https://github.com/Day-vampire/MicroservicesSolutionForTechnicalTask.git

```

## 2. Сборка прокта

Перейдите в корневую папку проекта и выполните следующую команды для запуска баз данных и приложения:
```shell

docker compose up -d --zookeeper
docker compose up -d --kafka
docker compose up -d --kafka-ui
docker compose up -d --UserServiceDB
docker compose up -d --AccountServiceDB
docker compose up -d --AuthServiceDB
docker compose up -d --user-service
docker compose up -d --account-service
docker compose up -d --auth-service
docker compose up -d --api-gateway

```

Или запустите docker-compose.

## 3. Открыть Swagger
Откройте первый сваггер (auth-service) и зарегистрируйте/авторизуйте пользователя, получив при этом jwt
```shell
go to http://localhost:8080/api/auth-service/swagger-ui/index.html#/
```
Далее переходите к сваггерам микросервисов и счетов, указывайте для работы методов полученный jwt и тестируйте их.  
```shell
go to http://localhost:8080/api/user-service/swagger-ui/index.html#/
go to http://localhost:8080/api/account-service/swagger-ui/index.html#/
```