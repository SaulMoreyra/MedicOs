package com.emergentes.medicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.emergentes.medicapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedicamentoCrudAdapter extends RecyclerView.Adapter<MedicamentoCrudAdapter.ViewHolder> {

    private Context context;
    private List<Medicamento> medicamentos;

    public MedicamentoCrudAdapter(Context context, List<Medicamento> medicamentos) {
        this.context = context;
        this.medicamentos = medicamentos;
    }

    @NonNull
    @Override
    public MedicamentoCrudAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicamento_crud,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentoCrudAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return medicamentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView medicamento;
        TextView dosis;
        TextView horario;
        TextView descripcion;
        Button editar;
        Button eliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicamento =
        }
    }
}
