package dao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ICrud;
import modelo.ConexionBD;
import modelo.Response;


public abstract class BaseDAO<T> implements ICrud<T> {

    protected Connection getConnection() throws SQLException {
        return ConexionBD.getInstance().getConnection();
    }

    @Override
    public abstract Response<T> create(T entity);

    @Override
    public abstract Response<T> read(int id);

    @Override
    public abstract Response<T> update(T entity);

    @Override
    public abstract Response<T> delete(int id);

    @Override
    public abstract Response<T> readAll();
}
