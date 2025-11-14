package service;
    import java.sql.SQLException;
    import java.util.List;

public interface GenericService<T> {
    T insertar(T entity) throws SQLException;
    T obtenerPorId(Long id) throws SQLException;
    List<T> obtenerTodos() throws SQLException;
    boolean actualizar(T entity) throws SQLException;
    boolean eliminar(Long id) throws SQLException;
}
