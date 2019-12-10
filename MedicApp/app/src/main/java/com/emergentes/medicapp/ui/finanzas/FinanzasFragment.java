package com.emergentes.medicapp.ui.finanzas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.emergentes.medicapp.R;


public class FinanzasFragment extends Fragment {

    private FinanzasViewModel finanzasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        finanzasViewModel =
                ViewModelProviders.of(this).get(FinanzasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_finzanzas, container, false);
        final TextView text = root.findViewById(R.id.mes);
        final Spinner spinner = (Spinner)root.findViewById(R.id.select_mes);
        final TextView total_consultas = root.findViewById(R.id.total_consultas);
        final TextView total_ingreso = root.findViewById(R.id.total_ingreso);
        final TextView quno = root.findViewById(R.id.quno);
        final TextView qdos = root.findViewById(R.id.qdos);
        final TextView tuno = root.findViewById(R.id.tuno);
        final TextView tdos = root.findViewById(R.id.tdos);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                text.setText("Finanzas de "+item.toString());
                System.out.println("numero"+quno.getText());
                String aux1 = tuno.getText().toString().substring(2);
                String aux2 = tdos.getText().toString().substring(2);
                total_consultas.setText(""+(Integer.parseInt(quno.getText().toString())+Integer.parseInt(qdos.getText().toString())));
                total_ingreso.setText("$ "+(Double.parseDouble(aux1)+Double.parseDouble(aux2)));
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        return root;
    }
}