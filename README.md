# CRUD de Superhéroes

Este proyecto es un CRUD (Create, Read, Update, Delete) de superhéroes desarrollado en Java utilizando el framework Spring Boot. Permite gestionar una colección de superhéroes, con operaciones como consultar, crear, actualizar y eliminar.

## Funcionalidades

El proyecto cuenta con las siguientes funcionalidades:

- Consultar todos los superhéroes.
- Consultar un único superhéroe por su ID.
- Consultar superhéroes que contienen cierto texto en su nombre.
- Crear un nuevo superhéroe.
- Modificar un superhéroe existente.
- Eliminar un superhéroe.

Además, incluye las siguientes características adicionales:

- Uso de base de datos H2 en memoria para almacenar los superhéroes.
- Documentación de la API con Swagger/OpenAPI.
- Gestión centralizada de excepciones.
- Anotación personalizada para medir el tiempo de ejecución de los métodos de la API.
- Pruebas unitarias y de integración.
- Uso de Flyway para el mantenimiento de scripts DDL de la base de datos.

## Tecnologías Utilizadas

El proyecto utiliza las siguientes tecnologías:

- Java
- Spring Boot
- Maven
- H2 Database (Base de datos en memoria)
- Spring Data JPA (Acceso a datos)
- Springdoc OpenAPI (Documentación de la API)
- Spring AOP (Aspect-Oriented Programming) para medir el tiempo de ejecución de los métodos de la API
- JUnit y Mockito (Pruebas unitarias e integración)
- Lombok (Reducir la verbosidad del código)
- Flyway (Gestión de versiones de la base de datos)

## Configuración y Uso

1. Clona este repositorio.
2. Importa el proyecto en tu IDE favorito.
3. Ejecuta la aplicación.
4. Accede a la documentación de la API desde `http://localhost:8080/swagger-ui/index.html`.

## Uso con Docker

1. Tener Docker instalado y funcionando
2. Construye la imagen Docker ejecutando el siguiente comando en la raíz del proyecto:
   ```
   docker build -t crud-superheroes .
    ```
3. Ejecuta el contenedor Docker con el siguiente comando:
4. ```
   docker run -p 8080:8080 crud-superheroes
   ```
5. Accede a la documentación de la API desde `http://localhost:8080/swagger-ui/index.html`.
6. Para detener el contenedor, ejecuta el siguiente comando:
   ```
   docker stop <CONTAINER_ID>
   ```
    Puedes obtener el CONTAINER_ID ejecutando el comando `docker ps`.

## Autores

- [Emanuel flores](https://github.com/emaflores) - Desarrollador principal
