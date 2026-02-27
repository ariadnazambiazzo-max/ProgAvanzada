package modelo;

import java.sql.Date;

public class AsignacionMaterial {

	    private int id;
	    private double cantidad;
	    private Material material;

	    //Constructor
	    public AsignacionMaterial(int id, double cantidad, Material material) {
			super();
			this.id = id;
			this.cantidad = cantidad;
			this.material = material;
		}

        //Getters y Setters
		public int getId() {
			return id;
		}

		public double getCantidad() {
			return cantidad;
		}

		public Material getMaterial() {
			return material;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setCantidad(double cantidad) {
			this.cantidad = cantidad;
		}

		public void setMaterial(Material material) {
			this.material = material;
		}



		//Métodos
	    public void asignarAObra(Obra obra) {
	        obra.getMateriales().add(material);
	        material.descontarStock(cantidad);
	        
	        Gasto gasto = new Gasto();
	        gasto.setMonto(material.getCosto() * cantidad);
	        gasto.setFecha(new Date(id, id, id));
	        gasto.setTipo("Material");
	        gasto.setObra(obra);
	        obra.getGastos().add(gasto);
	    
	    }
	
	
	
	
	
	
	
	
	
	
	
	
}
