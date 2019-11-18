package com.emergentes.medicapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.emergentes.medicapp.PerfilExpedienteActivity;
import com.emergentes.medicapp.R;
import com.emergentes.medicapp.clases.Cita;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CitaPendienteAdapter extends RecyclerView.Adapter<CitaPendienteAdapter.ViewHolder> {
    private Context context;
    private List<Cita> citas;

    public CitaPendienteAdapter(Context context, List<Cita> citas) {
        this.context = context;
        this.citas = citas;
    }

    @NonNull
    @Override
    public CitaPendienteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cita_pendiente,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitaPendienteAdapter.ViewHolder holder, int position) {
        holder.nombre.setText(""+citas.get(position).getIdpaciente());//nombre paciente
        holder.direccion.setText(""+citas.get(position).getIdpaciente());//direccion
        holder.hora_cita.setText(""+citas.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return citas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView perfil;
        TextView nombre;
        TextView direccion;
        TextView hora_cita;
        ImageView flecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            perfil = itemView.findViewById(R.id.perfil);
            nombre = itemView.findViewById(R.id.nombre_paciente);
            direccion = itemView.findViewById(R.id.direccion_paciente);
            hora_cita = itemView.findViewById(R.id.hora_cita);
            flecha = itemView.findViewById(R.id.flecha);
            flecha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, PerfilExpedienteActivity.class);
                    view.getContext().startActivity(i);
                    Toast.makeText(view.getContext(),"Citas del paciente",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
