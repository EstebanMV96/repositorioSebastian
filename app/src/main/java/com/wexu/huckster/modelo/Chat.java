package com.wexu.huckster.modelo;


/**
 * MOYA
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable{

	private String userName;
	private int ordenMensaje;
	private ArrayList<Mensaje> enviados;
	private ArrayList<Mensaje> recibidos;
	public Chat(String userName) {
		
		this.userName = userName;
		ordenMensaje=0;
		enviados=new ArrayList<Mensaje>();
		recibidos=new ArrayList<Mensaje>();
		
	}

	public void escribirMensaje(String mensaje)
	{
		
	}

	public void recibirMensaje(String mensaje)
	{
		
	}

	public String getLastMessage(){

		try {
			Mensaje lastSend= enviados.get(enviados.size()-1);
			Mensaje lastRec= recibidos.get(recibidos.size()-1);
			return lastSend.getId()>lastRec.getId()?lastSend.getMensaje():lastRec.getMensaje();
		}
		catch (Exception e){
			return "Hola, ¿como estas?";
		}

	}

	public String getUserName() {
		return userName;
	}
}
