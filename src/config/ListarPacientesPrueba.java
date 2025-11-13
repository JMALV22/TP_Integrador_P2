package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;   // para java.sql.Date

public class ListarPacientesPrueba {
    public static void main(String[] args) {


        String sql = "SELECT id_paciente, nombre, apellido, dni, fecha_nacimiento " +
                "FROM paciente " +
                "WHERE eliminado = FALSE " +
                "ORDER BY id_paciente";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println(" Lista de pacientes:");
            System.out.println("-----------------------------");

            while (rs.next()) {
                int id = rs.getInt("id_paciente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String dni = rs.getString("dni");
                Date fechaNac = rs.getDate("fecha_nacimiento");   // puede venir null

                // lo mostramos cuidando null
                String fechaTexto = (fechaNac != null) ? fechaNac.toString() : "sin fecha";

                System.out.println(id + " - " + nombre + " " + apellido +
                        " (DNI: " + dni + ") - FechaNac.: " + fechaTexto);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar pacientes:");
            e.printStackTrace();
        }
    }
}


