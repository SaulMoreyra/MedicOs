package com.emergentes.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.emergentes.medicapp.adapters.PerfilExpedienteAdapter;
import com.emergentes.medicapp.clases.Cita;

import java.util.ArrayList;
import java.util.List;

public class PerfilExpedienteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Cita> citas;
    PerfilExpedienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_expediente);
        recyclerView = findViewById(R.id.recyclerViewHistorial);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PerfilExpedienteAdapter(this,getCitas());
        recyclerView.setAdapter(adapter);
    }

    private List<Cita> getCitas() {
        citas = new ArrayList<Cita>();
        Cita cita = new Cita(1,1,1, "01/01/2019","10:00",-7.99,0.199,"Se va a morir","diarrea",900,'c','c');
        citas.add(cita);
        Cita cita1 = new Cita(3,2,5, "02/01/2019","16:30",-7.99,0.199,"Se va a morir","fiebre",900,'c','c');
        Cita cita2 = new Cita(2,2,10, "07/01/2019","16:30",-7.99,0.199,"Se va a morir","fiebre",900,'c','c');
        citas.add(cita1);
        citas.add(cita2);
        citas.add(cita1);
        citas.add(cita);
        citas.add(cita1);
        citas.add(cita);
        citas.add(cita1);
        return citas;
    }
}
