package com.emergentes.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.emergentes.medicapp.ui.ActivityCitasAnteriores;


public class PerfilExpedienteActivity extends AppCompatActivity {

    Button historial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_expediente);
        /*
        recyclerView = findViewById(R.id.recyclerViewHistorial);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PerfilExpedienteAdapter(this,getCitas());
        recyclerView.setAdapter(adapter);
         */

        historial = (Button) findViewById(R.id.btnHistorial);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("VOLLEY","CLICKEO");
                Intent i = new Intent(getApplicationContext(), ActivityCitasAnteriores.class);
                startActivity(i);
            }
        });

    }
/*
    private List<Cita> getCitas() {

        citas = new ArrayList<Cita>();
        Cita cita = new Cita(1,1,1,"José Rios José", "01/01/2019","10:00",-7.99,0.199,"Se va a morir","diarrea",900,'c','c');
        citas.add(cita);
        Cita cita1 = new Cita(3,2,5, "José el Fogozo", "02/01/2019","16:30",-7.99,0.199,"Se va a morir","fiebre",900,'c','c');
        Cita cita2 = new Cita(2,2,10, "Deisy Ignacio Luis","07/01/2019","16:30",-7.99,0.199,"Se va a morir","fiebre",900,'c','c');
        citas.add(cita1);
        citas.add(cita2);
        citas.add(cita1);
        citas.add(cita);
        citas.add(cita1);
        citas.add(cita);
        citas.add(cita1);
        return citas;
    }
    */
}
