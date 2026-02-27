package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;
import modelo.Gerente;
import modelo.Response;
import modelo.Usuario;

public class UsuarioDAO extends BaseDAO<Usuario> {

    public UsuarioDAO() {
        super();
    }

    @Override
    public Response<Usuario> create(Usuario u) {
        String sql = "INSERT INTO usuario (nombre, contrasenia, tipo, dni) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getContrasenia());
            stmt.setString(3, u.getTipo());
            stmt.setInt(4, u.getDni());
            stmt.executeUpdate();

            return new Response<>(true, "200", "Usuario creado", u);

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Usuario> read(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = construirUsuarioDesdeResultSet(rs);
                    return new Response<>(true, "200", "Usuario encontrado", u);
                } else {
                    return new Response<>(false, "404", "Usuario no encontrado");
                }
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Usuario> update(Usuario u) {
        String sql = "UPDATE usuario SET nombre = ?, contrasenia = ?, tipo = ?, dni = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getContrasenia());
            stmt.setString(3, u.getTipo());
            stmt.setInt(4, u.getDni());
            stmt.setInt(5, u.getId());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                return new Response<>(true, "200", "Usuario actualizado", u);
            } else {
                return new Response<>(false, "404", "Usuario no encontrado");
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Usuario> delete(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                return new Response<>(true, "200", "Usuario eliminado");
            } else {
                return new Response<>(false, "404", "Usuario no encontrado");
            }

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    @Override
    public Response<Usuario> readAll() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = construirUsuarioDesdeResultSet(rs);
                lista.add(u);
            }

            return new Response<>(true, "200", "Usuarios obtenidos", lista);

        } catch (SQLException e) {
            return new Response<>(false, "500", e.getMessage());
        }
    }

    private Usuario construirUsuarioDesdeResultSet(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo");
        Usuario u;
        if ("Gerente".equalsIgnoreCase(tipo)) {
            u = new Gerente();
        } else if ("Empleado".equalsIgnoreCase(tipo)) {
            u = new Empleado();
        } else {
            u = new Usuario();
        }
        u.setId(rs.getInt("id"));
        u.setNombre(rs.getString("nombre"));
        u.setContrasenia(rs.getString("contrasenia"));
        u.setTipo(tipo);
        u.setDni(rs.getInt("dni"));
        return u;
    }

 // Leer solo empleados
    public Response<List<Usuario>> readEmpleados() {
        List<Usuario> empleados = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE tipo = 'Empleado'";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setContrasenia(rs.getString("contrasenia"));
                u.setDni(rs.getInt("dni"));
                u.setTipo(rs.getString("tipo"));
                empleados.add(u);
            }

            return new Response<List<Usuario>>(true, "200", "Empleados obtenidos", empleados);

        } catch (SQLException e) {
            return new Response<List<Usuario>>(false, "500", "Error al leer empleados: " + e.getMessage(), empleados);
        }
    }


}