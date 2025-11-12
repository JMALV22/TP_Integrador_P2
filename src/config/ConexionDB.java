package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Datos de conexión (ajustados a tu entorno y nueva base)
    private static final String URL =
            "jdbc:mysql://localhost:3306/pacienteHistoriaClinica"
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = ""; // sin contraseña

    // Método que devuelve una conexión lista para usar
    public static Connection getConnection() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión establecida correctamente");
            return conexion;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos:");
            e.printStackTrace();
            return null;
        }
    }
}


