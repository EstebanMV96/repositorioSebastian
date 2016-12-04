package com.wexu.huckster.control.productos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wexu.huckster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StationeryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StationeryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StationeryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recycler;
    private ProductsAdapter productsAdapter;
    private RecyclerView.LayoutManager lManager;

    private OnFragmentInteractionListener mListener;

    public StationeryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StationeryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StationeryFragment newInstance(String param1, String param2) {
        StationeryFragment fragment = new StationeryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_stationery, container, false);

        // Inicializar Productos
        List items = new ArrayList();

//        Producto p=new Producto("Papas", "", R.drawable.papas, 2000, "julizuoso", Producto.COMIDA);
//        Producto q=new Producto("Donas", "", R.drawable.dona, 2000, "julizuoso", Producto.COMIDA);
//        Producto r=new Producto("Brownie", "", R.drawable.browniw, 1500, "julizuoso", Producto.COMIDA);
//        items.add(p);
//        items.add(q);
//        items.add(r);
//        items.add(p);
//        items.add(q);
//        items.add(r);

        // Obtener el FoodRecycler
        recycler = (RecyclerView) root.findViewById(R.id.reciclador2);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        productsAdapter = new ProductsAdapter(items);
        recycler.setAdapter(productsAdapter);

        // Inflate the layout for this fragment
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
