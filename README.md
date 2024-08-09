# Proyecto DiscoNorte

Este proyecto tiene como objetivo [breve descripción del proyecto]. A continuación se detallan las tareas completadas durante el desarrollo:

## Tareas Completadas

### 1. Estandarización del archivo `init.sql`
Se ha realizado una limpieza y formateo del archivo `init.sql` para asegurar la consistencia y legibilidad del código. Las sentencias SQL ahora están correctamente estructuradas, con una separación clara entre cada inserción, facilitando su comprensión y mantenimiento.

### 2. Agregar credenciales de uso al `application.properties`
Se han añadido las credenciales necesarias al archivo `application.properties` para configurar el acceso a la base de datos y otros recursos esenciales. Esto asegura que la aplicación puede conectarse correctamente a los servicios requeridos durante su ejecución.

### 3. Generar el archivo `docker-compose.yml`
Se ha generado un archivo `docker-compose.yml` que define los servicios necesarios para el entorno de desarrollo. Este archivo permite la fácil creación y gestión de contenedores Docker, facilitando la puesta en marcha de un entorno consistente para todos los desarrolladores y en diferentes entornos.

### 4. Generar las entidades necesarias para ocupar en la app
Se han creado las entidades Java correspondientes a las tablas de la base de datos. Estas entidades están diseñadas utilizando las dependencias de `Lombok` para minimizar el código boilerplate y `Jakarta Bean Validation` para garantizar que los datos cumplen con las restricciones definidas en la base de datos. Las entidades incluyen relaciones mapeadas con JPA para asegurar la integridad de los datos en el sistema.

## 5. Generación de repositorios para cada entidad
Se crearon los repositorios para cada entidad, con los siguientes métodos preparados para su futura implementación:

    ```
    Entidad create(Entidad entidad);
    Optional<Entidad> findById(Long id);
    List<Entidad> findAll();
    Entidad update(Entidad entidad);
    void delete(Miembro entidad);
    ```

