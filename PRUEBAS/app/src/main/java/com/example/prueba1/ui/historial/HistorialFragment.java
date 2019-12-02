package com.example.prueba1.ui.historial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.prueba1.R;
import com.example.prueba1.VolleySingleton;
import com.example.prueba1.loginActivity2;
import com.example.prueba1.menuPrincipal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/***aqui implementar metodo on create para rellenar el reciclerview*/
public class HistorialFragment extends Fragment {

    View v;
    private RecyclerView myrecyclreview;
    private List<ItemHistorial> listHistorial;
    private HistorialAdaptador historialAdaptador;
    private Button nuevo;
    public HistorialFragment(){ }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_historial, container, false);
        myrecyclreview = (RecyclerView)v.findViewById(R.id.rViewHistorial);
         historialAdaptador = new HistorialAdaptador(getContext(),listHistorial);
        myrecyclreview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclreview.setAdapter(historialAdaptador);
        nuevo =(Button) v.findViewById(R.id.nuevo);
        nuevo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HistorialNew.class);
                getActivity().startActivity(intent);
            }
        });


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String url=loginActivity2.base_url+"api/paciente/antecedentes/"+ menuPrincipal.id_usuario;
        //
        //cargar_historial(url);
        listHistorial = new ArrayList<ItemHistorial>();
    }

    public void cargar_historial(String url){
        //tipo,n,d,alistHistorial = new ArrayList<>();
        listHistorial.clear();
        JsonObjectRequest objetojson = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listaJson = response.optJSONArray("observaciones");
                            for (int i=0; i<listaJson.length(); i++){
                                JSONObject obj_dato = listaJson.getJSONObject(i);
                                   int id =obj_dato.getInt("id_observacion");
                                 String t =obj_dato.getString("tipo_obs");
                                 String n =obj_dato.getString("nombre_obs");
                                 String d =obj_dato.getString("descripcion_obs");
                                 String a =obj_dato.getString("antiguedad");
                                listHistorial.add(new ItemHistorial(id,t,n,d,a));
                            }

                            System.out.println("VOLLEY"+listaJson.toString() );
                            historialAdaptador.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e("VOLLEY", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(objetojson);
        objetojson.setRetryPolicy(new DefaultRetryPolicy(400000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        String url=loginActivity2.base_url+"api/paciente/antecedentes/"+ menuPrincipal.id_usuario;
        cargar_historial(url);
        historialAdaptador = new HistorialAdaptador(getContext(),listHistorial);
        myrecyclreview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclreview.setAdapter(historialAdaptador);
    }
}