package com.emergentes.medicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emergentes.medicapp.R;
import com.emergentes.medicapp.clases.Medicamento;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedicamentoAdapter extends RecyclerView.Adapter<MedicamentoAdapter.ViewHolder>{
    private Context context;
    private List<Medicamento> medicamentos;

    public MedicamentoAdapter(Context context, List<Medicamento> medicamentos) {
        this.context = context;
        this.medicamentos = medicamentos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicamento,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.medicamento.setText((medicamentos.get(position).getMedicamento()));
        holder.indicaciones.setText(medicamentos.get(position).getDosis()+" "+medicamentos.get(position).getHora_aplicacion());
        holder.descrip.setText(medicamentos.get(position).getDescrpcion());

    }

    @Override
    public int getItemCount() {
        return medicamentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView medicamento;
        TextView indicaciones;
        TextView descrip;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicamento= itemView.findViewById(R.id.medicamento);
            indicaciones= itemView.findViewById(R.id.indicaciones);
            descrip = itemView.findViewById(R.id.descripcion);
        }
    }
}
