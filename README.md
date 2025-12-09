# 📦 Inventory Manager

Sistema de gestión de inventario de productos desarrollado en Java con arquitectura MVC.

## 📋 Descripción

Inventory Manager es una aplicación de consola que permite gestionar un inventario de productos de manera sencilla y eficiente. El sistema implementa operaciones CRUD completas (Crear, Leer, Actualizar, Eliminar) con validaciones robustas y manejo de excepciones personalizadas.

## ✨ Características

- ✅ **Gestión completa de productos**: Crear, listar, actualizar y eliminar productos
- 🔍 **Búsqueda avanzada**: Buscar productos por nombre o rango de precios
- 📊 **Ordenamiento**: Ordenar productos por nombre o precio
- 🏷️ **Categorías predefinidas**: 7 categorías de productos disponibles
- ✔️ **Validaciones robustas**: Validación de datos de entrada con mensajes de error claros
- 🎯 **Arquitectura MVC**: Código organizado y mantenible
- 🔢 **IDs automáticos**: Asignación automática de IDs únicos para cada producto

## 🗂️ Categorías Disponibles

1. **Electrónicos** - Productos electrónicos
2. **Hogar** - Productos para el hogar
3. **Oficina** - Productos de oficina
4. **Moda** - Productos de moda
5. **Juguetes** - Juguetes y juegos
6. **Deportes** - Artículos deportivos
7. **Otros** - Otras categorías

## 🛠️ Tecnologías Utilizadas

- **Java 23**: Lenguaje de programación principal
- **Maven**: Gestión de dependencias y construcción del proyecto
- **JUnit**: Framework para pruebas unitarias

## 📁 Estructura del Proyecto

```
inventory-manager/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── jorge/
│   │               └── project/
│   │                   ├── App.java                    # Clase principal
│   │                   ├── controller/
│   │                   │   └── ProductController.java  # Controlador de productos
│   │                   ├── exceptions/
│   │                   │   └── ProductException.java   # Excepciones personalizadas
│   │                   ├── model/
│   │                   │   ├── Category.java           # Enum de categorías
│   │                   │   └── Product.java            # Modelo de producto
│   │                   ├── service/
│   │                   │   ├── IProductService.java    # Interface del servicio
│   │                   │   └── ProductServiceImpl.java # Implementación del servicio
│   │                   └── ui/
│   │                       └── UI.java                 # Interfaz de usuario
│   └── test/
│       └── java/
│           └── com/
│               └── jorge/
│                   └── project/
│                       └── AppTest.java               # Pruebas unitarias
├── pom.xml                                            # Configuración de Maven
└── README.md                                          # Este archivo
```

## 🏗️ Arquitectura

El proyecto sigue el patrón de arquitectura **MVC (Modelo-Vista-Controlador)**:

### Modelo (`model/`)
- **Product**: Clase que representa un producto con sus atributos y validaciones
- **Category**: Enum con las categorías disponibles

### Vista (`ui/`)
- **UI**: Interfaz de usuario por consola que interactúa con el usuario

### Controlador (`controller/`)
- **ProductController**: Gestiona las peticiones entre la UI y el servicio

### Servicio (`service/`)
- **IProductService**: Define el contrato de operaciones
- **ProductServiceImpl**: Implementa la lógica de negocio

### Excepciones (`exceptions/`)
- **ProductException**: Manejo de errores personalizados

## 🚀 Instalación y Ejecución

### Requisitos Previos

- Java 23 o superior
- Maven 3.6 o superior

### Pasos para ejecutar

1. **Clonar o descargar el proyecto**

2. **Navegar al directorio del proyecto**
   ```bash
   cd inventory-manager
   ```

3. **Compilar el proyecto con Maven**
   ```bash
   mvn clean compile
   ```

4. **Ejecutar la aplicación**
   ```bash
   mvn exec:java -Dexec.mainClass="com.jorge.project.App"
   ```

   O alternativamente:
   ```bash
   java -cp target/classes com.jorge.project.App
   ```

5. **Ejecutar las pruebas**
   ```bash
   mvn test
   ```

## 📖 Guía de Uso

### Menú Principal

Al iniciar la aplicación, verás el siguiente menú:

```
Opciones disponibles:
1. Agregar producto
2. Eliminar producto
3. Actualizar producto
4. Ver productos
5. Buscar productos por nombre
6. Ordenar productos por nombre
7. Ordenar productos por precio
8. Filtrar productos por rango de precio
9. Salir
```

### Operaciones Disponibles

#### 1. Agregar Producto
- Ingresa el nombre del producto
- Especifica el precio (debe ser mayor que 0)
- Define el stock (no puede ser negativo)
- Selecciona una categoría del menú

#### 2. Eliminar Producto
- Proporciona el ID del producto a eliminar
- El sistema confirmará la eliminación

#### 3. Actualizar Producto
- Ingresa el ID del producto a actualizar
- Proporciona los nuevos datos (nombre, precio, stock, categoría)

#### 4. Ver Productos
- Muestra todos los productos registrados en el inventario
- Formato: `Producto #ID: Nombre | Precio: $XX.XX | Stock: X unidades | Categoría: XXX`

#### 5. Buscar por Nombre
- Ingresa una palabra o parte del nombre
- El sistema mostrará todos los productos que coincidan

#### 6. Ordenar por Nombre
- Muestra los productos ordenados alfabéticamente

#### 7. Ordenar por Precio
- Muestra los productos ordenados de menor a mayor precio

#### 8. Filtrar por Rango de Precio
- Ingresa el precio mínimo y máximo
- El sistema mostrará productos dentro de ese rango

#### 9. Salir
- Cierra la aplicación

## 💡 Ejemplo de Uso

```
¡Bienvenido al sistema de gestión de productos!
Opciones disponibles:
1. Agregar producto
...
Seleccione una opción (1-9): 1

Ingrese el nombre del producto: Laptop Dell XPS 15
Ingrese el precio del producto: 1299.99
Ingrese el stock del producto: 10
Seleccione la categoría del producto:
1. Productos electrónicos
2. Productos para el hogar
...
Seleccione una opción (1-7): 1

Producto creado exitosamente con ID: 1
```

## ✅ Validaciones Implementadas

- **Nombre**: No puede estar vacío ni contener solo espacios
- **Precio**: Debe ser un número positivo mayor que 0
- **Stock**: No puede ser negativo
- **Categoría**: Debe ser una de las opciones predefinidas
- **ID**: Debe ser un número entero positivo

## 🔧 Personalización

### Agregar Nuevas Categorías

Edita el archivo `Category.java` y añade nuevas entradas al enum:

```java
NEW_CATEGORY("Descripción de la nueva categoría")
```

### Modificar Validaciones

Las validaciones se encuentran en la clase `Product.java` en los métodos privados de validación.

## 📝 Notas Técnicas

- Los productos se almacenan en memoria (ArrayList)
- Los IDs se asignan automáticamente de forma incremental
- Las validaciones lanzan `ProductException` para errores de datos
- El sistema utiliza `Scanner` para la entrada de datos por consola

## 🤝 Contribuciones

Este es un proyecto educativo. Siéntete libre de:
- Reportar bugs
- Sugerir nuevas características
- Mejorar el código existente

## 👨‍💻 Autor

Jorge - Proyecto de estudio en Java

## 📄 Licencia

Este proyecto es de código abierto y está disponible para uso educativo.

---

⭐ **¡Gracias por usar Inventory Manager!** ⭐

