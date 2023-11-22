# Proyecto Individual BCI - Nicolás Evangelista

## Descripción del Proyecto

Este proyecto es una API REST con Spring Boot y gradle para la creación y logueo de usuarios, con seguridad a través de tokens JWT. Consta de 2 endpoints (url base localhost:8080) los cuales sirven para registrar un usuario (/signUp), y loguearse con el token JWT generado por el endpoint anterior (/login).

Requisitos
Java 8
Gradle 7.4
Springboot 2.5.14
Junit 
Dependencias de Spring Boot (Spring Web, Spring Data JPA, Spring Security lombok, jaxb, jackson, etc)

## Configuración

Asegúrate de tener Java 8 instalado en tu sistema.

Edita el archivo application.properties para configurar la base de datos y otras propiedades según tus necesidades.

## Ejecución

Puedes ejecutar el proyecto utilizando Gradle. Abre una terminal en el directorio raíz del proyecto y ejecuta el siguiente comando:

Terminal cmd: gradlew bootRun

Terminal bash: ./gradlew bootRun

** Si el comando se ejecuta correctamente debe mostrar un los similar al siguiente: Hibernate: select u1_0.id,u1_0.created,u1_0.email,u1_0.is_active,u1_0.last_login,u1_0.name,u1_0.password,u1_0.token from apiuser u1_0 Hibernate: select p1_0.user_id,p1_1.id,p1_1.citycode,p1_1.countrycode,p1_1.number from apiuser_phones p1_0 join phone p1_1 on p1_1.id=p1_0.phones_id where p1_0.user_id=? <==========---> 80% EXECUTING [3m 27s]

:bootRun

La aplicación se ejecutará en http://localhost:8080.

**Aclaracion: Es necesario tener instalada la versión 8 o superior de java para ejecutar el programa.**

## Endpoints
/sign-up: Permite la creación de un usuario con un contrato de entrada en formato JSON. Validará el formato del correo electrónico y la contraseña. Retorna un usuario con los campos id, created, lastLogin, token y isActive.

Uso Puedes utilizar herramientas como Postman para probar los endpoints del servicio.

Ejemplo de solicitud POST para crear un usuario:

POST http://localhost:8080/signUp 
{
    "name": "String",
    "email": "nicols@yahoo.com",
    "password": "P4ssw0rd",
    "phones": [
        {
            "number": 123456789,
            "cityCode": 5539,
            "countryCode": "+19"
        }
    ]
} 
** La respuesta de este servisio es un Httpstatus + un json con la siguiente estructura 
{
    "id": "a0b1a97e-2777-431c-a33c-366c30ae2bd0",
    "name": "String",
    "email": "nicolsass1@yahoo.com",
    "password": "P4ssw0rd",
    "phones": [
        {
            "id": "4a961b32-34d5-495c-9dc0-edd01ef91d7d",
            "number": 123456789,
            "cityCode": 5539,
            "countryCode": "+19"
        }
    ],
    "created": "2023-11-21T12:22:08.8179667",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaWNvbHNhc3MxQHlhaG9vLmNvbSIsIm5iZiI6MTcwMDU4MDEyOCwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2FwaS92MSIsImV4cCI6MTcwMDU4MDE1OCwiaWF0IjoxNzAwNTgwMTI4LCJqdGkiOiIxOWYxNTYxMy05OWI2LTRkOTMtYjE1MS0xOTI5ODAzYjE5OTQifQ.vT1jHO2tkSofqFPXsIpIull-5YDnVjvBnn4TvgUBkSc",
    "isActive": true
}

** En caso de error el json de respuesta es el siguiente: 
{
    "errors": [
        {
            "timestamp": 1700677621,
            "codigo": 400,
            "detail": "The user entered is currently in use."
        }
    ]
}

## Diagramas
Los diagramas de componentes y secuencia se encuentran en la carpeta diagramas de este repositorio.

## "Pruebas unitarias"
Se desarrollaron 4 test unitarios y se utilizo junit y mockito para llegar a la cobertura indicada en SonarCube de más del 80%

UserControllerTest.java
CreateUserTest.java
loginUserTest.java
JWTUtilTest.java
