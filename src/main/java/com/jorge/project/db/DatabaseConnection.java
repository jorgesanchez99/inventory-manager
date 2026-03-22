package com.jorge.project.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    // static -> pertenecen a la clase
    // final ->  constantes que no cambian
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    private static HikariDataSource dataSource;

    static {
        System.out.println("Bloque static: Pool de conexiones inicializado");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);

        config.setMaximumPoolSize(10); //maximo 10 conexiones
        config.setMinimumIdle(2); //minimo 2 conexiones inactivas

        config.setIdleTimeout(30000); //tiempo de inactividad
        config.setConnectionTimeout(30000); //tiempo de espera para obtener una conexion

        config.setLeakDetectionThreshold(15000); //deteccion de fugas de conexion

        dataSource = new HikariDataSource(config);
    }


    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("Connection pool cerrado.");
        }
    }
}