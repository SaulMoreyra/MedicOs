package com.emergentes.medicapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.emergentes.medicapp.R;
import com.emergentes.medicapp.adapters.PerfilExpedienteAdapter;
import com.emergentes.medicapp.clases.Cita;

import java.util.ArrayList;
import java.util.List;

public class ActivityCitasAnteriores extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Cita> citas;
    PerfilExpedienteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_anteriores);

        recyclerView = findViewById(R.id.recycler_citas_anteriores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PerfilExpedienteAdapter(getApplication(),getCitas());
        recyclerView.setAdapter(adapter);
    }
    private List<Cita> getCitas() {

        /*
        TextView doctor;
        TextView fecha;
        TextView cedula;
        TextView diagnostico;
        */

        citas = new ArrayList<>();

        Cita cita = new Cita()
                .setDoctor("Ignacio Luis Luz Deisy")
                .setFecha("07/01/2019")
                .setCedula_doc("8474677FJFJFD4")
                .setDiagnostico("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");


        Cita cita1 = new Cita()
                .setDoctor("José Rios José")
                .setFecha("07/01/2019")
                .setCedula_doc("8474677FJFJFD4")

                .setDiagnostico("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");

        Cita cita2 = new Cita()
                .setDoctor("Aragón Moreyra Saúl Renato")
                .setFecha("07/01/2019")
                .setCedula_doc("8474677FJFJFD4")
                .setDiagnostico("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");


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
