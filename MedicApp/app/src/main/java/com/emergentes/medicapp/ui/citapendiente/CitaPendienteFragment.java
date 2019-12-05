package com.emergentes.medicapp.ui.citapendiente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.emergentes.medicapp.R;
import com.emergentes.medicapp.adapters.CitaPendienteAdapter;
import com.emergentes.medicapp.clases.Cita;
import com.emergentes.medicapp.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.emergentes.medicapp.MedicoActivity.BASE_URL;


public class CitaPendienteFragment extends Fragment {
    private CitaPendienteViewModel slideshowViewModel;
    RecyclerView recyclerView;
    List<Cita> citas;
    CitaPendienteAdapter adapter;

    int idcita, idproducto;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        slideshowViewModel = ViewModelProviders.of(this).get(CitaPendienteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cita_pendiente, container,false );//View v = inflater.inflate(R.layout.camera_fragment, null);
        //final TextView textView = root.findViewById(R.id.text_slideshow);
        citas = new ArrayList<>();
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText("aqui estoy");
            }
        });
        System.out.println("VOLLEY  SATAAAA");
        getDataJSON();
        System.out.println("VOLLEY REGRESE");
        recyclerView = root.findViewById(R.id.recyclerViewCitaPendiente);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CitaPendienteAdapter(getContext(),citas,1);
        recyclerView.setAdapter(adapter);
        return root;
    }



    String URL = "";
    public void getDataJSON(){
        final int idmedico = 1;
        citas.clear();
        URL = "api/medico/citas_pendientes/"+idmedico;

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, BASE_URL+URL, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(BASE_URL+URL);
                        try {
                            System.out.println("VOLLEY" + response.toString());
                            for (int i = 0; i<response.length(); i++) {
                                JSONObject datos = response.getJSONObject(i);
                                citas.add(new Cita(
                                        Integer.parseInt(datos.getString("id_cita")),
                                        Integer.parseInt(datos.getString("id_paciente")),
                                        idmedico,
                                        datos.getString("nombre"),
                                        datos.getString("fecha"),
                                        datos.getString("hora"),
                                        Double.parseDouble(datos.getString("latitud")),
                                        Double.parseDouble(datos.getString("longitud")),
                                        null,
                                        null,
                                        800,
                                        datos.getString("tipo_cita").charAt(0),
                                        'p'
                                ));
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            System.out.println(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());

                    }
                });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }

}