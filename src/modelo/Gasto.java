package modelo;

import java.sql.Date;

public class Gasto {

	 private int id;
	 private double monto;
	 private Date fecha;
	 private String tipo;
	 private Obra obra;
	 
	
	//Constructor
	 public Gasto() {
		}
	 
	public Gasto(int id, double monto, Date fecha, String tipo, Obra obra) {
		super();
		this.id = id;
		this.monto = monto;
		this.fecha = fecha;
		this.tipo = tipo;
		this.obra = obra;
	}

    //Getters y Setters
	public int getId() {
		return id;
	}

	public double getMonto() {
		return monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public Obra getObra() {
		return obra;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
