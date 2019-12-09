package com.emergentes.medicapp.ui.Agenda;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.emergentes.medicapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AgendaFragment extends Fragment {
    CheckBox [] dias;
    Spinner [] inicio;
    Spinner [] fin;
    String [] labels;
    int id_medico = 1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agenda, container, false);

        dias = new CheckBox[7];
        inicio = new Spinner[7];
        fin = new Spinner[7];
        labels = new String[]{"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};


        dias[0] = root.findViewById(R.id.lunes);
        dias[1] = root.findViewById(R.id.martes);
        dias[2] = root.findViewById(R.id.miercoles);
        dias[3] = root.findViewById(R.id.jueves);
        dias[4] = root.findViewById(R.id.viernes);
        dias[5] = root.findViewById(R.id.sabado);
        dias[6] = root.findViewById(R.id.domingo);

        inicio[0] = root.findViewById(R.id.inicio1);
        inicio[1] = root.findViewById(R.id.inicio2);
        inicio[2] = root.findViewById(R.id.inicio3);
        inicio[3] = root.findViewById(R.id.inicio4);
        inicio[4] = root.findViewById(R.id.inicio5);
        inicio[5] = root.findViewById(R.id.inicio6);
        inicio[6] = root.findViewById(R.id.inicio7);

        fin[0] = root.findViewById(R.id.fin1);
        fin[1] = root.findViewById(R.id.fin2);
        fin[2] = root.findViewById(R.id.fin3);
        fin[3] = root.findViewById(R.id.fin4);
        fin[4] = root.findViewById(R.id.fin5);
        fin[5] = root.findViewById(R.id.fin6);
        fin[6] = root.findViewById(R.id.fin7);


        Button update = root.findViewById(R.id.actualizar);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONArray arraydias = new JSONArray();
                    for(int i=0; i<7; i++){
                        arraydias.put(new JSONArray(new String[]{
                                labels[0],
                                inicio[0].getSelectedItem().toString(),
                                fin[0].getSelectedItem().toString(),
                                dias[0].isChecked()+"",
                        }));
                    }

                    JSONObject send = new JSONObject();
                    send.put("id_medico", id_medico);
                    send.put("dias",arraydias);

                    Log.i("VOLLEY",send.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return root;
    }


    /**
     public void doPedido(){


     String URL = "nuevo/pedido";
     JSONObject jsonBody = new JSONObject();
     try {
     jsonBody.put("idcliente", VistaClienteActivity.cliente.get("idcliente")+"");
     jsonBody.put("idproducto", idproducto+"");
     jsonBody.put("latitud", latitud+"");
     jsonBody.put("longitud", longitud+"");
     Log.i("VOLLEY", jsonBody.toString());
     } catch (JSONException e) {
     e.printStackTrace();
     }
     final String requestBody = jsonBody.toString();
     StringRequest stringRequest = new StringRequest(Request.Method.POST, LoginActivity.BASE_URL+URL,
     new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {
    try {
    JSONObject jsonObject = new JSONObject(response);
    String mensaje = jsonObject.getString("mensaje");
    finish();
    } catch (JSONException e) {
    Log.e("VOLLEY","PARSEO " +  e.toString());
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
     VolleySingleton.getInstance(VistaPagoActivity.this).addToRequestQueue(stringRequest);
     }
     **/
}