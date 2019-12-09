package com.emergentes.medicapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.emergentes.medicapp.PerfilExpedienteActivity;
import com.emergentes.medicapp.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.emergentes.medicapp.clases.Cita;

import java.util.List;

public class CitaPendienteAdapter extends RecyclerView.Adapter<CitaPendienteAdapter.ViewHolder> {
    private Context context;
    private List<Cita> citas;
    private int tag;

    public CitaPendienteAdapter(Context context, List<Cita> citas,int tag) {
        this.context = context;
        this.citas = citas;
        this.tag=tag;
    }

    @NonNull
    @Override
    public CitaPendienteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cita_pendiente,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitaPendienteAdapter.ViewHolder holder, final int position) {
        holder.nombre.setText(citas.get(position).getNombre());//nombre paciente
        holder.fecha.setText(citas.get(position).getFecha());//direccion
        holder.hora_cita.setText(citas.get(position).getHora());

        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PerfilExpedienteActivity.class);
                i.putExtra("tag", tag);
                i.putExtra("all",citas.get(position).getAll());
                view.getContext().startActivity(i);
                Toast.makeText(view.getContext(),"Citas del paciente",Toast.LENGTH_SHORT).show();
            }
        });
        if(tag==2){
            holder.ir.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return citas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView perfil;
        TextView nombre;
        TextView fecha;
        TextView hora_cita;
        Button ver;
        Button ir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            perfil = itemView.findViewById(R.id.perfil);
            nombre = itemView.findViewById(R.id.nombre_paciente);
            fecha = itemView.findViewById(R.id.direccion_paciente);
            hora_cita = itemView.findViewById(R.id.hora_cita);
            ver = itemView.findViewById(R.id.flecha);
            ir = itemView.findViewById(R.id.mapa);
        }
    }
}
