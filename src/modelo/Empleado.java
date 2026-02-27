package modelo;

import java.sql.Date;
import java.util.List;

public class Empleado extends Usuario{

	
    private List<Asistencia> asistencias;

    //Constructor
    public Empleado() {
	
	}

    //Getters y Setters
	


	public List<Asistencia> getAsistencias() {
		return asistencias;
	}


	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}


	//Métodos
    public double calcularHorasTrabajadas(Obra obra) {
        return asistencias.stream().filter(a -> a.getFecha().after(obra.getFechaInicio()) && a.getFecha().before(obra.getFechaFin()))
            .mapToDouble(Asistencia::calcularHoras).sum();
    }

    public boolean estaDisponible() {
        return true; // lógica simplificada
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
