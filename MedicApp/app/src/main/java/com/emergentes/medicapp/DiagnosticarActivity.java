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

import java.util.ArrayList;
import java.util.List;

public class DiagnosticarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosticar);

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
