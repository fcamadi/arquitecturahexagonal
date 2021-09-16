Ejercicio con arquitectura hexagonal para empresa de comercio electrónico

- Acceso a documentación de la API:

http://localhost:8080/prices/api-docs

- Swagger:

http://localhost:8080/swagger-ui/index.html?configUrl=/prices/api-docs/swagger-config


- Ejecución con Docker

construir la imagen con el siguiente comando:

$ docker build --tag=pricer:latest --rm=true .

Ejecutar con 

$ docker run -d --name pricer -p 8080:8080 pricer
