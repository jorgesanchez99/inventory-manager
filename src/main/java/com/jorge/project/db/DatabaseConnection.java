package com.jorge.project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // static -> pertenecen a la clase
    // final ->  constantes que no cambian
    private static final String URL = "jdbc:postgresql://localhost:5432/inventory_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345";

    // La única instancia de esta clase
    private static DatabaseConnection instance;

    // conexión compartida
    private Connection connection;

    // Private para que nadie pueda hacer new DatabaseConnection()
    private DatabaseConnection() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Retorna siempre la misma instancia
    public static Connection getConnection() {
        try {
            if (instance == null || instance.connection.isClosed()) {
                instance = new DatabaseConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos: " + e.getMessage());
        }
        return instance.connection;
    }
}