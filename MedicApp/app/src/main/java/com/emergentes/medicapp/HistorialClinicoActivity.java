package com.emergentes.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.emergentes.medicapp.adapters.ObservacionAdapter;
import com.emergentes.medicapp.clases.Observacion;

import java.util.ArrayList;
import java.util.List;

public class HistorialClinicoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Observacion> observaciones;
    ObservacionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observaciones = new ArrayList<>();
        setContentView(R.layout.activity_historial_clinico);
        recyclerView = findViewById(R.id.recycler_observaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ObservacionAdapter(getApplication(),getObservaciones(),1);
        recyclerView.setAdapter(adapter);
    }

    private List<Observacion> getObservaciones() {
        Observacion o = new Observacion(1,1,"Alergia","Abejas","1998-10-10");
        Observacion o1 = new Observacion(1,1,"Alergia","Metoclopramida","1998-05-10");
        observaciones.add(o);
        observaciones.add(o1);
        return observaciones;
    }
}
