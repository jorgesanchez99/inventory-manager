package com.jorge.project.db;


import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

     // static -> pertenecen a la clase
    // final ->  constantes que no cambian
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL  = dotenv.get("DB_URL");
    private static final String USER  = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

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