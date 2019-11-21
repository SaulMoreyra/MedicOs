package com.example.prueba1.ui.historial;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***aqui implementar metodo on create para rellenar el reciclerview*/
public class HistorialFragment extends Fragment {

    View v;
    private RecyclerView myrecyclreview;
    private List<ItemHistorial> listHistorial;

    public HistorialFragment(){
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_historial, container, false);
        myrecyclreview = (RecyclerView)v.findViewById(R.id.rViewHistorial);
        HistorialAdaptador historialAdaptador = new HistorialAdaptador(getContext(),listHistorial);
        myrecyclreview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclreview.setAdapter(historialAdaptador);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cargar_historial();

        /*listHistorial.add(new ItemHistorial("ant1","desc1"));
        listHistorial.add(new ItemHistorial("ant2","desc2"));
        listHistorial.add(new ItemHistorial("ant3","desc3"));
        listHistorial.add(new ItemHistorial("ant4","desc4"));
        listHistorial.add(new ItemHistorial("ant5","desc5"));*/

    }

    public void cargar_historial(String url){
        listHistorial = new ArrayList<>();
        JsonObjectRequest objetojson = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listaJson = response.optJSONArray("historial");
                            for (int i=0; i<listaJson.length(); i++){
                                JSONObject obj_dato = listaJson.getJSONObject(i);

                            }
                        } catch (JSONException e) {
                            Log.e("VOLLEY", e.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HistorialFragment.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        VolleySingleton.getInstanciaVolley(HistorialFragment.this).addToRequestQueue(objetojson);
        objetojson.setRetryPolicy(new DefaultRetryPolicy(400000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}