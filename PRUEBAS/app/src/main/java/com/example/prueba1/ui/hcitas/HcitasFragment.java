package com.example.prueba1.ui.hcitas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.prueba1.ui.historial.HistorialFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***aqui implementar metodo on create para rellenar el reciclerview*/
public class    HcitasFragment extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<ItemHcitas> listHcitas;

    public HcitasFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_hcitas, container, false);
        myrecyclerview = (RecyclerView)v.findViewById(R.id.rViewHcitas);
        HcitasAdaptador hcitasAdaptador = new HcitasAdaptador(getContext(),listHcitas);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(hcitasAdaptador);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cargarHcitas();

        /*listHcitas.add(new ItemHcitas("12/12/12","12:12","Apendicitis","Chingo Dolor","$5","Domicilio"));
        listHcitas.add(new ItemHcitas("1/1/1","1:1","Apendicitis","Chingo Dolor","$5","Domicilio"));
        listHcitas.add(new ItemHcitas("12/12/12","12:12","Apendicitis","Chingo Dolor","$5","Domicilio"));
        listHcitas.add(new ItemHcitas("2/2/2","2:2","Apendicitis","Chingo Dolor","$5","Domicilio"));
        listHcitas.add(new ItemHcitas("12/12/12","12:12","Apendicitis","Chingo Dolor","$5","Domicilio"));*/

    }

    public void cargarHcitas(String url){
        listHcitas = new ArrayList<>();
        JsonObjectRequest objetojson = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listaJson = response.optJSONArray("hcitas");
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
                        Toast.makeText(HcitasFragment.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        VolleySingleton.getInstanciaVolley(HcitasFragment.this).addToRequestQueue(objetojson);
        objetojson.setRetryPolicy(new DefaultRetryPolicy(400000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

}