package com.wexu.huckster.modelo;

import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.wexu.huckster.control.DataBase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ASUS1 on 08/11/2016.
 */

public class Huckster implements Serializable{

    private String universidadActual;
    private Usuario user;
    private ArrayList<Producto> catalogo;
    private DataBase dataBase;
    private long totalProductos;

    public Huckster(SharedPreferences info, String nickName, String nombre)
    {
        dataBase=new DataBase(this);
        String mensaje=info.getString("info","ERROR");
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        catalogo=new ArrayList<Producto>();
        if(mensaje.equals("ERROR"))
        {

            dataBase.agregarUsuario(nombre,nickName,refreshedToken);
            SharedPreferences.Editor editor=info.edit();
            editor.putString("info",nickName+";"+nombre);
            editor.commit();
        }

        user=new Usuario(nombre,nickName,refreshedToken);
        totalProductos=0;



    }




    public void filtrarProductos(String categoria)
    {

    }

    public void inicializarProdcutos()
    {



    }

    public DataBase getDataBase()
    {
        return  dataBase;
    }

    public  Usuario darUsuario()
    {
        return user;
    }

    public void agregarProducto(Producto p)
    {
        catalogo.add(p);


    }

    public boolean hayImagenParaMostrar()
    {
        return dataBase.darHayImagenParaMostrar();
    }

    public void cambiarEstadoImgPM()
    {
        dataBase.reiniciarImgParaMostrar();
    }

    public long getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(long totalProductos) {
        this.totalProductos = totalProductos;
    }
}




