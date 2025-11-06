package dao;

import entities.Paciente;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PacienteDao extends GenericDao<Paciente> {

    // busca un paciente por DNI
    Paciente obtenerPorDni(String dni, Connection con) throws SQLException;

    // busca por apellido
    List<Paciente> buscarPorApellido(String apellido, Connection con) throws SQLException;
}
