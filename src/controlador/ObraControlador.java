package controlador;

import dao.ObraDAO;
import dao.ObraEmpleadoDAO;
import modelo.Obra;
import modelo.Response;

public class ObraControlador {


	    private final ObraDAO obraDAO = new ObraDAO();

	    public Response<Obra> crearObra(Obra o) {
	        return obraDAO.create(o);
	    }

	    public Response<Obra> obtenerObra(int id) {
	        return obraDAO.read(id);
	    }

	    public Response<Obra> actualizarObra(Obra o) {
	        return obraDAO.update(o);
	    }

	    public Response<Obra> eliminarObra(int id) {
	        return obraDAO.delete(id);
	    }

	    public Response<Obra> listarObras() {
	        return obraDAO.readAll();
	    }
  
            public Response<String> asignarEmpleado(int obraId, int empleadoId) {
            ObraEmpleadoDAO dao = new ObraEmpleadoDAO(); // crear instancia
            return dao.asignar(obraId, empleadoId);
             }
            
            public Response<String> eliminarEmpleado(int obraId, int empleadoId) {
             ObraEmpleadoDAO dao = new ObraEmpleadoDAO();
             return dao.eliminar(obraId, empleadoId);      
            }
            
            
            
            
            
            
            
            
    }

