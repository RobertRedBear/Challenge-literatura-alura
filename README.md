Challenge Literatura - LiterAlura 📚
Este proyecto es un buscador de libros interactivo desarrollado con Java y Spring Boot, que consume datos de la API externa Gutendex y los persiste en una base de datos relacional PostgreSQL.

El objetivo principal es aplicar conceptos avanzados de persistencia de datos (JPA/Hibernate), consumo de APIs REST y manipulación de colecciones.

🚀 Funcionalidades
Búsqueda de libros por título: Realiza una consulta a la API de Gutendex, procesa el JSON y guarda el libro junto con su autor en la base de datos.

Gestión de Autores: Persistencia de datos biográficos (nombre, año de nacimiento y muerte), soportando años históricos (A.C./D.C.).

Listado Completo: Visualización de todos los libros registrados con su respectivo autor e idioma.

Consultas Especializadas:

Filtro por idiomas específicos (Español, Inglés, Francés, Portugués) mediante el uso de Enums.

Búsqueda de libros por nombre del autor.

Top 10 de libros más descargados según los datos de la API.

🛠️ Tecnologías Utilizadas
Java 17+

Spring Boot 3.x

Spring Data JPA: Para la gestión de la persistencia y relaciones entre entidades.

PostgreSQL: Base de datos relacional para el almacenamiento de datos.

Jackson: Para el mapeo de datos JSON (records y DTOs).

API Gutendex: Fuente externa de datos bibliográficos.

🏗️ Estructura del Modelo
El proyecto implementa una relación Muchos a Uno (@ManyToOne) entre Libro y Autor:

Un Autor puede tener múltiples libros registrados en el sistema.

Un Libro pertenece a un único autor (según el primer resultado de búsqueda).

Se utiliza CascadeType.ALL para asegurar que al guardar un libro, el autor se cree automáticamente en la base de datos si es necesario.

💻 Cómo Ejecutar el Proyecto
Clona el repositorio.

Configura tus credenciales de PostgreSQL en el archivo src/main/resources/application.properties.

Asegúrate de tener instalada la base de datos con el nombre indicado en la configuración.

Ejecuta la aplicación desde tu IDE.
