package com.wexu.huckster.control.vendedor;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.wexu.huckster.R;
import com.wexu.huckster.control.HiloEspera;
import com.wexu.huckster.control.HiloRefresco;
import com.wexu.huckster.control.productos.ProductsAdapter;
import com.wexu.huckster.modelo.Huckster;
import com.wexu.huckster.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class SellerActivity extends AppCompatActivity implements
        ProfileFragment.OnFragmentInteractionListener,
        AdapterView.OnItemSelectedListener
{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    /*
    Declarar instancias globales
    */

    private Typeface nameFont;
    private Typeface priceFont;
    private Huckster principal;
    private HiloRefresco hilo;
    private RecyclerView.Adapter adapter;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Bundle extras=getIntent().getExtras();
        principal=(Huckster) extras.getSerializable("modelo");
        String info=extras.getString("info");
        agregarToolbar();
        if(info==null||info.equals(""))
        {
            new HiloEspera(new ProgressDialog(this),this,principal).execute();
        }else {

            lanzarFragment();

        }





    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }


    public void lanzarFragment()
    {


     RecyclerView rv= (RecyclerView)findViewById(R.id.reciclador5);
     rv.setHasFixedSize(true);

     ArrayList<Producto> misProductos=principal.darUsuario().getMisProductos();
     List items = new ArrayList();

     for(int i=0; i<misProductos.size();i++)
     {
     Producto p= misProductos.get(i);
     items.add(p);
     System.out.println(p.getNombre());
     }


        adapter= new ProductsAdapter(items);
     RecyclerView.LayoutManager lManager= new LinearLayoutManager(this);

     //**********
     rv.setAdapter(adapter);
     rv.setLayoutManager(lManager);
     hilo=new HiloRefresco(this,principal);
     hilo.execute();

    }




    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        ArrayList<Producto> misProductos=principal.darUsuario().getMisProductos();
        bundle.putSerializable("productos",misProductos);


        ProfileFragment pf= new ProfileFragment();

        nameFont= Typeface.createFromAsset(getAssets(), "fonts/Raleway-Light.ttf");
        priceFont= Typeface.createFromAsset(getAssets(), "fonts/lilita.ttf");

        //adapter.addFragment(mf, "Mis productos");

       // adapter.addFragment(pf, "Mi perfil");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    //métodos manejo toolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_products, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public void  agregarProducto(View view)
    {
        Intent i=new Intent(this,PublishActivity.class);
        i.putExtra("modelo",principal);
        startActivity(i);
        if(hilo!=null)
        hilo.destruir();
        finish();

    }




    public void refrescar()
    {

        adapter.notifyDataSetChanged();


    }



    //fin métodos toolbar
}