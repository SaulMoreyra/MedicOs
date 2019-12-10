package com.emergentes.medicapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.emergentes.medicapp.MedicoActivity;
import com.emergentes.medicapp.R;
import com.emergentes.medicapp.clases.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MedicamentoNew extends AppCompatActivity {

    JSONObject dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento_new);

        try {
            dat = new JSONObject(getIntent().getStringExtra("all"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final TextView nombre_medicamento = findViewById(R.id.nombre_medicamento);
        final TextView dosis = findViewById(R.id.dosis);
        final TextView horario = findViewById(R.id.horario);
        final TextView descripcion = findViewById(R.id.descripcion);

        Button guardar = findViewById(R.id.save_medicamento);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id_cita",dat.getString("id_cita"));
                    jsonObject.put("medicamento",nombre_medicamento.getText().toString());
                    jsonObject.put("dosis",dosis.getText().toString());
                    jsonObject.put("horario_aplicacion",horario.getText().toString());
                    jsonObject.put("descripcion",descripcion.getText().toString());

                    sendMedicamento(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void sendMedicamento(JSONObject jsonBody ){
        final String URL = "api/medico/new_medicamento";
        final String requestBody = jsonBody.toString();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                MedicoActivity.BASE_URL+URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String mensaje = jsonObject.getString("mensaje");
                            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Log.e("VOLLEY","PARSEO " +  e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                //String statusCode = String.valueOf(response.statusCode);
                return super.parseNetworkResponse(response);
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
