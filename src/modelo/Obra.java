package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Obra {

	  private int id;
	    private String nombre;
	    private String ubicacion;
	    private Date fechaInicio;
	    private Date fechaFin;
	    private double presupuesto;
	    private String estado;
	    private List<Empleado> empleados;
	    private List<Material> materiales;
	    private List<Gasto> gastos;
	    private List<Maquina> maquinas;
	    
	    
	    //Constructor
	    
	    public Obra() {
	        this.gastos = new ArrayList<>();
	        this.materiales = new ArrayList<>();
	        this.maquinas = new ArrayList<>();
	        this.empleados = new ArrayList<>();
	    }
	    
	    public Obra(int id, String nombre, String ubicacion, Date fechaInicio, Date fechaFin, double presupuesto,
				String estado, List<Empleado> empleados, List<Material> materiales, List<Gasto> gastos,
				List<Maquina> maquinas) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.ubicacion = ubicacion;
			this.fechaInicio = fechaInicio;
			this.fechaFin = fechaFin;
			this.presupuesto = presupuesto;
			this.estado = estado;
			this.empleados = empleados;
			this.materiales = materiales;
			this.gastos = gastos;
			this.maquinas = maquinas;
		}




		//Getters y Setters

	    public int getId() {
			return id;
		}


		public String getNombre() {
			return nombre;
		}


		public String getUbicacion() {
			return ubicacion;
		}


		public Date getFechaInicio() {
			return fechaInicio;
		}


		public Date getFechaFin() {
			return fechaFin;
		}


		public double getPresupuesto() {
			return presupuesto;
		}




		public String getEstado() {
			return estado;
		}


		public List<Empleado> getEmpleados() {
			return empleados;
		}


		public List<Material> getMateriales() {
			return materiales;
		}


		public List<Gasto> getGastos() {
			return gastos;
		}


		public List<Maquina> getMaquinas() {
			return maquinas;
		}


		public void setId(int id) {
			this.id = id;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public void setUbicacion(String ubicacion) {
			this.ubicacion = ubicacion;
		}


		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}


		public void setFechaFin(Date fechaFin) {
			this.fechaFin = fechaFin;
		}


		public void setPresupuesto(double presupuesto) {
			this.presupuesto = presupuesto;
		}


		public void setEstado(String estado) {
			this.estado = estado;
		}


		public void setEmpleados(List<Empleado> empleados) {
			this.empleados = empleados;
		}


		public void setMateriales(List<Material> materiales) {
			this.materiales = materiales;
		}


		public void setGastos(List<Gasto> gastos) {
			this.gastos = gastos;
		}


		public void setMaquinas(List<Maquina> maquinas) {
			this.maquinas = maquinas;
		}


        //Métodos
		public double obtenerGastosTotales() {
	        return gastos.stream().mapToDouble(Gasto::getMonto).sum();
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
