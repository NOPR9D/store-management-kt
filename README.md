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

$ curl -v http://localhost:8080
```

### Shut down

```bash
$ docker-compose down
```


### Utils

## Create client

Endpoint :
```bash
localhost:8080/api/clients
```
Model :

```json
{
	"firstName":"Jean",
	"lastName":"Dupont"
}
```

## Create client

Endpoint :
```bash
localhost:8080/api/clients
```
Model :

```json
{
	"firstName":"Jean",
	"lastName":"Dupont"
}
```