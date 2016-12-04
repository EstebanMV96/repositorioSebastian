package com.wexu.huckster.modelo;

import java.io.Serializable;

public class Mensaje implements Serializable{
	
	
	
	private String mensaje;
	private int id;
	public Mensaje(String mensaje, int id) {
		
		this.mensaje = mensaje;
		this.id = id;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

	

}
