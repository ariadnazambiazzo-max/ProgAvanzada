package modelo;

public class Material {

	private int id;
    private String nombre;
    private double costo;
    private double stock;
    
    
    //Constructor
    
    public Material() {
	
	}

	public Material(int id, String nombre, double costo, double stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.stock = stock;
	}
    
    //Getters y Setters

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public double getCosto() {
		return costo;
	}

	public double getStock() {
		return stock;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	//Métodos
	
	public void descontarStock(double cantidad) {
        stock -= cantidad;
    }

    public void reponerStock(double cantidad) {
        stock += cantidad;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
