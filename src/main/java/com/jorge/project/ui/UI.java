package com.jorge.project.ui;

import com.jorge.project.controller.ProductController;
import com.jorge.project.model.Category;
import com.jorge.project.model.Product;


import java.util.List;
import java.util.Scanner;

public class UI {
    private final Scanner scanner;
    private final ProductController productController;

    private static final String MENU_OPTIONS = """
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
            """;
    private static final String SELECT_OPTION = "Seleccione una opción (1-9): ";
    private static final String INPUT_NAME = "Ingrese el nombre del producto: ";
    private static final String INPUT_PRICE = "Ingrese el precio del producto: ";
    private static final String INPUT_STOCK = "Ingrese el stock del producto: ";
    private static final String SELECT_CATEGORY = "Seleccione la categoría del producto: ";
    private static final String INPUT_ID = "Ingrese el ID del producto: ";

    public UI(ProductController productController) {
        this.scanner = new Scanner(System.in);
        this.productController = productController;
    }

    public void run() {
        System.out.println("¡Bienvenido al sistema de gestión de productos!");
        while (true) {
            displayMenu();
            String option = nonBlank(SELECT_OPTION, "opción");
            switch (option) {
                case "1" -> addProductView();
                case "2" -> deleteProductView();
                case "3" -> updateProductView();
                case "4" -> getProductsView();
                case "5" -> getProductsByNameView();
                case "6" -> sortProductsByNameView();
                case "7" -> sortProductsByPriceView();
                case "8" -> getProductsByPriceRangeView();
                case "9" -> {
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    return;
                }
                default -> showError("Opción inválida. Inténtelo de nuevo.");
            }
        }

    }

    public void addProductView() {
        String name = nonBlank(INPUT_NAME, "nombre");
        double price = validatePrice(INPUT_PRICE, "precio");
        int stock = validateStock(INPUT_STOCK, "stock");
        Category category = selectCategory(SELECT_CATEGORY, "categoría");
        Product product = new Product(name, price, stock, category);

        productController.createProduct(product);
    }

    public void deleteProductView() {

        String raw = nonBlank(INPUT_ID, "id");
        try {
            int id = Integer.parseInt(raw);
            if (productController.deleteProduct(id)) {
                System.out.println("Producto eliminado exitosamente.");
            } else {
                showError("No se encontró un producto con el ID proporcionado.");
            }
        } catch (NumberFormatException e) {
            showError("El ID debe ser un número entero válido. Inténtelo de nuevo.");
        }

    }

    public void updateProductView() {
        String rawId = nonBlank(INPUT_ID, "id");
        try {
            int id = Integer.parseInt(rawId);
            if (productController.getProductById(id) == null) {
                showError("No se encontró un producto con el ID proporcionado.");
                return;
            }
            String name = nonBlank(INPUT_NAME, "nombre");
            double price = validatePrice(INPUT_PRICE, "precio");
            int stock = validateStock(INPUT_STOCK, "stock");
            Category category = selectCategory(SELECT_CATEGORY, "categoría");
            Product product = new Product(name, price, stock, category);

            if (productController.updateProduct(id, product)) {
                System.out.println("Producto actualizado exitosamente.");
            } else {
                showError("No se encontró un producto con el ID proporcionado.");
            }
        } catch (NumberFormatException e) {
            showError("El ID debe ser un número entero válido. Inténtelo de nuevo.");
        }
    }

    public void getProductsView() {
        List<Product> products = productController.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public void getProductsByNameView() {
        String namePart = nonBlank("Ingrese el nombre o parte del nombre del producto a buscar: ", "nombre");
        List<Product> products = productController.getProductsByName(namePart);
        if (products.isEmpty()) {
            System.out.println("No se encontraron productos que coincidan con la búsqueda.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public void getProductsByPriceRangeView() {
        double minPrice = validatePrice("Ingrese el precio mínimo: ", "precio mínimo");
        double maxPrice = validatePrice("Ingrese el precio máximo: ", "precio máximo");
        if (minPrice > maxPrice) {
            showError("El precio mínimo no puede ser mayor que el precio máximo. Inténtelo de nuevo.");
            return;
        }
        List<Product> products = productController.getProductsByPriceRange(minPrice, maxPrice);
        if (products.isEmpty()) {
            System.out.println("No se encontraron productos en el rango de precios especificado.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public void sortProductsByNameView() {
        List<Product> products = productController.getAllProducts();
        products.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void sortProductsByPriceView() {
        List<Product> products = productController.getAllProducts();
        products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public String nonBlank(String message, String fieldName) {
        while (true) {
            System.out.println(message);
            String value = scanner.nextLine();
            if (value == null || value.trim().isBlank()) {
                showError("El %s no puede estar vacío. Inténtelo de nuevo.".formatted(fieldName));
            } else {
                return value.trim();
            }

        }
    }

    public double validatePrice(String message, String fieldName) {
        while (true) {
            String value = nonBlank(message, fieldName);
            try {
                double price = Double.parseDouble(value);
                if (price <= 0) {
                    showError("El %s debe ser un número positivo. Inténtelo de nuevo.".formatted(fieldName));
                } else {
                    return price;
                }
            } catch (NumberFormatException e) {
                showError("El %s debe ser un número válido. Inténtelo de nuevo.".formatted(fieldName));
            }
        }
    }

    public int validateStock(String message, String fieldName) {
        while (true) {
            String value = nonBlank(message, fieldName);
            try {
                int stock = Integer.parseInt(value);
                if (stock < 0) {
                    showError("El %s no puede ser negativo. Inténtelo de nuevo.".formatted(fieldName));
                } else {
                    return stock;
                }
            } catch (NumberFormatException e) {
                showError("El %s debe ser un número entero válido. Inténtelo de nuevo.".formatted(fieldName));
            }
        }
    }

    public Category selectCategory(String message, String fieldName) {
        while (true) {
            System.out.println(message);
            for (int i = 0; i < Category.values().length; i++) {
                System.out.printf("%d. %s%n", i + 1, Category.values()[i]);
            }
            String value = nonBlank("Ingrese el número de la categoría: ", fieldName);
            switch (value) {

                case "1" -> {
                    return Category.ELECTRONIC;
                }
                case "2" -> {
                    return Category.HOME;
                }
                case "3" -> {
                    return Category.OFFICE;
                }
                case "4" -> {
                    return Category.FASHION;
                }
                case "5" -> {
                    return Category.TOYS;
                }
                case "6" -> {
                    return Category.SPORTS;
                }
                case "7" -> {
                    return Category.OTHER;
                }
                default -> showError("Categoría inválida. Inténtelo de nuevo.");
            }
        }
    }


    public void displayMenu() {
        System.out.println("\n-----------------------------------");
        System.out.println(MENU_OPTIONS);
    }

    public void showError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }


}
