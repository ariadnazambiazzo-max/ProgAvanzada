package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Obra;
import modelo.Response;


public class ObraDAO extends BaseDAO<Obra> {

    public ObraDAO() {
        super();
    }

    @Override
    public Response<Obra> create(Obra o) {
        String sql = "INSERT INTO obra (nombre, ubicacion, fechaInicio, fechaFin, presupuesto, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, o.getNombre());
            stmt.setString(2, o.getUbicacion());
            stmt.setDate(3, new java.sql.Date(o.getFechaInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(o.getFechaFin().getTime()));
            stmt.setDouble(5, o.getPresupuesto());
            stmt.setString(6, o.getEstado());
            stmt.executeUpdate();

            return new Response<>(true, "200", "Obra creada exitosamente", o);

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Obra> read(int id) {
        String sql = "SELECT * FROM obra WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Obra o = new Obra();
                    o.setId(rs.getInt("id"));
                    o.setNombre(rs.getString("nombre"));
                    o.setUbicacion(rs.getString("ubicacion"));
                    o.setFechaInicio(rs.getDate("fechaInicio"));
                    o.setFechaFin(rs.getDate("fechaFin"));
                    o.setPresupuesto(rs.getDouble("presupuesto"));
                    o.setEstado(rs.getString("estado"));
                    return new Response<>(true, "200", "Obra encontrada exitosamente", o);
                } else {
                    return new Response<>(false, "404", "Obra no encontrada");
                }
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Obra> update(Obra o) {
        String sql = "UPDATE obra SET nombre = ?, ubicacion = ?, fechaInicio = ?, fechaFin = ?, presupuesto = ?, estado = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, o.getNombre());
            stmt.setString(2, o.getUbicacion());
            stmt.setDate(3, new java.sql.Date(o.getFechaInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(o.getFechaFin().getTime()));
            stmt.setDouble(5, o.getPresupuesto());
            stmt.setString(6, o.getEstado());
            stmt.setInt(7, o.getId());
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                return new Response<>(true, "200", "Obra actualizada exitosamente", o);
            } else {
                return new Response<>(false, "404", "Obra no encontrada");
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Obra> delete(int id) {
        String sql = "DELETE FROM obra WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                return new Response<>(true, "200", "Obra eliminada exitosamente");
            } else {
                return new Response<>(false, "404", "Obra no encontrada");
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Obra> readAll() {
        List<Obra> lista = new ArrayList<>();
        String sql = "SELECT * FROM obra";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Obra o = new Obra();
                o.setId(rs.getInt("id"));
                o.setNombre(rs.getString("nombre"));
                o.setUbicacion(rs.getString("ubicacion"));
                o.setFechaInicio(rs.getDate("fechaInicio"));
                o.setFechaFin(rs.getDate("fechaFin"));
                o.setPresupuesto(rs.getDouble("presupuesto"));
                o.setEstado(rs.getString("estado"));
                lista.add(o);
            }

            return new Response<>(true, "200", "Obras obtenidas", lista);

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }
}