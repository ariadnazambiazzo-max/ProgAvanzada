package controlador;

import dao.UsuarioDAO;
import modelo.Response;
import modelo.Usuario;

public class LoginControlador {

	  private final UsuarioDAO usuarioDAO = new UsuarioDAO();

	    public Response<Usuario> login(String nombre, String contrasenia) {
	        Response<Usuario> respuesta = usuarioDAO.readAll();
	        if (respuesta.isSuccess() && respuesta.getData() != null) {
	            for (Usuario u : respuesta.getData()) {
	                if (u.getNombre().equals(nombre) && u.getContrasenia().equals(contrasenia)) {
	                    return new Response<>(true, "200", "Login exitoso", u);
	                }
	            }
	        }
	        return new Response<>(false, "401", "Credenciales inválidas");
	    }
	
	
	
	
	
	
}
