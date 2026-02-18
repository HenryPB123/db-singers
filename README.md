# 🎤 db-singers

Aplicación de consola desarrollada en Java que integra consumo de API externa (IA - Gemini) con persistencia en base de datos relacional.

El proyecto demuestra habilidades en integración de servicios externos, manejo de datos, arquitectura básica en capas y buenas prácticas de desarrollo backend.

## 📌 Objetivo del Proyecto

El propósito de esta aplicación es:

Consumir información desde una API de Inteligencia Artificial.

Procesar y transformar la respuesta recibida.

Persistir los datos en una base de datos relacional.

Permitir consultas posteriores desde la base de datos.

Este proyecto fue desarrollado como práctica de integración entre servicios externos y persistencia de datos utilizando Java.

## 🧠 Arquitectura y Enfoque

El proyecto sigue una estructura organizada en capas:

Capa de presentación (Interacción por consola)

Capa de servicio (Lógica de negocio)

Capa de acceso a datos (Persistencia / JDBC)

Integración externa (Consumo de API Gemini)

Se aplican principios como:

Separación de responsabilidades

Encapsulamiento

Manejo estructurado de excepciones

Uso de variables de entorno para credenciales

## 🛠 Tecnologías Utilizadas

Java

Maven

JDBC

Base de datos relacional

API Gemini

Git

## 🔄 Flujo de Funcionamiento

El usuario ingresa el nombre de un cantante.

La aplicación consulta la API de Gemini.

Se procesa la respuesta obtenida.

El usuario decide si desea almacenar la información.

Los datos se guardan en la base de datos.

Posteriormente pueden ser consultados desde la aplicación.

## 📂 Estructura del Proyecto

db-singers
│
├── src
│ └── main
│ ├── java
│ └── resources
│
├── pom.xml
├── .gitignore
└── mvnw / mvnw.cmd

## 🔐 Seguridad

Las credenciales no se encuentran en el código fuente.

Se utilizan variables de entorno para:

Conexión a base de datos

API key de Gemini

Ejemplo:
``
DB_URL=jdbc:tu_base_de_datos
DB_USER=usuario
DB_PASS=contraseña
AI_API_KEY=clave_api
``
Esto evita exponer información sensible en el repositorio.

## 🚀 Ejecución

Clonar repositorio:

````
git clone https://github.com/HenryPB123/db-singers.git

Entrar al proyecto:

cd db-singers

Compilar:

mvn clean package

Ejecutar:

java -jar target/db-singers.jar
````

## 🧪 Posibles Mejoras

Implementar pruebas unitarias.

Migrar a arquitectura más escalable (Spring Boot).

Implementar patrón DAO formal.

Agregar logs estructurados.

Implementar paginación en consultas.

Crear versión con API REST.

## 💡 Valor Técnico del Proyecto

Este proyecto demuestra:

Capacidad de integrar APIs externas.

Manejo de persistencia en base de datos.

Organización modular del código.

Uso adecuado de variables de entorno.

Buenas prácticas básicas de backend en Java.

## 👨‍💻 Autor

#### Henry Peralta Briceño
Desarrollador Backend en formación

