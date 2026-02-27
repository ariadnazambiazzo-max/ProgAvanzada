package modelo;

import java.sql.Date;

public class AsignacionMaquina {

    private int id;
    private Maquina maquina;
    private Obra obra;
    private Date fechaInicio;
    private Date fechaFin;
    private double horasUtilizadas;
    
    
    //Constructor
    
    public AsignacionMaquina() {
	
	}
    public AsignacionMaquina(int id, Maquina maquina, Obra obra, Date fechaInicio, Date fechaFin, double horasUtilizadas) {
		super();
		this.id = id;
		this.maquina = maquina;
		this.obra = obra;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.horasUtilizadas = horasUtilizadas;
	}
    

	//Getters y Setters

	public int getId() {
		return id;
	}
	
	public Maquina getMaquina() {
		return maquina;
	}

	public Obra getObra() {
		return obra;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public double getHorasUtilizadas() {
		return horasUtilizadas;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public void setHorasUtilizadas(double horasUtilizadas) {
		this.horasUtilizadas = horasUtilizadas;
	}



	//Métodos
    public double calcularCosto() {
        return horasUtilizadas * maquina.getCostoHora();
    }

    public void registrarGasto() {
    	if (obra == null) {
            throw new IllegalStateException("La obra no puede ser nula al registrar un gasto.");
        }

        Gasto gasto = new Gasto();
        gasto.setMonto(calcularCosto());
        gasto.setFecha(new Date(id, id, id));
        gasto.setTipo("Maquinaria");
        gasto.setObra(obra);

        obra.getGastos().add(gasto);
    }
    
}
