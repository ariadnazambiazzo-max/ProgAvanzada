package modelo;

import java.util.List;

public class Gerente extends Usuario {

	private List<Obra> obras;
	
	
   //Constructor
	public Gerente() {
		
	}

	//Getters y Setters
	public List<Obra> getObras() {
		return obras;
	}


	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}
	
	
	
	
	
	
	
	
	
	
}
