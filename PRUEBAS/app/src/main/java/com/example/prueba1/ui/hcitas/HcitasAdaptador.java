package com.example.prueba1.ui.hcitas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba1.R;

import java.util.List;

public class HcitasAdaptador extends RecyclerView.Adapter<HcitasAdaptador.ViewHolder> {

    Context context;
    List<ItemHcitas> datosHcitas;

    public HcitasAdaptador(Context context, List<ItemHcitas> datosHcitas) {
        this.context = context;
        this.datosHcitas = datosHcitas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hcitas,parent,false);
        ViewHolder viewHolder = new ViewHolder(vista);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fecha.setText(datosHcitas.get(position).getFecha());
        holder.hora.setText(datosHcitas.get(position).getHora());
        holder.diagnostico.setText(datosHcitas.get(position).getDiagnostico());
        holder.sintomas.setText(datosHcitas.get(position).getSintomas());
        holder.costo.setText(datosHcitas.get(position).getCosto());
        holder.tipo_cita.setText(datosHcitas.get(position).getTipo_cita());
    }

    @Override
    public int getItemCount() {
        return datosHcitas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView fecha;
        TextView hora;
        TextView diagnostico;
        TextView sintomas;
        TextView costo;
        TextView tipo_cita;

        public ViewHolder(View item){

            super(item);
            fecha = (TextView)item.findViewById(R.id.fecha);
            hora = (TextView)item.findViewById(R.id.hora);
            diagnostico = (TextView)item.findViewById(R.id.diagnostico);
            sintomas = (TextView)item.findViewById(R.id.sintomas);
            costo = (TextView)item.findViewById(R.id.costo);
            tipo_cita = (TextView)item.findViewById(R.id.tipo_cita);

        }
    }
}
