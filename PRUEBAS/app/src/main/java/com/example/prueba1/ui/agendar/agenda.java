package com.example.prueba1.ui.agendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.example.prueba1.ui.dialog.DatePickerFragment;
import com.example.prueba1.ui.perfil.PerfilEditActivity;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class agenda extends AppCompatActivity implements View.OnClickListener {
    public TextView no,esp,tel,direcci,proce,costo2;
    EditText etPlannedDate;
    EditText etHora,sinto;
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();
    public String id_medico;
    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);
    public Button confirmar;
    public RadioButton consultorio,domicilio;
    public String opcion,costo;

    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    private int id_producto;
    private static final String CONFIG_CLIENT_ID = "AUWynYmbRF8mAw8QknBiI53Bq5BC8EvQh6R_ybCp-2JjcwR4K68Dp0H3HUFKXdCAd67c9qVf2spQw00p";
    private static final int REQUEST_CODE_PAYMENT = 1;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)

            // configuracion minima del ente
            .merchantName("uberemergentes")
            .merchantPrivacyPolicyUri(
                    Uri.parse("https://www.mi_tienda.com/privacy"))
            .merchantUserAgreementUri(
                    Uri.parse("https://www.mi_tienda.com/legal"));



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_cita);
        no=findViewById(R.id.nombreM);
        esp=findViewById(R.id.EspecialidadM);
        tel=findViewById(R.id.telefonoM);
        direcci=findViewById(R.id.direcionM);
        proce=findViewById(R.id.procedenciaM);
        confirmar=findViewById(R.id.confirmarcita);
        sinto=findViewById(R.id.sintomas);
        consultorio=findViewById(R.id.consultorio);
        domicilio=findViewById(R.id.domicilio);
        costo2=findViewById(R.id.costo);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            id_medico=b.get("id_medico")+"";
            no.setText((String) b.get("nombre"));
            esp.setText((String) b.get("especialidad"));
            tel.setText((String) b.get("telefono"));
            direcci.setText((String) b.get("direccion"));
            proce.setText((String) b.get("procedencia"));
            costo=(String)b.get("costo");
        }
        costo2.setText("$"+costo);
        etPlannedDate= (EditText) findViewById(R.id.et_mostrar_fecha_picker);
        etPlannedDate.setOnClickListener(this);
        etHora = (EditText) findViewById(R.id.et_mostrar_hora_picker);
        etHora.setOnClickListener(this);

        /*confirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(agenda.this, "confirmado", Toast.LENGTH_SHORT).show();
            }
        });*/

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        confirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String al="";
                if(consultorio.isChecked()){
                    al="CITA EN CONSULTORIO";
                }else if(domicilio.isChecked()){
                    al="CITA A DOMICILIO";
                }
                PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(costo), "MXN",  al, PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(agenda.this, PaymentActivity.class);

                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

                startActivityForResult(intent, REQUEST_CODE_PAYMENT);
            }
        });

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_mostrar_fecha_picker:
                showDatePickerDialog();
                break;
            case R.id.et_mostrar_hora_picker:
                obtenerHora();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etPlannedDate.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(),"datPicker");
    }
    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                etHora.setText(horaFormateada + DOS_PUNTOS + minutoFormateado);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

    public void confirmarcita(){

        String URL = "api/paciente/newCita";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id_paciente", menuPrincipal.id_usuario);
            jsonBody.put("id_medico", id_medico);
            jsonBody.put("fecha", etPlannedDate.getText());
            jsonBody.put("hora", etHora.getText());
            jsonBody.put("latitud", 1.0000);
            jsonBody.put("longitud", 1.0000);
            jsonBody.put("diagnostico", " ");
            jsonBody.put("sintomas",sinto.getText());
            jsonBody.put("costo","300");
            jsonBody.put("tipo_cita", opcion);

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
                        Toast.makeText(agenda.this, mensaje,Toast.LENGTH_LONG).show();
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
        VolleySingleton.getInstanciaVolley(agenda.this).addToRequestQueue(stringRequest);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data
                    .getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {

                    // informacion extra del pedido
                    System.out.println(confirm.toJSONObject().toString(4));
                    System.out.println(confirm.getPayment().toJSONObject()
                            .toString(4));
                    //aqui hacer el post si es que si se pago

                    Toast.makeText(getApplicationContext(), "Orden procesada",
                            Toast.LENGTH_LONG).show();
                    if(consultorio.isChecked()){
                        opcion="c";
                    }else if(domicilio.isChecked()){
                        opcion="d";
                    }

                    confirmarcita();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            System.out.println("El usuario canceló el pago");
        }
    }

}
