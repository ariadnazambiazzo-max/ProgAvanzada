package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Material;
import modelo.Response;

public class MaterialDAO extends BaseDAO<Material> {

    public MaterialDAO() {
        super();
    }

    @Override
    public Response<Material> create(Material m) {
        String sql = "INSERT INTO material (nombre, costo, stock) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNombre());
            stmt.setDouble(2, m.getCosto());
            stmt.setDouble(3, m.getStock());
            stmt.executeUpdate();

            return new Response<>(true, "200", "Material creado exitosamente", m);

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Material> read(int id) {
        String sql = "SELECT * FROM material WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Material m = new Material();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setCosto(rs.getDouble("costo"));
                    m.setStock(rs.getDouble("stock"));
                    return new Response<>(true, "200", "Material encontrado exitosamente", m);
                } else {
                    return new Response<>(false, "404", "Material no encontrado");
                }
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Material> update(Material m) {
        String sql = "UPDATE material SET nombre = ?, costo = ?, stock = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNombre());
            stmt.setDouble(2, m.getCosto());
            stmt.setDouble(3, m.getStock());
            stmt.setInt(4, m.getId());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                return new Response<>(true, "200", "Material actualizado exitosamente", m);
            } else {
                return new Response<>(false, "404", "Material no encontrado");
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Material> delete(int id) {
        String sql = "DELETE FROM material WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                return new Response<>(true, "200", "Material eliminado exitosamente");
            } else {
                return new Response<>(false, "404", "Material no encontrado");
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Material> readAll() {
        List<Material> lista = new ArrayList<>();
        String sql = "SELECT * FROM material";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Material m = new Material();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setCosto(rs.getDouble("costo"));
                m.setStock(rs.getDouble("stock"));
                lista.add(m);
            }

            return new Response<>(true, "200", "Materiales obtenidos", lista);

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }
}
