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

import com.emergentes.medicapp.ui.ActivityCitasAnteriores;


public class PerfilExpedienteActivity extends AppCompatActivity {

    Button historial;
    Button diagnosticar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_expediente);
        Bundle datos = this.getIntent().getExtras();
        int tag_r = datos.getInt("tag");
        System.out.println("TAG: "+tag_r);


        historial = (Button) findViewById(R.id.btnHistorial);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("VOLLEY","CLICKEO");
                Intent i = new Intent(getApplicationContext(), ActivityCitasAnteriores.class);
                startActivity(i);
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
