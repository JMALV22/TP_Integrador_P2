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
        // lista resultado
        List<Paciente> lista = new ArrayList<>();

        // prepara y ejecuta la consulta
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) { // recorre fila por fila
                Paciente p = new Paciente();
                // se cargan parametros en mismo orden que los ?
                p.setId(rs.getLong("id_paciente"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setDni(rs.getString("dni"));

                Date f = rs.getDate("fecha_nacimiento"); // convierte date de SQL a date de Java
                if (f != null) {
                    p.setFechaNacimiento(f.toLocalDate()); // evita nullpointerexept
                }

                p.setEliminado(rs.getBoolean("eliminado"));

                lista.add(p);
            }
        }

        return lista; // puede estar vacia
    }

    // metodos heredados, CRUD

    @Override   // INSERT
    public Paciente insertar(Paciente p, Connection con) throws SQLException {
        final String sql =
                "INSERT INTO paciente (eliminado, nombre, apellido, dni, fecha_nacimiento) " +
                        "VALUES (FALSE, ?, ?, ?, ?)";

        // se prepara INSERT y SQL devuelve el ID autoincrement
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // se cargan parametros en mismo orden que los ?
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getDni());

            if (p.getFechaNacimiento() != null) {
                ps.setDate(4, java.sql.Date.valueOf(p.getFechaNacimiento()));
            } else {
                ps.setNull(4, java.sql.Types.DATE); // convierte fecha de SQL a fecha de java
            }

            int filas = ps.executeUpdate(); // se ejecuta INSERT
            if (filas != 1) {  // si filas != 1 significa que no hubo insercion
                throw new SQLException("La insercion no afectó filas");
            }

            try (ResultSet keys = ps.getGeneratedKeys()) { // agarra el id autogenerado
                if (keys.next()) {
                    p.setId(keys.getLong(1)); // reflejamos la PK autogenerada en el objeto
                    return p; // devuelve el dato con id
                } else {
                    throw new SQLException("Se inserto el paciente pero no se obtuvo el ID generado.");
                }
            }
        }
    }

    @Override  // SELECT POR ID
    public Paciente obtenerPorId(Long id, Connection con) throws SQLException {
        String sql = "SELECT id_paciente, nombre, apellido, dni, fecha_nacimiento, eliminado " +
                "FROM paciente " +
                "WHERE id_paciente = ? AND eliminado = FALSE"; // busca por id si no esta eliminado
        // preparacion del statement
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id); // reemplaza el ? con el valor recibido

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) { // si encontro un registro

                    Paciente p = new Paciente();
                    p.setId(rs.getLong("id_paciente"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setDni(rs.getString("dni"));

                    Date f = rs.getDate("fecha_nacimiento"); // convierte date de SQL a date de Java
                    if (f != null) { // evita nullpointerexept
                        p.setFechaNacimiento(f.toLocalDate()); // se convierte a localdate
                    }

                    p.setEliminado(rs.getBoolean("eliminado"));

                    return p; // devolvemos el objeto ya cargado
                }
            }
        }

        return null; // si no encontró ningun paciente con ese id, devuelve null

    }

    @Override   // UPDATE
    public boolean actualizar(Paciente p, Connection con) throws SQLException {
        String sql = "UPDATE paciente " +
                "SET nombre = ?, apellido = ?, dni = ?, fecha_nacimiento = ? " +
                "WHERE id_paciente = ? AND eliminado = FALSE";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // se cargan parametros en mismo orden que los ?
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getDni());

            if (p.getFechaNacimiento() != null) {
                ps.setDate(4, java.sql.Date.valueOf(p.getFechaNacimiento()));
            } else {
                ps.setNull(4, java.sql.Types.DATE); // convierte date de SQL a date de Java
            }

            ps.setLong(5, p.getId()); // id que define que fila actualizar

            int filas = ps.executeUpdate(); // devuelve cuantas filas fueron modificadas
            return filas == 1; // true si el update afecto 1 unica fila, false si id no existe, o estaba eliminado
        }
    }

    @Override   // DELETE // Solo se pasa el boolean eliminado a TRUE // dar de baja
    public boolean eliminar(Long id, Connection con) throws SQLException {
        String sql = "UPDATE paciente " +
                "SET eliminado = TRUE " + // no borra deinitivamente
                "WHERE id_paciente = ? AND eliminado = FALSE"; // and verifica que ya estaba eliminado

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            int filas = ps.executeUpdate();
            return filas == 1; // true si el update afecto 1 unica fila, false si id no existe, o estaba eliminado
        }
    }

    @Override   // SELECT POR DNI
    public Paciente obtenerPorDni(String dni, Connection con) throws SQLException { // casi igual a obtporId
        String sql = "SELECT id_paciente, nombre, apellido, dni, fecha_nacimiento, eliminado " +
                "FROM paciente " +
                "WHERE dni = ? AND eliminado = FALSE";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Paciente p = new Paciente();
                    // se cargan parametros en mismo orden que los ?
                    p.setId(rs.getLong("id_paciente"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setDni(rs.getString("dni"));

                    java.sql.Date f = rs.getDate("fecha_nacimiento"); // convierte date de SQL a date de Java
                    if (f != null) p.setFechaNacimiento(f.toLocalDate()); // evita nullpointerexept

                    p.setEliminado(rs.getBoolean("eliminado")); // por si esta eliminado
                    return p;
                }
            }
        }
        return null; // si no existe o esta eliminado retorna null
    }

    @Override   // SELECT POR APELLIDO
    public List<Paciente> buscarPorApellido(String apellido, Connection con) throws SQLException {
        List<Paciente> pacientes = new ArrayList<>(); // lista porque puede haber mas de un paciente con = apellido

        String sql = "SELECT id_paciente, nombre, apellido, dni, fecha_nacimiento, eliminado " +
                "FROM paciente " +
                "WHERE apellido LIKE ? AND eliminado = FALSE " +
                "ORDER BY nombre";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + apellido + "%"); // ignora mayusc
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Paciente p = new Paciente();
                    // se cargan parametros en mismo orden que los ?
                    p.setId(rs.getLong("id_paciente"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setDni(rs.getString("dni"));

                    java.sql.Date f = rs.getDate("fecha_nacimiento"); // convierte date de SQL a date de Java
                    if (f != null) p.setFechaNacimiento(f.toLocalDate()); // evita nullpointerexept

                    p.setEliminado(rs.getBoolean("eliminado")); // por si esta eliminado
                    pacientes.add(p);
                }
            }
        }
        return pacientes; // puede venir vacia
    }
}

