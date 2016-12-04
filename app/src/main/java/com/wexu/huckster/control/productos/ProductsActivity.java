package com.wexu.huckster.control.productos;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.wexu.huckster.R;
import com.wexu.huckster.control.HiloEspera;
import com.wexu.huckster.modelo.Huckster;

import java.util.ArrayList;
import java.util.List;


public class ProductsActivity extends AppCompatActivity implements
        FoodFragment.OnFragmentInteractionListener,
        StationeryFragment.OnFragmentInteractionListener,
        OthersFragment.OnFragmentInteractionListener,
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        //TOOLBAR
        // Conectamos el Layout al objeto Toolbar
        toolbar = (Toolbar) findViewById(R.id.search_bar);
        // Configuración de la barra de herramientas (toolbar ) como la Barra de acciones (ActionBar)
        // con la llamada de setSupportActionBar ()
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras=getIntent().getExtras();
        principal=(Huckster) extras.getSerializable("modelo");
        String info=extras.getString("info");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if(info==null||info.equals(""))
        {
            new HiloEspera(new ProgressDialog(this),this,principal).execute();
        }else {

            lanzarFragment();

        }





    }


    public void lanzarFragment()
    {
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        FoodFragment ff= new FoodFragment();
        StationeryFragment sf= new StationeryFragment();
        OthersFragment of= new OthersFragment();

        nameFont= Typeface.createFromAsset(getAssets(), "fonts/Raleway-Light.ttf");
        priceFont= Typeface.createFromAsset(getAssets(), "fonts/lilita.ttf");

        adapter.addFragment(ff, "Comida");
        adapter.addFragment(sf, "Papelería");
        adapter.addFragment(of, "Otros");

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
                int id = item.getItemId();
                if (id == R.id.logout) {
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

            //fin métodos toolbar
        }
