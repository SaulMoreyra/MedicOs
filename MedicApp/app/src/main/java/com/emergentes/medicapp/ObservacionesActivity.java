package com.emergentes.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.emergentes.medicapp.adapters.ObservacionAdapter;
import com.emergentes.medicapp.clases.Observacion;
import com.emergentes.medicapp.ui.AddObservationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ObservacionesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Observacion> observaciones;
    ObservacionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observaciones);
        observaciones = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_observaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ObservacionAdapter(getApplication(),getObservaciones());//,2
        recyclerView.setAdapter(adapter);
        FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddObservationActivity.class);
                startActivity(i);
            }
        });
    }

    private List<Observacion> getObservaciones() {
        Observacion o = new Observacion(1,1,"Alergia","Abejas","1998-10-10");
        Observacion o1 = new Observacion(1,1,"Alergia","Metoclopramida","1998-05-10");
        observaciones.add(o);
        observaciones.add(o1);
        return observaciones;
    }
}
