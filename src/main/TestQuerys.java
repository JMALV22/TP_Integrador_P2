package main;

import config.ConexionDB;
import dao.HistoriaClinicaDao;
import dao.HistoriaClinicaDaoImpl;
import dao.PacienteDao;
import dao.PacienteDaoImpl;
import entities.HistoriaClinica;
import entities.Paciente;

import java.sql.Connection;
import java.util.List;

// clase para testear las impl

public class TestQuerys {
    public static void main(String[] args) throws Exception {
        try (Connection con = ConexionDB.getConnection()) {
            PacienteDao dao = new PacienteDaoImpl();

            // descometar funcion para testearla


            // ***************** TEST DE PACIENTE *****************

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
            Paciente p = dao.obtenerPorId(idBuscado, con); // con = conexion  jdbc a BD

            if (p != null) {
                System.out.println(" Paciente encontrado:");
                System.out.println(p);
            } else {
                System.out.println(" No existe paciente con ID = " + idBuscado);
            }
            */

                        // test 3: buscar por DNI
           /*
            String dniBuscado = "20000005";
            Paciente p2 = dao.obtenerPorDni(dniBuscado, con); // con = conexion  jdbc a BD

            if (p2 != null) {
                System.out.println(" Paciente por DNI encontrado:");
                System.out.println(p2);
            } else {
                System.out.println(" No existe paciente con DNI = " + dniBuscado);
            }
            */

                        // test 4: buscar por apellido
            /*
            String apellidoBuscado = "PacApellido123";
            List<Paciente> encontrados = dao.buscarPorApellido(apellidoBuscado, con); // con = conexion jdbc a BD

            if (encontrados.isEmpty()) {
                System.out.println(" No se encontraron pacientes con apellido: " + apellidoBuscado);
            } else {
                System.out.println(" Pacientes encontrados (" + encontrados.size() + "):");
                for (Paciente p : encontrados) {
                    System.out.println(p);
                }
            }
            */

                        // test 5: actualizar paciente por ID
            /*
            Long idEditar = 5L; // cambiar por un ID que exista
            Paciente p = dao.obtenerPorId(idEditar, con); // con = conexion  jdbc a BD

            if (p == null) {
                System.out.println("️ No existe paciente con ID = " + idEditar);
            } else {
                System.out.println("Antes de actualizar:");
                System.out.println(p);

                // mostramos datos cambiados
                p.setNombre("NombreActualizado5");
                p.setApellido("ApellidoActualizado5");
                // mantener el mismo DNI o usar uno que no choque con otro
                p.setDni(p.getDni());

                boolean ok = dao.actualizar(p, con);
                System.out.println(ok ? " Actualizado correctamente" : "️ No se pudo actualizar"); // if ternario

                // verificar
                Paciente verif = dao.obtenerPorId(idEditar, con);
                System.out.println("Despues de actualizar:");
                System.out.println(verif);
            }
            */

                        // test 6: dar de baja
            /*
            Long idBorrar = 5L; // elegir id

            Paciente antes = dao.obtenerPorId(idBorrar, con); // con = conexion  jdbc a BD
            if (antes == null) { // si no existe id
                System.out.println(" No existe paciente activo con ID = " + idBorrar);
            } else {
                System.out.println("Antes de eliminar: " + antes);

                boolean ok = dao.eliminar(idBorrar, con);
                System.out.println(ok ? "Baja exitosa" :
                                       " No se pudo realizar la baja (ya estaba de baja o no existe)");

                // al tener WHERE eliminado = TRUE, no debería aparecer
                Paciente despues = dao.obtenerPorId(idBorrar, con);
                System.out.println("Después de baja (buscarPorId): " +
                        (despues == null ? "no aparece " : despues)); // if ternario
            }
            */

                        // test 7: insertar paciente (devuelve la entidad con ID)
            /*
            Paciente nuevo = new Paciente();
            nuevo.setNombre("Luciano");
            nuevo.setApellido("Andrelo");
            nuevo.setDni("99001234");
            nuevo.setFechaNacimiento(java.time.LocalDate.of(1991, 12, 22));

            Paciente insertado = dao.insertar(nuevo, con);
            System.out.println("Insertado con ID = " + insertado.getId());

            // verificación
            Paciente verif = dao.obtenerPorId(insertado.getId(), con);
            System.out.println("Verificación: " + verif);
            */

            // ***************** TEST DE HISTORIA CLINICA *****************

            // test 1: SELECT Historia Clínica por ID

        /*
            // ID a consultar en la tabla historia_clinica
            Long idPrueba = 10L;   // L por Long

            // creamos el DAO
            HistoriaClinicaDao daoHC = new HistoriaClinicaDaoImpl();

            // ejecutamos la consulta usando la misma conexión "con"
            HistoriaClinica hc = daoHC.obtenerPorId(idPrueba, con);

            // si hc NO es null imprime el objeto
            // si hc ES null imprime "No encontrada"
            System.out.println(hc != null ? hc : "No encontrada");
        */

            // test 2: SELECT Historia Clinica por nro_historia
/*
            // nro de historia clínica a consultar
            String nroHistoria = "HC-00042";

            // creamos el DAO
            HistoriaClinicaDao daoHC = new HistoriaClinicaDaoImpl();

            // ejecutamos la consulta usando la misma conexión "con"
            HistoriaClinica hc = daoHC.obtenerPorNroHistoria(nroHistoria, con);

            // si hc NO es null imprime el objeto encontrado
            // si hc ES null imprime mensaje indicando que no existe
            System.out.println(hc != null ? ("Encontrada: " + hc) : " No existe historia con nro = "
                    + nroHistoria); // if ternario
*/

            // test 3: SELECT * FROM Historia Clinica
/*
            // creamos el DAO
            HistoriaClinicaDao daoHC = new HistoriaClinicaDaoImpl();

            // ejecutamos la consulta con la misma con
            List<HistoriaClinica> historias = daoHC.obtenerTodos(con);

            // si la lista no esta vacía, mostramos cada una
            if (!historias.isEmpty()) {
                System.out.println(" Lista completa de Historias Clínicas:");
                System.out.println("--------------------------------------");
                for (HistoriaClinica hc : historias) {
                    System.out.println(hc);
                }
                System.out.println(" Total de historias encontradas: " + historias.size());
            } else {
                System.out.println(" No se encontraron historias clinicas activas.");
            }
*/






        }
    }
}


