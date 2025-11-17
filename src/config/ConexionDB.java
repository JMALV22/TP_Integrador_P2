package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL =
            "jdbc:mysql://localhost:3306/pacienteHistoriaClinica"
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASS = "";

    // esto deja una conexion lista para usar
    public static Connection getConnection() throws SQLException {

        try {
            // carga del driver jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver JDBC", e);
        }

        return DriverManager.getConnection(URL, USER, PASS);
    }
}



