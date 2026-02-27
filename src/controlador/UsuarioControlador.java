package controlador;


import dao.UsuarioDAO;
import modelo.Response;
import modelo.Usuario;

public class UsuarioControlador {

	
	private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Response<Usuario> crearUsuario(Usuario u) {
        return usuarioDAO.create(u);
    }

    public Response<Usuario> obtenerUsuario(int id) {
        return usuarioDAO.read(id);
    }

    public Response<Usuario> actualizarUsuario(Usuario u) {
        return usuarioDAO.update(u);
    }

    public Response<Usuario> eliminarUsuario(int id) {
        return usuarioDAO.delete(id);
    }

    public Response<Usuario> listarUsuarios() {
        return usuarioDAO.readAll();
    }
}
