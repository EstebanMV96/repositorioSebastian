package com.wexu.huckster.modelo;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;


public class Usuario implements Serializable{
	
	
	private String nombre;
	private String nickName;
	private String token;
	private boolean estado;
	private ArrayList<Producto> misProductos;
	private ArrayList<Chat>  chats;
	private long totalP;
	
	public Usuario(String nombre, String nickName, String token) {
		
		this.nombre = nombre;
		this.nickName = nickName;
		misProductos=new ArrayList<Producto>();
		this.token=token;
		totalP=0;
		
	}
	
	

	
	

	public boolean isEstado() {
		return estado;
	}


	public ArrayList<Producto> getMisProductos() {
		return misProductos;
	}


	public String getNombre() {
		return nombre;
	}



	public String getNickName() {
		return nickName;
	}

	public String getToken() {
		return token;
	}
	
	public boolean agregarProducto(String name, String des, String nomImg, int precio,String categoria)
	{
		boolean sePudo=false;
		Producto nuevo=new Producto(name,des,precio,nickName,categoria,nomImg);
		if(buscarProducto(name)==null)
		{
			misProductos.add(nuevo);
			totalP++;
			sePudo=true;
		}
		return sePudo;
	}
	
	public void eliminarProducto(String name)throws Exception
	{
		
	}

	public long getTotalP()
	{
		return totalP;
	}

	public void setTotalP(long total)
	{
		totalP=total;
	}
	
	public void enviarMensaje(String user,String token)
	{
		
	}

	public void cargarProducto(Producto p)
	{

		Log.d("AGREGO P","TRUE");
		misProductos.add(p);

		Log.d("TAMAÑO DEL BUFFER",misProductos.size()+"");
	}

	public Producto buscarProducto(String nombre)
	{
		nombre=nombre.trim();
		Producto encontrado=null;
		Log.d("TAMAÑO DEL BUFFER",misProductos.size()+"");
		for (int i=0;i<misProductos.size()&&encontrado==null;i++)
		{
			if(misProductos.get(i).getNombre().trim().equalsIgnoreCase(nombre))
			{
				encontrado=misProductos.get(i);
			}
		}

		return encontrado;
	}
	

}
