package com.emergentes.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emergentes.medicapp.adapters.ObservacionAdapter;
import com.emergentes.medicapp.clases.Observacion;
import com.emergentes.medicapp.ui.MedicamentosCrud;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticarActivity extends AppCompatActivity {
<<<<<<< HEAD
    RecyclerView recyclerView;
    List<Observacion> observaciones;
    ObservacionAdapter adapter;
    JSONObject dat;
=======
>>>>>>> c4d76166c2f7d8b5fe0074969f2c24e38d607590
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosticar);
<<<<<<< HEAD



        try {
            dat = new JSONObject(getIntent().getStringExtra("all"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        observaciones = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_add_obs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ObservacionAdapter(getApplication(),getObservaciones(),2);
        recyclerView.setAdapter(adapter);
=======
>>>>>>> c4d76166c2f7d8b5fe0074969f2c24e38d607590

        Button add_obs = findViewById(R.id.btnObserv);
        add_obs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ObservacionesActivity.class);
                startActivity(i);
            }
        });

        Button tratamiento = findViewById(R.id.agregar);
        tratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MedicamentosCrud.class);
                startActivity(i);
            }
        });
    }

}
