package com.jorge.project.model;

public enum Category {

    ELECTRONIC("Productos electrónicos"),
    HOME("Productos para el hogar"),
    OFFICE("Productos de oficina"),
    FASHION("Productos de moda"),
    TOYS("Juguetes y juegos"),
    SPORTS("Artículos deportivos"),
    OTHER("Otras categorías");

    private final String message;

    Category(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}

