package modelo;

public class Maquina {

	    private int id;
	    private String nombre;
	    private String estado;
	    private double costoHora;
	    
	    

	    //Constructor
	    public Maquina() {
	 
	    }
			
	    public Maquina(int id, String nombre, String estado, double costoHora) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.estado = estado;
			this.costoHora = costoHora;
		}
	    

		//Getters y Setters

		public int getId() {
			return id;
		}

		public String getNombre() {
			return nombre;
		}

		public String getEstado() {
			return estado;
		}

		public double getCostoHora() {
			return costoHora;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public void setEstado(String estado) {
			this.estado = estado;
		}

		public void setCostoHora(double costoHora) {
			this.costoHora = costoHora;
		}



		//Métodos
	    public boolean estaDisponible() {
	        return estado.equals("Disponible");
	    }

	    public void asignarAObra(Obra obra) {
	        obra.getMaquinas().add(this);
	    }
	    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
