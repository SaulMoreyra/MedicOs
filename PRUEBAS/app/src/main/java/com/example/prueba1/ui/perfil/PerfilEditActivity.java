package com.example.prueba1.ui.perfil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.prueba1.R;
import com.example.prueba1.VolleySingleton;
import com.example.prueba1.loginActivity2;
import com.example.prueba1.menuPrincipal;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static android.content.ContentValues.TAG;

public class PerfilEditActivity extends AppCompatActivity {

    EditText etBirthday;
    public static JSONObject datos;
    public EditText nombre,ap1,ap2,corre,direc,tel,curp;
    public Button guardar;
    public PerfilEditActivity() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_perfil_edit);
        etBirthday= (EditText) findViewById(R.id.fecha_nac);
        cargaDatos();
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PerfilEditActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        guardar = findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            etBirthday.setText(date);
        }
    };

    public void cargaDatos(){
        nombre=(EditText) findViewById(R.id.nombre);
        ap1=(EditText) findViewById(R.id.primerAp);
        ap2=(EditText) findViewById(R.id.segundoAp);
        corre=(EditText) findViewById(R.id.username);
        direc=(EditText) findViewById(R.id.direccion);
        tel=(EditText) findViewById(R.id.telefono);
        curp=(EditText) findViewById(R.id.curp);
        try {
            nombre.setText(datos.getString("nombre"));
            ap1.setText(datos.getString("primer_apellido"));
            ap2.setText(datos.getString("segundo_apellido"));
            etBirthday.setText(datos.getString("fecha_nacimiento"));
            corre.setText(datos.getString("correo"));
            direc.setText(datos.getString("direccion"));
            tel.setText(datos.getString("telefono"));
            curp.setText(datos.getString("curp"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void actualizaperfil(){

        String URL = "api/paciente/edit_paciente";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id_paciente", menuPrincipal.id_usuario);
            jsonBody.put("correo", corre.getText());
            jsonBody.put("curp", curp.getText());
            jsonBody.put("nombre", nombre.getText());
            jsonBody.put("primer_apellido", ap1.getText());
            jsonBody.put("segundo_apellido", ap2.getText());
            jsonBody.put("fecha_nacimiento", etBirthday.getText());
            jsonBody.put("direccion", direc.getText());
            jsonBody.put("telefono", tel.getText());
            jsonBody.put("foto_perfil", datos.getString("foto_perfil"));

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
                        Toast.makeText(PerfilEditActivity.this, mensaje,Toast.LENGTH_LONG).show();
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
        VolleySingleton.getInstanciaVolley(PerfilEditActivity.this).addToRequestQueue(stringRequest);

    }


}
