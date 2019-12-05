package com.emergentes.medicapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.emergentes.medicapp.R;
import com.emergentes.medicapp.adapters.MedicamentoCrudAdapter;
import com.emergentes.medicapp.clases.Medicamento;

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
    }

    public List<Medicamento> getMedicamentos(){
        List<Medicamento> medicamentos = new ArrayList<>();
        Medicamento m = new Medicamento(1,"Paracetamol","1 pastilla","Cada 8 horas","Despues de cada comida");
        medicamentos.add(m);
        medicamentos.add(m);
        return medicamentos;
    }
}
