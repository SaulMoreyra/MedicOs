package com.example.prueba1.ui.perfil;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import org.json.JSONException;
import org.json.JSONObject;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    EditText etBirthday;
    private TextView tcor,tcur,tnom,tnac,tdir,ttel,tfot;
    private Button editar;
    String correo,curp,nombre,naci,dire,tel,foto;
    View root;


    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         root= inflater.inflate(R.layout.fragment_perfil, container, false);
        editar =(Button) root.findViewById(R.id.editar);
        editar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PerfilEditActivity.class);
                getActivity().startActivity(intent);
            }
        });
        /*
        */
        return  root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url= loginActivity2.base_url+"api/paciente/pacienteid/"+ menuPrincipal.id_usuario;
        cargar_perfil(url);
    }

    private void cargar_perfil(String url) {
        //listHistorial = new ArrayList<ItemHistorial>();
        JsonObjectRequest objetojson = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                                JSONObject obj_dato = response.optJSONObject("paciente");
                                PerfilEditActivity.datos=obj_dato;
                                correo =obj_dato.getString("correo");
                                curp =obj_dato.getString("curp");
                                nombre =obj_dato.getString("nombre")+obj_dato.getString("primer_apellido")+obj_dato.getString("segundo_apellido");
                                naci =obj_dato.getString("fecha_nacimiento");
                                tel =obj_dato.getString("telefono");
                                foto=obj_dato.getString("foto_perfil");
                                dire=obj_dato.getString("direccion");

                            cargar_datos();
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

    private void cargar_datos() {
        tcor =(TextView) root.findViewById(R.id.correo);
       // tcur=(TextView) root.findViewById(R.id.curp);
        tnom=(TextView) root.findViewById(R.id.nombre);
        tnac=(TextView) root.findViewById(R.id.nacimiento);
        tdir=(TextView) root.findViewById(R.id.direccion);
        ttel=(TextView) root.findViewById(R.id.telefono);
        //tfot=(TextView) root.findViewById(R.id.correo);
        tnom.setText(nombre);
        tcor.setText(correo);
        tnac.setText(naci);
        tdir.setText(dire);
        ttel.setText(tel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        String url= loginActivity2.base_url+"api/paciente/pacienteid/"+ menuPrincipal.id_usuario;
        cargar_perfil(url);
    }
}
