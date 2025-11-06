package dao;

import entities.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoImpl implements PacienteDao {

    // obtenerTodos = SELECT * FROM pacientes
    @Override
    public List<Paciente> obtenerTodos(Connection con) throws SQLException {
        String sql = "SELECT id_paciente, nombre, apellido, dni, fecha_nacimiento, eliminado " +
                "FROM paciente " +
                "WHERE eliminado = FALSE " + // los eliminados no saldrian en el resultado, pero siguen en la db
                "ORDER BY id_paciente";

        List<Paciente> lista = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) { // lee columna por columna
                Paciente p = new Paciente();
                p.setId(rs.getLong("id_paciente"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setDni(rs.getString("dni"));

                Date f = rs.getDate("fecha_nacimiento");
                if (f != null) {
                    p.setFechaNacimiento(f.toLocalDate());
                }

                p.setEliminado(rs.getBoolean("eliminado"));

                lista.add(p);
            }
        }

        return lista;
    }

    // Metodos heredados obligatoriamente, todavia sin implementar
    @Override
    public boolean guardar(Paciente entity, Connection con) throws SQLException {
        throw new UnsupportedOperationException("guardar() aún no implementado");
    }

    @Override
    public Paciente obtenerPorId(Long id, Connection con) throws SQLException {
        String sql = "SELECT id_paciente, nombre, apellido, dni, fecha_nacimiento, eliminado " +
                "FROM paciente " +
                "WHERE id_paciente = ? AND eliminado = FALSE";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id); // reemplaza el ? con el valor recibido

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) { // si encontro un registro

                    Paciente p = new Paciente();
                    p.setId(rs.getLong("id_paciente"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setDni(rs.getString("dni"));

                    Date f = rs.getDate("fecha_nacimiento");
                    if (f != null) {
                        p.setFechaNacimiento(f.toLocalDate()); // se convierte a localdate
                    }

                    p.setEliminado(rs.getBoolean("eliminado"));

                    return p; // devolvemos el objeto ya cargado
                }
            }
        }

        return null; // si no encontró ningún paciente con ese id, devuelve null

    }

    @Override
    public boolean actualizar(Paciente entity, Connection con) throws SQLException {
        throw new UnsupportedOperationException("actualizar() aún no implementado");
    }

    @Override
    public boolean eliminar(Long id, Connection con) throws SQLException {
        throw new UnsupportedOperationException("eliminar() (baja lógica) aún no implementado");
    }

    @Override
    public Paciente obtenerPorDni(String dni, Connection con) throws SQLException {
        throw new UnsupportedOperationException("obtenerPorDni() aún no implementado");
    }

    @Override
    public List<Paciente> buscarPorApellido(String apellido, Connection con) throws SQLException {
        throw new UnsupportedOperationException("buscarPorApellido() aún no implementado");
    }
}

