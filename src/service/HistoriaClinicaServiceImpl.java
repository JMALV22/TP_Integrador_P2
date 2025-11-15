package service;

import config.ConexionDB;
import dao.HistoriaClinicaDao;
import dao.HistoriaClinicaDaoImpl;
import entities.HistoriaClinica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HistoriaClinicaServiceImpl implements GenericService<HistoriaClinica> {
    private final HistoriaClinicaDao historiaClinicaDao;
    
    public HistoriaClinicaServiceImpl() {
        this.historiaClinicaDao = new HistoriaClinicaDaoImpl();
    }
    
    @Override
    public HistoriaClinica insertar(HistoriaClinica historiaClinica) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return historiaClinicaDao.insertar(historiaClinica, conn);
        }
    }

    @Override
    public HistoriaClinica obtenerPorId(Long id) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return historiaClinicaDao.obtenerPorId(id, conn);
        }
    }

    @Override
    public List<HistoriaClinica> obtenerTodos() throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return historiaClinicaDao.obtenerTodos(conn);
        }
    }

    @Override
    public boolean actualizar(HistoriaClinica historiaClinica) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return historiaClinicaDao.actualizar(historiaClinica, conn);
        }
    }

    @Override
    public boolean eliminar(Long id) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return historiaClinicaDao.eliminar(id, conn);
        }
    }

    public HistoriaClinica obtenerPorNroHistoria(String nroHistoria) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            return historiaClinicaDao.obtenerPorNroHistoria(nroHistoria, conn);
        }
    }
}