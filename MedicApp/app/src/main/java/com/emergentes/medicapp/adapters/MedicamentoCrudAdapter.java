package com.emergentes.medicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.emergentes.medicapp.R;
import com.emergentes.medicapp.clases.Medicamento;

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
        holder.medicamento.setText(medicamentos.get(position).getMedicamento());
        holder.dosis.setText(medicamentos.get(position).getDosis());
        holder.horario.setText(medicamentos.get(position).getHora_aplicacion());
        holder.descripcion.setText(medicamentos.get(position).getDescrpcion());
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
            medicamento = itemView.findViewById(R.id.medicamento);
            dosis = itemView.findViewById(R.id.dosis);
            horario = itemView.findViewById(R.id.horario);
            descripcion = itemView.findViewById(R.id.descripcion);
            editar = itemView.findViewById(R.id.editar);
            eliminar = itemView.findViewById(R.id.eliminar);
        }
    }
}
