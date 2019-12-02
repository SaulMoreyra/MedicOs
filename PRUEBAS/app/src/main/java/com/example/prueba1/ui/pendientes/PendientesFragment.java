package com.example.prueba1.ui.pendientes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.prueba1.ui.historial.HistorialFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***aqui implementar metodo on create para rellenar el reciclerview*/
public class PendientesFragment extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<ItemPendientes> listPendientes;
    PendientesAdaptador pendientesAdaptador;
    public PendientesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pendientes,container,false);
        myrecyclerview = (RecyclerView)v.findViewById(R.id.rViewPendientes);
         pendientesAdaptador = new PendientesAdaptador(getContext(),listPendientes);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(pendientesAdaptador);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url= loginActivity2.base_url+"api/paciente/citaspendientes/"+ menuPrincipal.id_usuario;
        cargarPendiientes(url);

        /*listPendientes.add(new ItemPendientes("5/5/2019","5:5","$300","Consultorio"));
        listPendientes.add(new ItemPendientes("6/6/2019","6:5","$250","Casa"));
        listPendientes.add(new ItemPendientes("7/7/2019","7:5","$550","Consultorio"));
        listPendientes.add(new ItemPendientes("8/8/2019","8:5","$150","Casa"));
        listPendientes.add(new ItemPendientes("9/10/2019","9:5","$100","Consultorio"));*/

    }

    public void cargarPendiientes(String url){
        listPendientes = new ArrayList<ItemPendientes>();
        JsonObjectRequest objetojson = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listaJson = response.optJSONArray("citas");
                            for (int i=0; i<listaJson.length(); i++){
                                JSONObject obj_dato = listaJson.getJSONObject(i);
                                int id = obj_dato.getInt("id_cita");
                                String f =obj_dato.getString("fecha");
                                String h=obj_dato.getString("hora");
                                String c=obj_dato.getString("costo");
                                String t = (obj_dato.getString("tipo_cita").equals("d")) ? "Domicilio" : "Consultorio";
                                listPendientes.add(new ItemPendientes(id,f,h,c,t));
                            }
                            pendientesAdaptador.notifyDataSetChanged();
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

}