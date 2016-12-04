package com.wexu.huckster.control;

import android.os.AsyncTask;

import com.wexu.huckster.control.vendedor.SellerActivity;
import com.wexu.huckster.modelo.Huckster;

/**
 * Created by ASUS1 on 03/12/2016.
 */

public class HiloRefresco extends AsyncTask<Void,Void,Void>{


    private SellerActivity seller;
    private Huckster principal;
    private boolean morirme;

    public HiloRefresco(SellerActivity s, Huckster p)
    {
        seller=s;
        principal=p;
        morirme=false;
    }

    @Override
    protected Void doInBackground(Void... params) {
        while (!morirme)
        {

           if(principal.hayImagenParaMostrar())
           {
               publishProgress();
               principal.cambiarEstadoImgPM();
           }



        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        seller.refrescar();
    }

    public void destruir()
    {
        morirme=true;
    }
}
