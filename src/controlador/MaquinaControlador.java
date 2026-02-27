package controlador;

import dao.MaquinaDAO;
import modelo.Maquina;
import modelo.Response;

public class MaquinaControlador {


	    private final MaquinaDAO maquinaDAO = new MaquinaDAO();

	    public Response<Maquina> crearMaquina(Maquina m) {
	        return maquinaDAO.create(m);
	    }

	    public Response<Maquina> obtenerMaquina(int id) {
	        return maquinaDAO.read(id);
	    }

	    public Response<Maquina> actualizarMaquina(Maquina m) {
	        return maquinaDAO.update(m);
	    }

	    public Response<Maquina> eliminarMaquina(int id) {
	        return maquinaDAO.delete(id);
	    }

	    public Response<Maquina> listarMaquinas() {
	        return maquinaDAO.readAll();
	    }
}
