package com.emergentes.medicapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.emergentes.medicapp.R;
import com.emergentes.medicapp.adapters.MedicamentoCrudAdapter;
import com.emergentes.medicapp.clases.Medicamento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosCrud extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MedicamentoCrudAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos_crud);

        recyclerView = findViewById(R.id.recycler_medicamentos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicamentoCrudAdapter(getApplication(),getMedicamentos());
        recyclerView.setAdapter(adapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MedicamentoNew.class);
                startActivity(i);
            }
        });
    }

    public List<Medicamento> getMedicamentos(){
        List<Medicamento> medicamentos = new ArrayList<>();
        Medicamento m = new Medicamento(1,1,"Paracetamol","1 pastilla","Cada 8 horas","Despues de cada comida");
        medicamentos.add(m);
        medicamentos.add(m);
        return medicamentos;
    }
}
