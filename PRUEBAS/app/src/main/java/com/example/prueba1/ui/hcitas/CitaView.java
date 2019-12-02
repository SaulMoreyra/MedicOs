package com.example.prueba1.ui.hcitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

public class CitaView extends AppCompatActivity {
    public TextView no,esp,tel,fecha,hora,tipo,diag,sint,costo;
    public int id_cita;

    private RecyclerView myrecyclerview;

    private List<ItemMedicamento> listHcitas;
    private HmedicamentosAdaptador hcitasAdaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita_view);
        no=findViewById(R.id.nombremedico);
        esp=findViewById(R.id.especialidad);
        tel=findViewById(R.id.numero);
        fecha=findViewById(R.id.fecha);
        hora=findViewById(R.id.hora);
        tipo=findViewById(R.id.tipo);
        diag=findViewById(R.id.diagnostico);
        sint=findViewById(R.id.sintomas);
        costo=findViewById(R.id.costo);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            id_cita= b.getInt("id_cita");
            no.setText((String) b.get("nombre"));
            esp.setText((String) b.get("esp"));
            tel.setText((String) b.get("tel"));
            fecha.setText((String) b.get("fecha"));
            hora.setText((String) b.get("hora"));
            costo.setText((String) b.get("costo"));
            tipo.setText((String) b.get("tipo"));
            diag.setText((String) b.get("diagnostico"));
            sint.setText((String) b.get("sintomas"));
        }

        myrecyclerview = (RecyclerView)findViewById(R.id.rViewMedicinas);
        String url= loginActivity2.base_url+"api/paciente/citafinalizada/"+ id_cita;
        cargarMedicamentos(url);
        hcitasAdaptador = new HmedicamentosAdaptador(CitaView.this,listHcitas);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(CitaView.this));
        myrecyclerview.setAdapter(hcitasAdaptador);

    }

    private void cargarMedicamentos(String url) {
        listHcitas = new ArrayList<>();
        JsonObjectRequest objetojson = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listaJson = response.optJSONArray("medicamentos");
                            for (int i=0; i<listaJson.length(); i++){
                                JSONObject obj_dato = listaJson.getJSONObject(i);
                                int id= obj_dato.getInt("id_cita");
                                String f =obj_dato.getString("medicamento");
                                String h =obj_dato.getString("dosis");
                                String d =obj_dato.getString("horario_aplicacion");
                                String s =obj_dato.getString("descripcion");
                                listHcitas.add(new ItemMedicamento(id,f,h,d,s));
                            }

                            hcitasAdaptador.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e("VOLLEY", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(HcitasFragment.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        VolleySingleton.getInstanciaVolley(CitaView.this).addToRequestQueue(objetojson);
        objetojson.setRetryPolicy(new DefaultRetryPolicy(400000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


}
