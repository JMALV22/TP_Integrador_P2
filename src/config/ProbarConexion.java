package config;

import java.sql.Connection;
import java.sql.SQLException;

public class ProbarConexion {
    public static void main(String[] args) {

        try (Connection con = ConexionDB.getConnection()) {

            if (con != null) {
                System.out.println("🚀 Conexión exitosa a la base de datos!");
            } else {
                System.out.println("⚠️ No se pudo establecer la conexión.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error durante la conexión:");
            e.printStackTrace();
        }
    }
}
