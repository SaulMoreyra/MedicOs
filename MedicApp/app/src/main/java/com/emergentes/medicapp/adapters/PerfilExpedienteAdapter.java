package com.emergentes.medicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emergentes.medicapp.R;
import com.emergentes.medicapp.clases.Cita;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

public class PerfilExpedienteAdapter extends  RecyclerView.Adapter<PerfilExpedienteAdapter.ViewHolder>{
    private Context context;
    private List<Cita> citas;

    public PerfilExpedienteAdapter(Context context, List<Cita> citas) {
        this.context = context;
        this.citas = citas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_historial,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fecha.setText(citas.get(position).getFecha());//fecha
        holder.doctor.setText(citas.get(position).getDoctor());//doctor
        holder.cedula.setText(citas.get(position).getCedula_doc());//doctor
        holder.sintomas.setText(citas.get(position).getSintomas());
        holder.diagnostico.setText(citas.get(position).getDiagnostico());//doctor
    }

    @Override
    public int getItemCount() {
        return citas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctor;
        TextView fecha;
        TextView cedula;
        TextView sintomas;
        TextView diagnostico;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sintomas = itemView.findViewById(R.id.sintomas);
            diagnostico = itemView.findViewById(R.id.diagnostico);
            fecha = itemView.findViewById(R.id.fecha_cita);
            doctor = itemView.findViewById(R.id.doctor);
            cedula = itemView.findViewById(R.id.cedula);
        }
    }
}
