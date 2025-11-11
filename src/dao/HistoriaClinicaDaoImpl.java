package dao;

import entities.GrupoSanguineo;
import entities.HistoriaClinica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    // metodos del contrato (generic) pendientes

    @Override
    public HistoriaClinica obtenerPorNroHistoria(String nroHistoria, Connection con) throws SQLException {
        throw new UnsupportedOperationException("metodo aún no implementado");
    }

    @Override
    public java.util.List<HistoriaClinica> obtenerTodos(Connection con) throws SQLException {
        throw new UnsupportedOperationException("metodo aún no implementado");
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
