package com.emergentes.medicapp.ui.citapendiente;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emergentes.medicapp.R;
import com.emergentes.medicapp.adapters.CitaPendienteAdapter;
import com.emergentes.medicapp.clases.Cita;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CitaPendienteFragment extends Fragment {

    private CitaPendienteViewModel slideshowViewModel;
    RecyclerView recyclerView;
    List<Cita> citas;
    CitaPendienteAdapter adapter;

    int idcita, idproducto;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(CitaPendienteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cita_pendiente, container, false);
        //final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText("aqui estoy");
            }
        });
        recyclerView = root.findViewById(R.id.recyclerViewCitaPendiente);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CitaPendienteAdapter(getContext(),getCitas());
        recyclerView.setAdapter(adapter);
        return root;
    }

    private List<Cita> getCitas(){
        citas = new ArrayList<Cita>();
        Cita cita = new Cita(1,1,1, "01/01/2019","10:00",-7.99,0.199,"Se va a morir","diarrea",900,'c','c');
        citas.add(cita);
        Cita cita1 = new Cita(1,2,1, "02/01/2019","16:30",-7.99,0.199,"Se va a morir","fiebre",900,'c','c');
        citas.add(cita1);
        return citas;
    }


}