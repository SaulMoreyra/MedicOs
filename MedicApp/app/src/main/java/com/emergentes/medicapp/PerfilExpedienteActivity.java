package com.emergentes.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.emergentes.medicapp.ui.ActivityCitasAnteriores;

import org.json.JSONException;
import org.json.JSONObject;


public class PerfilExpedienteActivity extends AppCompatActivity {

    Button historial;
    Button diagnosticar;
    JSONObject dat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_expediente);
        Bundle datos = this.getIntent().getExtras();
        int tag_r = datos.getInt("tag");
        //System.out.println("TAG: "+tag_r);

        TextView nom = findViewById(R.id.nombre_paciente);
        TextView telefono = findViewById(R.id.telefono);
        TextView correo = findViewById(R.id.correo);
        TextView direccion = findViewById(R.id.direccion);
        TextView fecha_nac = findViewById(R.id.fecha_nac);

        try {
            dat = new JSONObject(datos.getString("all"));
            nom.setText(dat.getString("nombre"));
            telefono.setText(dat.getString("telefono"));
            correo.setText(dat.getString("correo"));
            direccion.setText(dat.getString("direccion"));
            fecha_nac.setText(dat.getString("fecha_nacimiento"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        historial = (Button) findViewById(R.id.btnHistorial);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i("VOLLEY","CLICKEO");
                    Intent i = new Intent(getApplicationContext(), ActivityCitasAnteriores.class);
                    i.putExtra("id_paciente",dat.getString("id_paciente"));
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        diagnosticar = (Button)findViewById(R.id.btnDiagnostico);
        if(tag_r==1){
            diagnosticar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("VOLLEY","CLICKEO");
                    Intent i = new Intent(getApplicationContext(), DiagnosticarActivity.class);
                    startActivity(i);
                }
            });
        }
        else
            diagnosticar.setVisibility(View.GONE);

    }
}
