package modelo;

public class Usuario {

	private int id;
    private String nombre;
    private String contrasenia;
    private String tipo;
    private int dni;
    
    //Constructor
    
    public Usuario() {
		super();
	}



	public Usuario(int id, String nombre, String contrasenia, int dni, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.dni = dni;
		this.tipo = tipo;
	}



	//Getters y Setters
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
    
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
