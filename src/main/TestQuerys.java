package main;

import config.ConexionDB;
import dao.PacienteDao;
import dao.PacienteDaoImpl;
import entities.Paciente;

import java.sql.Connection;
import java.util.List;

// clase para testear las impl

public class TestQuerys {
    public static void main(String[] args) throws Exception {
        try (Connection con = ConexionDB.getConnection()) {
            PacienteDao dao = new PacienteDaoImpl();

            List<Paciente> pacientes = dao.obtenerTodos(con);

            System.out.println(" Pacientes (primeros 10):");
            for (int i = 0; i < Math.min(10, pacientes.size()); i++) {
                System.out.println(pacientes.get(i));
            }
            System.out.println("Total: " + pacientes.size());
        }
    }
}


