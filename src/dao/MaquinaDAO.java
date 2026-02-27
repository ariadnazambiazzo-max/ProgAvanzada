package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Maquina;
import modelo.Response;

public class MaquinaDAO extends BaseDAO<Maquina> {

    public MaquinaDAO() {
        super();
    }

    @Override
    public Response<Maquina> create(Maquina m) {
        String sql = "INSERT INTO maquina (nombre, estado, costoHora) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNombre());
            stmt.setString(2, m.getEstado());
            stmt.setDouble(3, m.getCostoHora());
            stmt.executeUpdate();

            return new Response<>(true, "200", "Máquina creada exitosamente", m);

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Maquina> read(int id) {
        String sql = "SELECT * FROM maquina WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Maquina m = new Maquina();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setEstado(rs.getString("estado"));
                    m.setCostoHora(rs.getDouble("costoHora"));
                    return new Response<>(true, "200", "Máquina encontrada exitosamente", m);
                } else {
                    return new Response<>(false, "404", "Máquina no encontrada");
                }
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Maquina> update(Maquina m) {
        String sql = "UPDATE maquina SET nombre = ?, estado = ?, costoHora = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNombre());
            stmt.setString(2, m.getEstado());
            stmt.setDouble(3, m.getCostoHora());
            stmt.setInt(4, m.getId());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                return new Response<>(true, "200", "Máquina actualizada exitosamente", m);
            } else {
                return new Response<>(false, "404", "Máquina no encontrada");
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Maquina> delete(int id) {
        String sql = "DELETE FROM maquina WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                return new Response<>(true, "200", "Máquina eliminada exitosamente");
            } else {
                return new Response<>(false, "404", "Máquina no encontrada");
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Maquina> readAll() {
        List<Maquina> lista = new ArrayList<>();
        String sql = "SELECT * FROM maquina";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Maquina m = new Maquina();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setEstado(rs.getString("estado"));
                m.setCostoHora(rs.getDouble("costoHora"));
                lista.add(m);
            }

            return new Response<>(true, "200", "Máquinas obtenidas", lista);

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }
}