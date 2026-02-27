package controlador;

import dao.MaterialDAO;
import modelo.Material;
import modelo.Response;

public class MaterialControlador {

	
	private final MaterialDAO materialDAO = new MaterialDAO();

    public Response<Material> crearMaterial(Material m) {
        return materialDAO.create(m);
    }

    public Response<Material> obtenerMaterial(int id) {
        return materialDAO.read(id);
    }

    public Response<Material> actualizarMaterial(Material m) {
        return materialDAO.update(m);
    }

    public Response<Material> eliminarMaterial(int id) {
        return materialDAO.delete(id);
    }

    public Response<Material> listarMaquinas() {
        return materialDAO.readAll();
    }
}
