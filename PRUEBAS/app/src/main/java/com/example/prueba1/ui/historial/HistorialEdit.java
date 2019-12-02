package com.example.prueba1.ui.historial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.prueba1.R;
import com.example.prueba1.VolleySingleton;
import com.example.prueba1.loginActivity2;
import com.example.prueba1.menuPrincipal;
import com.example.prueba1.ui.perfil.PerfilEditActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class HistorialEdit extends AppCompatActivity {

    public EditText tipo,nombre,descripcion,antiguedad;
    public int id_antecedente;
    public Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_edit);
        tipo = findViewById(R.id.editTipoA);
        nombre = findViewById(R.id.editNombreA);
        descripcion= findViewById(R.id.editDescripcionA);
        antiguedad= findViewById(R.id.editAntiguedadA);
        antiguedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        HistorialEdit.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            id_antecedente= b.getInt("id_observacion");
            tipo.setText((String) b.get("tipo_obs"));
            nombre.setText((String) b.get("nombre_obs"));
            descripcion.setText((String) b.get("descripcion_obs"));
            antiguedad.setText((String) b.get("antiguedad"));
        }
        guardar= findViewById(R.id.guardarHistorial);
        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actualizaperfil();
            }
        });

    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            month = month + 1;
            Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

            String date = year + "-" + day + "-" + month;
            antiguedad.setText(date);
        }
    };


    public void actualizaperfil(){

        String URL = "api/paciente/editarAntecedente";

        JSONObject jsonBody = new JSONObject();
        try {
             jsonBody.put("id_observacion", id_antecedente);
            jsonBody.put("tipo_obs", tipo.getText());
            jsonBody.put("nombre_obs", nombre.getText());
            jsonBody.put("descripcion_obs", descripcion.getText());
            jsonBody.put("antiguedad", antiguedad.getText());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = jsonBody.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginActivity2.base_url+URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(LoginActivity.this, response,Toast.LENGTH_LONG).show();
                Log.i("VOLLEY", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String mensaje = jsonObject.getString("mensaje");
                    Log.i("VOLLEY", mensaje);
                    if(mensaje.equals("ok")) {
                        onBackPressed();
                    }else{
                        Toast.makeText(HistorialEdit.this, mensaje,Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e("VOLLEY", e.toString());
                }
            }
        }, new Response.ErrorListener() {
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
        VolleySingleton.getInstanciaVolley(HistorialEdit.this).addToRequestQueue(stringRequest);

    }
}
