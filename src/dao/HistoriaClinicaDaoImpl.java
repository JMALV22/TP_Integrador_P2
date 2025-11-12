package dao;

import entities.GrupoSanguineo;
import entities.HistoriaClinica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoriaClinicaDaoImpl implements HistoriaClinicaDao {


    // metodos del contrato (generic)

    @Override
    public HistoriaClinica obtenerPorId(Long id, Connection con) throws SQLException {

        final String sql =
                "SELECT id_historia, eliminado, nro_historia, grupo_sanguineo, antecedentes, " +
                        "medicacion_actual, observaciones " +
                        "FROM historia_clinica " +
                        "WHERE id_historia = ? AND eliminado = FALSE"; // los eliminados no saldrian en el resultado, pero siguen en la db

        // prepara y ejecuta la consulta
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id); // en el primer ? del sql, pone valor Long llamado id

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) { // recorre fila por fila
                    HistoriaClinica hc = new HistoriaClinica();
                    // se cargan parametros en mismo orden que los ?
                    hc.setId(rs.getLong("id_historia"));
                    hc.setEliminado(rs.getBoolean("eliminado"));
                    hc.setNroHistoria(rs.getString("nro_historia"));

                    // convierte ENUM de la BD → enum de Java
                    hc.setGrupoSanguineo(
                            GrupoSanguineo.convertir(rs.getString("grupo_sanguineo")));

                    hc.setAntecedentes(rs.getString("antecedentes"));
                    hc.setMedicacionActual(rs.getString("medicacion_actual"));
                    hc.setObservaciones(rs.getString("observaciones"));

                    return hc;
                }
            }
        }

        return null; // si no existe o esta eliminado
    }


    @Override
    public HistoriaClinica obtenerPorNroHistoria(String nro, Connection con) throws SQLException {

        // busca por el identificador nro_historia y solo si no esta eliminado
        final String sql =
                "SELECT id_historia, eliminado, nro_historia, grupo_sanguineo, " +
                        "       antecedentes, medicacion_actual, observaciones " +
                        "FROM historia_clinica " +
                        "WHERE nro_historia = ? AND eliminado = FALSE";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nro); // 1er '?' = valor del nro de historia clínica

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // una sola fila (nro_historia es UNIQUE)
                    HistoriaClinica hc = new HistoriaClinica();

                    hc.setId(rs.getLong("id_historia"));
                    hc.setEliminado(rs.getBoolean("eliminado"));
                    hc.setNroHistoria(rs.getString("nro_historia"));

                    // funcion convertir en el enum grupo sanguineo
                    String gs = rs.getString("grupo_sanguineo"); // lee el valor de la columna de la DB
                    if (gs != null) {
                        hc.setGrupoSanguineo(GrupoSanguineo.convertir(gs)); // convierte el texto SQL al enum de Java
                    } // convertir("A+") devolveria GrupoSanguineo.A_POSITIVO"

                    // columnas
                    hc.setAntecedentes(rs.getString("antecedentes"));
                    hc.setMedicacionActual(rs.getString("medicacion_actual"));
                    hc.setObservaciones(rs.getString("observaciones"));

                    return hc;
                }
            }
        }

        // si no existe o esta eliminado devolvemos null
        return null;
    }

    // metodos del contrato (generic) pendientes

    @Override
    public ArrayList<HistoriaClinica> obtenerTodos(Connection con) throws SQLException {

        // selecciona todas las historias clínicas
        final String sql =
                "SELECT id_historia, nro_historia, grupo_sanguineo, antecedentes, " +
                        "medicacion_actual, observaciones, eliminado " +
                        "FROM historia_clinica " +
                        "WHERE eliminado = FALSE " +
                        "ORDER BY id_historia";

        ArrayList<HistoriaClinica> lista = new ArrayList<>(); // seran guardadas en una lista

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) { // recorre cada fila del resultado
                HistoriaClinica hc = new HistoriaClinica();

                // columnas
                hc.setId(rs.getLong("id_historia"));
                hc.setNroHistoria(rs.getString("nro_historia"));
                hc.setAntecedentes(rs.getString("antecedentes"));
                hc.setMedicacionActual(rs.getString("medicacion_actual"));
                hc.setObservaciones(rs.getString("observaciones"));

                // función convertir en el enum grupo sanguíneo
                String gs = rs.getString("grupo_sanguineo"); // lee valor de la columna en BD
                if (gs != null) {
                    hc.setGrupoSanguineo(GrupoSanguineo.convertir(gs)); // convierte texto SQL al enum Java
                } // por ej. convertir("A+") = GrupoSanguineo.A_POSITIVO

                hc.setEliminado(rs.getBoolean("eliminado")); // indica si esta dado de baja

                lista.add(hc); // agrega el objeto a la lista
            }
        }

        // devuelve la lista de historias encontradas
        return lista;
    }

    @Override
    public HistoriaClinica insertar(HistoriaClinica entity, Connection con) throws SQLException {
        throw new UnsupportedOperationException("metodo aún no implementado");
    }

    @Override
    public boolean actualizar(HistoriaClinica entity, Connection con) throws SQLException {
        throw new UnsupportedOperationException("metodo aún no implementado");
    }

    @Override
    public boolean eliminar(Long id, Connection con) throws SQLException {
        throw new UnsupportedOperationException("metodo aún no implementado");
    }


}
