# 📦 Inventory Manager

Sistema de gestión de inventario de productos desarrollado en Java con arquitectura MVC y persistencia de datos en JSON.

## 📋 Descripción

Inventory Manager es una aplicación de consola que permite gestionar un inventario de productos de manera sencilla y eficiente. El sistema implementa operaciones CRUD completas (Crear, Leer, Actualizar, Eliminar) con validaciones robustas, manejo de excepciones personalizadas y **persistencia de datos en archivo JSON** usando Gson.

## ✨ Características

- ✅ **Gestión completa de productos**: Crear, listar, actualizar y eliminar productos
- 💾 **Persistencia de datos**: Los productos se guardan automáticamente en `products.json`
- 🔄 **Carga automática**: Al iniciar la aplicación, los productos se cargan desde el archivo JSON
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
- **Gson 2.13.2**: Serialización y deserialización de objetos Java a JSON
- **JSON**: Formato de persistencia de datos

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
│   └── products.json                                   # Archivo de persistencia de datos
├── pom.xml                                             # Configuración de Maven
└── README.md                                           # Este archivo
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
- **ProductServiceImpl**: Implementa la lógica de negocio y persistencia de datos

### Excepciones (`exceptions/`)
- **ProductException**: Manejo de errores personalizados

## 💾 Persistencia de Datos

El sistema utiliza **Gson** para guardar y cargar productos en formato JSON.

### Archivo `products.json`

Los productos se almacenan en `src/products.json` con la siguiente estructura:

```json
[
  {
    "id": 1,
    "name": "Laptop Lenovo ThinkPad",
    "price": 3200.0,
    "stock": 5,
    "category": "ELECTRONIC"
  },
  {
    "id": 2,
    "name": "Silla Ergonómica de Oficina",
    "price": 450.0,
    "stock": 12,
    "category": "OFFICE"
  },
  {
    "id": 3,
    "name": "Polera Deportiva Nike",
    "price": 159.9,
    "stock": 20,
    "category": "FASHION"
  },
  {
    "id": 4,
    "name": "LEGO Star Wars X-Wing",
    "price": 899.0,
    "stock": 8,
    "category": "TOYS"
  },
  {
    "id": 5,
    "name": "Pelota de Fútbol Profesional",
    "price": 120.0,
    "stock": 15,
    "category": "SPORTS"
  }
]
```

### Funcionamiento

- **Carga automática**: Al iniciar la aplicación, `ProductServiceImpl` lee el archivo `products.json` y carga todos los productos existentes
- **Guardado automático**: Cada operación que modifica datos (crear, actualizar, eliminar) guarda automáticamente los cambios en el archivo JSON
- **IDs secuenciales**: El sistema calcula automáticamente el siguiente ID basándose en el ID más alto existente + 1
- **Manejo de errores**: Si el archivo no existe, se crea uno nuevo al agregar el primer producto
- **Pretty Printing**: El JSON se formatea con indentación para mejor legibilidad

### Ventajas

✅ Los datos persisten entre ejecuciones de la aplicación  
✅ Formato JSON legible y editable manualmente  
✅ No requiere base de datos externa  
✅ Fácil de respaldar y transferir  
✅ Compatible con cualquier editor de texto  

## 🚀 Instalación y Ejecución

### Requisitos Previos

- Java 23 o superior
- Maven 3.6 o superior

### Pasos para ejecutar

1. **Clonar o descargar el proyecto**

2. **Navegar al directorio del proyecto**
   ```powershell
   cd E:\IntelliProjects\JavaEstudio\inventory-manager
   ```

3. **Compilar el proyecto con Maven**
   ```powershell
   mvn clean compile
   ```

4. **Ejecutar la aplicación**
   ```powershell
   mvn exec:java -Dexec.mainClass="com.jorge.project.App"
   ```

   O alternativamente:
   ```powershell
   java -cp target/classes com.jorge.project.App
   ```

## 📖 Guía de Uso

### Menú Principal

Al iniciar la aplicación, verás el siguiente menú:

```
¡Bienvenido al sistema de gestión de productos!

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

Seleccione una opción (1-9):
```

### Operaciones Disponibles

#### 1. Agregar Producto
- Ingresa el nombre del producto
- Especifica el precio (debe ser mayor que 0)
- Define el stock (no puede ser negativo)
- Selecciona una categoría del menú
- El producto se guarda automáticamente en `products.json`

#### 2. Eliminar Producto
- Proporciona el ID del producto a eliminar
- El sistema confirmará la eliminación
- Los cambios se guardan automáticamente

#### 3. Actualizar Producto
- Ingresa el ID del producto a actualizar
- Proporciona los nuevos datos (nombre, precio, stock, categoría)
- Los cambios se guardan automáticamente

