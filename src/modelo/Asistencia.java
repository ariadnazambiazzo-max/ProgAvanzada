package modelo;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalTime;

public class Asistencia {

	
    private int id;
    private Date fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    //Constructor
    public Asistencia(int id, Date fecha, LocalTime horaEntrada, LocalTime horaSalida) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
	}

    //Getters y Setters
	public int getId() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public LocalTime getHoraSalida() {
		return horaSalida;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}



	//Métodos
    public double calcularHoras() {
        return Duration.between(horaEntrada, horaSalida).toHours();
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
