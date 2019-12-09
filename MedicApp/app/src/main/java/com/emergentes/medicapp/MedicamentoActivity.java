package com.emergentes.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.emergentes.medicapp.adapters.MedicamentoAdapter;
import com.emergentes.medicapp.clases.Cita;
import com.emergentes.medicapp.clases.Medicamento;
import com.emergentes.medicapp.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.emergentes.medicapp.MedicoActivity.BASE_URL;

public class MedicamentoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Medicamento> medicamentos;
    MedicamentoAdapter adapter;
    String id_cita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);
        medicamentos = new ArrayList<>();
        id_cita = this.getIntent().getStringExtra("id_cita");
        getDataJSON();
        recyclerView = findViewById(R.id.recyclerViewMedicamento);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicamentoAdapter(this,medicamentos);
        recyclerView.setAdapter(adapter);
    }

    private void getDataJSON() {
        medicamentos.clear();
        final String URL = "api/medico/cita_medicamentos/"+id_cita;
        Log.i("VOLLEY",BASE_URL+URL);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, BASE_URL+URL, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(BASE_URL+URL);
                        try {
                            System.out.println("VOLLEY" + response.toString());
                            for (int i = 0; i<response.length(); i++) {
                                JSONObject datos = response.getJSONObject(i);
                                Medicamento medicamento = new Medicamento()
                                        .setId_cita(Integer.parseInt(datos.getString("id_cita")))
                                        .setId_medicamento(Integer.parseInt(datos.getString("id_medicamento")))
                                        .setMedicamento(datos.getString("medicamento"))
                                        .setDosis(datos.getString("dosis"))
                                        .setHora_aplicacion(datos.getString("horario_aplicacion"))
                                        .setDescrpcion(datos.getString("descripcion"));

                                medicamentos.add(medicamento);
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
