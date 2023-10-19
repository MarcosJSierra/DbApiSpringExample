# DbApiSpringExample

Este proyecto plantea ser un ejemplo de aplicación de Srping Boot 3.x con acceso a base de datos como ejemplo para post de blog.

## Datos Generales del proyecto

El proyecto al momento de realizar la actualización del READMe aplica las siguientes tecnologias.

- Spring boot 3.x
- Java 17
- MariaDB
- Hibernate

## DB

Se incluye una copia del esquema de base de datos para implementación de la aplicación. Origilamente se construyo para trabajar con mariadb y una distribución Arch Linux por lo que se debe tomar en cuenta que Linux reconoce las diferencies entre mayusculas y minusculas. Finalmente se dejo un respaldo del esquema de base de datos que tenia por nombre [__cuentas__](./db/cuentas.sql), En caso de cambiar el nombre del esquema se puede realizar los cambios concernientes dentro del archivo de propiedades [application.properties](./src/main/resources/application.properties)
