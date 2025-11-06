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

            // descometar funcion para testearla

            // test 1 simular SELECT * FROM paciente
            /*
            List<Paciente> pacientes = dao.obtenerTodos(con);
            System.out.println(" Pacientes (primeros 10):");
            for (int i = 0; i < Math.min(10, pacientes.size()); i++) {
                System.out.println(pacientes.get(i));
            }
            System.out.println("Total: " + pacientes.size());
            */

            // test 2 buscar por id
            /*
            Long idBuscado = 5L; // 5L porque el tipo es Long
            Paciente p = dao.obtenerPorId(idBuscado, con);

            if (p != null) {
                System.out.println(" Paciente encontrado:");
                System.out.println(p);
            } else {
                System.out.println(" No existe paciente con ID = " + idBuscado);
            }
            */
        }
    }
}


