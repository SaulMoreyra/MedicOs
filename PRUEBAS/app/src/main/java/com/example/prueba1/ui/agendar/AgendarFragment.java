package com.example.prueba1.ui.agendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.prueba1.R;
import com.example.prueba1.VolleySingleton;
import com.example.prueba1.loginActivity2;
import com.example.prueba1.menuPrincipal;
import com.example.prueba1.ui.historial.HistorialAdaptador;
import com.example.prueba1.ui.historial.ItemHistorial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AgendarFragment extends Fragment {
    View v;
    private RecyclerView myrecyclreview;
    private List<ItemDoctor> listaDoctores;
    private DoctoresAdaptador historialAdaptador;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_agendar, container, false);
        myrecyclreview = (RecyclerView)v.findViewById(R.id.rViewDoctores);
         historialAdaptador = new DoctoresAdaptador(getContext(),listaDoctores);
        myrecyclreview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclreview.setAdapter(historialAdaptador);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url= loginActivity2.base_url+"api/paciente/medicos";
        cargar_medicos(url);
        /*listaDoctores = new ArrayList<ItemDoctor>();
        listaDoctores.add(new ItemDoctor(1,"jose_rios456@hotmail.com","Jose Rios Jose","pediatra","asdasdasd","Instutito Tecnologico de Oaxaca","9511950857","130","privada de sompantles no 16"));
        listaDoctores.add(new ItemDoctor(1,"erick_andradhe@hotmail.com","Andrade Revilla Erick","general","asdasdasd","Instutito Tecnologico de Oaxaca","9511950857","130","Fraccionamiento las casas"));
        listaDoctores.add(new ItemDoctor(1,"saul_renato@hotmail.com","Saul Renato Moreyra","enfermero","asdasdasd","Instutito Tecnologico de Oaxaca","9511950857","130","Colonia del bosque"));
        listaDoctores.add(new ItemDoctor(1,"deysi_ignacio@hotmail.com","Guillermo Silva","pediatra","asdasdasd","Instutito Tecnologico de Oaxaca","9511950857","130","Fraccionamiento lomas de nazareno"));
        listaDoctores.add(new ItemDoctor(1,"guillermo@hotmail.com","Rosas Maganda Ricardo","gastroenterologo","asdasdasd","Instutito Tecnologico de Oaxaca","9511950857","130","Privada de division orienta"));
        listaDoctores.add(new ItemDoctor(1,"gaby456@hotmail.com","Gabi vazquez Vazquez","Urologo","asdasdasd","Instutito Tecnologico de Oaxaca","9511950857","130","privada de pochutla no 16"));*/

    }

    private void cargar_medicos(String url) {
        listaDoctores = new ArrayList<ItemDoctor>();
        JsonObjectRequest objetojson = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listaJson = response.optJSONArray("medicos");
                            for (int i=0; i<listaJson.length(); i++){
                                JSONObject obj_dato = listaJson.getJSONObject(i);
                                int id =obj_dato.getInt("id_medico");
                                String c =obj_dato.getString("correo");
                                String n =obj_dato.getString("nombre")+" ";
                                String p1 =obj_dato.getString("primer_apellido")+" ";
                                String p2 =obj_dato.getString("segundo_apellido")+" ";
                                String e =obj_dato.getString("especialidad");
                                String ce =obj_dato.getString("cedula");
                                String pro =obj_dato.getString("procedencia");
                                String la =obj_dato.getString("latitud");
                                String lo =obj_dato.getString("longitud");
                                String t =obj_dato.getString("telefono");
                                String cxc =obj_dato.getString("costoxconsulta");
                                String r =obj_dato.getString("rfc");
                                String cn =obj_dato.getString("contrasena");
                                String f =obj_dato.getString("foto_perfil");
                                listaDoctores.add(new ItemDoctor(id,c,n+p1+p2,e,ce,pro,t,cxc,la,lo));
                                System.out.println("VOLLEY" );
                            }
                            historialAdaptador.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e("VOLLEY", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(objetojson);
        objetojson.setRetryPolicy(new DefaultRetryPolicy(400000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

}