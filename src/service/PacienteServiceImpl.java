package service;

import config.ConexionDB;
import entities.HistoriaClinica;
import entities.Paciente;
import dao.HistoriaClinicaDao;
import dao.HistoriaClinicaDaoImpl;
import dao.PacienteDao;
import dao.PacienteDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PacienteServiceImpl implements GenericService<Paciente>{
    
    private final PacienteDao pacienteDao;
    private final HistoriaClinicaDao historiaClinicaDao;

    public PacienteServiceImpl() {
        this.pacienteDao = new PacienteDaoImpl();
        this.historiaClinicaDao = new HistoriaClinicaDaoImpl();
    }
    
    @Override
    public Paciente insertar(Paciente paciente) throws SQLException {
        Connection conn = null;
        try {
            conn = ConexionDB.getConnection();
            conn.setAutoCommit(false);

            Paciente pacienteInsertado = pacienteDao.insertar(paciente, conn);
            
            if (pacienteInsertado.getHistoriaClinica() != null) {
                HistoriaClinica hc = pacienteInsertado.getHistoriaClinica();
                hc.setIdPaciente(pacienteInsertado.getId());
                historiaClinicaDao.insertar(hc, conn);
            }
            
            conn.commit();
            return pacienteInsertado;
            
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
    
    @Override
    public Paciente obtenerPorId(Long id) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return pacienteDao.obtenerPorId(id, conn);
        }
    }

    @Override
    public List<Paciente> obtenerTodos() throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return pacienteDao.obtenerTodos(conn);
        }
    }

    @Override
    public boolean actualizar(Paciente paciente) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return pacienteDao.actualizar(paciente, conn);
        }
    }

    @Override
    public boolean eliminar(Long id) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return pacienteDao.eliminar(id, conn);
        }
    }

    public Paciente buscarPorDni(String dni) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return pacienteDao.obtenerPorDni(dni, conn);
        }
    }
    
    public List<Paciente> buscarPorApellido(String apellido) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return pacienteDao.buscarPorApellido(apellido, conn);
        }
    }
}