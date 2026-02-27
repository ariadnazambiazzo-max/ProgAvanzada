/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.ConexionBD;
import modelo.Response;

/**
 *
 * @author Soledad
 */
public class ObraEmpleadoDAO {
    public Response<String> asignar(int obraId, int empleadoId) {

    String sql = "INSERT INTO obra_empleado (obra_id, empleado_id) VALUES (?, ?)";

    try (Connection conn = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, obraId);
        ps.setInt(2, empleadoId);
        ps.executeUpdate();

        // Indicar explícitamente T=String y pasar code y message
        return new Response<String>(true, "200", "Empleado asignado correctamente", (String) null);

    } catch (Exception e) {
        return new Response<String>(false, "500", "Error al asignar: " + e.getMessage(), (String) null);
    }
}

public Response<String> eliminar(int obraId, int empleadoId) {
    String sql = "DELETE FROM obra_empleado WHERE obra_id = ? AND empleado_id = ?";

    try (Connection conn = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, obraId);
        ps.setInt(2, empleadoId);
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            return new Response<String>(true, "200", "Empleado eliminado correctamente", (String) null);
        } else {
            return new Response<String>(false, "404", "No se encontró el empleado en la obra", (String) null);
        }

    } catch (Exception e) {
        return new Response<String>(false, "500", "Error al eliminar: " + e.getMessage(), (String) null);
    }
}
}
