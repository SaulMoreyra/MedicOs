package com.emergentes.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.emergentes.medicapp.adapters.MedicamentoAdapter;
import com.emergentes.medicapp.clases.Medicamento;

import java.util.ArrayList;
import java.util.List;

public class MedicamentoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Medicamento> medicamentos;
    MedicamentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);
        medicamentos = new ArrayList<>();
        getDataJSON();
        recyclerView = findViewById(R.id.recyclerViewMedicamento);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicamentoAdapter(this,medicamentos);
        recyclerView.setAdapter(adapter);
    }
    String URL = "";
    private void getDataJSON() {
        medicamentos.clear();
        medicamentos.add(new Medicamento(1,"Paracetamol","1 tableta","cada 8 horas","por 5 días"));
        medicamentos.add(new Medicamento(1,"Diclofenaco","1 tableta","cada 8 horas","por 7 días"));
    }
}
