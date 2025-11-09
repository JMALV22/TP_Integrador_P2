package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

    T insertar(T entity, Connection con) throws SQLException;

    T obtenerPorId(Long id, Connection con) throws SQLException;

    List<T> obtenerTodos(Connection con) throws SQLException;

    boolean actualizar(T entity, Connection con) throws SQLException;

    // cambia eliminado a TRUE ( no borrraria datos )
    boolean eliminar(Long id, Connection con) throws SQLException;
}

// esta interfaz sera implementada por las clases DAO, que se encargan de ejecutar las querys SQL
// todas las operaciones reciben Connection para que la capa Service pueda controlar transacciones

/* DAO = contrato (qué operaciones existen)
DAOImpl = implementación concreta (el SQL con PreparedStatement)

El DAO no sabe cómo se hace, solo qué se puede hacer
El DAOImpl es el que realmente accede a la base. */