#### 4. Ver Productos
- Muestra todos los productos registrados en el inventario
- Formato: `Producto #ID: Nombre | Precio: $XX.XX | Stock: X unidades | Categoría: XXX`

#### 5. Buscar por Nombre
- Ingresa una palabra o parte del nombre
- El sistema mostrará todos los productos que coincidan (búsqueda insensible a mayúsculas)

#### 6. Ordenar por Nombre
- Muestra los productos ordenados alfabéticamente (A-Z)

#### 7. Ordenar por Precio
- Muestra los productos ordenados de menor a mayor precio

#### 8. Filtrar por Rango de Precio
- Ingresa el precio mínimo y máximo
- El sistema mostrará productos dentro de ese rango (inclusive)

#### 9. Salir
- Cierra la aplicación
- Todos los datos quedan guardados en `products.json`

## 💡 Ejemplo de Uso

```
¡Bienvenido al sistema de gestión de productos!
Opciones disponibles:
1. Agregar producto
2. Eliminar producto
...
Seleccione una opción (1-9): 1

Ingrese el nombre del producto: Laptop Dell XPS 15
Ingrese el precio del producto: 1299.99
Ingrese el stock del producto: 10
Seleccione la categoría del producto:
1. Productos electrónicos
2. Productos para el hogar
3. Productos de oficina
4. Productos de moda
5. Juguetes y juegos
6. Artículos deportivos
7. Otras categorías
Seleccione una opción (1-7): 1

Producto creado exitosamente con ID: 6
```

Después de este proceso, el archivo `products.json` se actualiza automáticamente con el nuevo producto.

## ✅ Validaciones Implementadas

- **Nombre**: No puede estar vacío ni contener solo espacios
- **Precio**: Debe ser un número positivo mayor que 0
- **Stock**: No puede ser negativo
- **Categoría**: Debe ser una de las opciones predefinidas
- **ID**: Debe ser un número entero positivo

## 🗃️ Datos de Ejemplo

El proyecto incluye un archivo `products.json` con 5 productos de ejemplo:

| ID | Producto | Precio | Stock | Categoría |
|----|----------|--------|-------|-----------|
| 1 | Laptop Lenovo ThinkPad | $3,200.00 | 5 | Electrónicos |
| 2 | Silla Ergonómica de Oficina | $450.00 | 12 | Oficina |
| 3 | Polera Deportiva Nike | $159.90 | 20 | Moda |
| 4 | LEGO Star Wars X-Wing | $899.00 | 8 | Juguetes |
| 5 | Pelota de Fútbol Profesional | $120.00 | 15 | Deportes |

Puedes modificar, eliminar o agregar productos directamente editando el archivo JSON o usando la aplicación.

## 🔧 Personalización

### Agregar Nuevas Categorías

Edita el archivo `Category.java` y añade nuevas entradas al enum:

```java
NEW_CATEGORY("Descripción de la nueva categoría")
```

### Modificar Validaciones

Las validaciones se encuentran en la clase `Product.java` en los métodos privados de validación:
- `nonBlank()` - Valida campos de texto
- `positivePrice()` - Valida el precio
- `nonNegativeStock()` - Valida el stock
- `validateCategory()` - Valida la categoría

### Cambiar ubicación del archivo JSON

Edita la constante `PATH` en `ProductServiceImpl.java`:

```java
private final String PATH = "ruta/deseada/products.json";
```

## 📝 Notas Técnicas

- **Persistencia**: Los productos se guardan en `src/products.json` usando Gson
- **Estructura de datos**: Internamente se usa un `ArrayList<Product>`
- **IDs automáticos**: Se asignan de forma incremental basándose en el ID máximo existente + 1
- **Validaciones**: Lanzan `ProductException` para errores de datos
- **Interfaz de usuario**: Utiliza `Scanner` para la entrada de datos por consola
- **Formato JSON**: Pretty printing habilitado para mejor legibilidad del archivo
- **Manejo de errores**: Mensajes descriptivos en consola para errores de I/O

## ⚠️ Consideraciones

- El archivo `products.json` debe estar en la ruta `src/products.json` para que la aplicación funcione correctamente
- Si el archivo no existe al iniciar, se creará automáticamente al agregar el primer producto
- Los cambios se guardan inmediatamente después de cada operación
- La edición manual del archivo JSON es posible, pero debe mantener el formato correcto

## 🤝 Contribuciones

Este es un proyecto educativo. Siéntete libre de:
- Reportar bugs
- Sugerir nuevas características
- Mejorar el código existente
- Agregar nuevas funcionalidades

## 👨‍💻 Autor

Jorge - Proyecto de estudio en Java

## 📄 Licencia

Este proyecto es de código abierto y está disponible para uso educativo.

---

⭐ **¡Gracias por usar Inventory Manager!** ⭐

