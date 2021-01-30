# Store Management Back End

# Requirements
* JDK >= 11
* Kotlin
* Gradle >= 5.0
* Docker >= 19
* Docker Compose >= 1.13.0

### Run

```bash
$ docker-compose up --build -d
```

### Shut down

```bash
$ docker-compose down
```

### Swagger

(swagger show models with all keys (need to ignore params like id, linkedWith ...) use model in this readme)

```bash
http://localhost:8080/swagger-ui/index.html
```

### API
```bash

baseUrl : localhost:8080

----

Get all Clients
GET : /api/clients

Get Client by id
GET : /api/client/${storeId}

Create Client
POST : /api/clients
Model : {
        	"firstName":"",
        	"lastName":""
        }

Delete Client by id
DELETE : /api/client/${storeId}
---

Get all Store 
GET : /api/stores

Get Store by id
GET : /api/stores/${storeId}

Create Store
POST : /api/stores
Model : {
       	"name":""
        }

Delete Store : 
DELETE : /api/stores/${storeId}
---- 

Link Client with Store
POST : /api/actions/link
Model : {
        	"clientId":"",
        	"storeId":""
        }

UnLink Client with Store
POST : /api/actions/UnLink
Model : {
        	"clientId":"",
        	"storeId":""
        }

```