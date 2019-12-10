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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.emergentes.medicapp.MedicoActivity;
import com.emergentes.medicapp.R;
import com.emergentes.medicapp.clases.Medicamento;
import com.emergentes.medicapp.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static com.emergentes.medicapp.MedicoActivity.BASE_URL;

public class AgendaFragment extends Fragment {
    CheckBox [] dias;
    Spinner [] inicio;
    Spinner [] fin;
    String [] labels;
    int id_medico = 1;
    String [] horas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agenda, container, false);

        dias = new CheckBox[7];
        inicio = new Spinner[7];
        fin = new Spinner[7];
        labels = new String[]{"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        horas = new String[]{
                "00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00",
                "08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00",
                "16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"
        };

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

        getDataJSON();
        final Button update = root.findViewById(R.id.actualizar);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONArray arraydias = new JSONArray();
                    for(int i=0; i<7; i++){
                        arraydias.put(new JSONArray(new String[]{
                                labels[i],
                                inicio[i].getSelectedItem().toString(),
                                fin[i].getSelectedItem().toString(),
                                dias[i].isChecked()+"",
                                (i+1)+""
                        }));
                    }

                    JSONObject send = new JSONObject();
                    send.put("id_medico", id_medico);
                    send.put("dias",arraydias);

                    updateData(send);
                    Log.i("VOLLEY",send.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    public void updateData(JSONObject jsonBody ){
        final String URL = "api/medico/horario";
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
                            Toast.makeText(getContext(),mensaje, Toast.LENGTH_SHORT).show();
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
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void getDataJSON() {
        final String URL = "api/medico/get_horario/"+id_medico;
        Log.i("VOLLEY",MedicoActivity.BASE_URL+URL);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, MedicoActivity.BASE_URL+URL, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(BASE_URL+URL);
                        try {
                            System.out.println("VOLLEY" + response.toString());
                            for (int i = 0; i<response.length(); i++) {
                                JSONObject datos = response.getJSONObject(i);
                                dias[i].setChecked(Boolean.parseBoolean(datos.getString("status")));
                                inicio[i].setSelection(findPosition(datos.getString("hora_ingreso")));
                                fin[i].setSelection(findPosition(datos.getString("hora_salida")));
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
        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public final int findPosition(String input){
        for(int i=0; i<horas.length; i++){
            if(horas[i].equals(input)) return i;
        }
        return -1;
    }
}