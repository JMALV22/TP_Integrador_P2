package dao;

import entities.HistoriaClinica;
import java.sql.Connection;
import java.sql.SQLException;

public interface HistoriaClinicaDao extends GenericDao<HistoriaClinica> {

    HistoriaClinica obtenerPorNroHistoria(String nroHistoria, Connection con) throws SQLException;

}
