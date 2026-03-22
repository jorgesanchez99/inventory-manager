# 📦 Inventory Manager

Sistema de gestión de inventario de productos desarrollado en Java con arquitectura MVC y persistencia de datos con
PostgreSQL.

## 📋 Descripción

Inventory Manager es una aplicación de consola que permite gestionar un inventario de productos de manera sencilla y
eficiente. El sistema implementa operaciones CRUD completas (Crear, Leer, Actualizar, Eliminar) con validaciones
robustas, manejo de excepciones personalizadas y **persistencia de datos en base de datos** usando PostgreSQL.

## 🛠️ Tecnologías Utilizadas

- **Java 23**: Lenguaje de programación principal
- **Maven 3.9.11**: Gestión de dependencias y construcción del proyecto
- **PostgreSQL**: Sistema de gestión de bases de datos relacional para persistencia de datos
- **dotenv-java**: Manejo de variables de entorno
- **Junit 5**: Framework de pruebas unitarias
- **Mockito**: Framework de mocking para pruebas unitarias
- **Hikaricp**: Pool de conexiones para optimizar el acceso a la base de datos

## 🏗️ Arquitectura

El proyecto sigue el patrón de arquitectura **MVC (Modelo-Vista-Controlador)**:

### Modelo (`model/`)

- **Product**: Clase que representa un producto con sus atributos y validaciones
- **Category**: Enum con las categorías disponibles

### Vista (`ui/`)

- **UI**: Interfaz de usuario por consola que interactúa con el usuario

### Controlador (`controller/`)

- **ProductController**: Gestiona las peticiones entre la UI y el servicio

### Repositorio (`repository/`)

- **IProductRepository**: Define el contrato de operaciones de persistencia
- **ProductRepositoryImpl**: Implementa la lógica de acceso a datos usando PostgreSQL

### Servicio (`service/`)

- **IProductService**: Define el contrato de operaciones
- **ProductServiceImpl**: Implementa la lógica de negocio

### Database(`db/`)

- **DatabaseConnection**: Gestiona la conexión a la base de datos PostgreSQL

### Excepciones (`exceptions/`)

- **ProductException**: Manejo de errores personalizados

## 🚀 Instalación y Ejecución

### Requisitos Previos

- Java 23 o superior
- Maven 3.6 o superior

### Pasos para ejecutar

1. **Clonar o descargar el proyecto**
    ```powershell
    git clone https://github.com/jorgesanchez99/inventory-manager.git
   ```

2. **Navegar al directorio del proyecto**
   ```powershell
   cd inventory-manager
   ```

3. **Tener PostgreSQL y crear la base de datos con la tabla products**
    ```sql
    CREATE TABLE products (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        price DECIMAL(10,2) NOT NULL,
        stock INT NOT NULL,
        category VARCHAR(50) NOT NULL
    );
    ```

4. **Crear el .env basandose en el .env.example**

5. **Correr la aplicación**
    ```powershell
    mvn exec:java -Dexec.mainClass="com.jorge.project.App"
    ```

## 👨‍💻 Autor

- Jorge Sanchez - Proyecto de estudio en Java
- [GitHub](https://github.com/jorgesanchez99)
- [LinkedIn](https://www.linkedin.com/in/jorge-anthony-sanchez-chavez/)

## 📄 Licencia

Este proyecto es de código abierto y está disponible para cualquier uso.

---

⭐ **¡Gracias!** ⭐

