package com.emergentes.medicapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.emergentes.medicapp.R;
import com.emergentes.medicapp.adapters.PerfilExpedienteAdapter;
import com.emergentes.medicapp.clases.Cita;
import com.emergentes.medicapp.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.emergentes.medicapp.MedicoActivity.BASE_URL;

public class ActivityCitasAnteriores extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Cita> citas;
    PerfilExpedienteAdapter adapter;
    String id_paciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_anteriores);
        citas = new ArrayList<>();
        id_paciente  = this.getIntent().getStringExtra("id_paciente");
        getDataJSON();

        recyclerView = findViewById(R.id.recycler_citas_anteriores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PerfilExpedienteAdapter(getApplication(),citas);
        recyclerView.setAdapter(adapter);
    }

    public void getDataJSON(){
        citas.clear();
        final String URL = "api/medico/paciente_citas/"+id_paciente;

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, BASE_URL+URL, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(BASE_URL+URL);
                        try {
                            System.out.println("VOLLEY" + response.toString());
                            for (int i = 0; i<response.length(); i++) {
                                JSONObject datos = response.getJSONObject(i);
                                Cita cita = new Cita()
                                        .setIdcita(Integer.parseInt(datos.getString("id_cita")))
                                        .setDoctor(datos.getString("nombre"))
                                        .setFecha(datos.getString("fecha"))
                                        .setCedula_doc(datos.getString("cedula"))
                                        .setDiagnostico(datos.getString("diagnostico"))
                                        .setSintomas(datos.getString("sintomas"));
                                citas.add(cita);
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
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}
