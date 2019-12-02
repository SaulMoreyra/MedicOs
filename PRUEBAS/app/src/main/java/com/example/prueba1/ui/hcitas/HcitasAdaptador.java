package com.example.prueba1.ui.hcitas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba1.R;

import java.util.List;

public class HcitasAdaptador extends RecyclerView.Adapter<HcitasAdaptador.ViewHolder> {
    View vista;
    Context context;
    List<ItemHcitas> datosHcitas;

    public HcitasAdaptador(Context context, List<ItemHcitas> datosHcitas) {
        this.context = context;
        this.datosHcitas = datosHcitas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hcitas2,parent,false);
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
        bind(datosHcitas.get(position), vista);
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

    private void bind( ItemHcitas item, final View view) {
        final ItemHcitas nuevo =item;
        Button button = (Button) view.findViewById(R.id.detalle);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(context,CitaView.class);
                intent.putExtra("id_cita", nuevo.getId_cita());
                intent.putExtra("nombre", nuevo.getNom());
                intent.putExtra("esp", nuevo.getEsp());
                intent.putExtra("tel", nuevo.getTel());
                intent.putExtra("fecha",nuevo.getFecha());
                intent.putExtra("hora",nuevo.getHora());
                intent.putExtra("costo",nuevo.getCosto());
                intent.putExtra("tipo",nuevo.getTipo_cita());
                intent.putExtra("diagnostico",nuevo.getDiagnostico());
                intent.putExtra("sintomas",nuevo.getSintomas());
                System.out.println("consultar"+nuevo.getId_cita());
                context.startActivity(intent);
                //Toast.makeText(mContext,"selec"+nom, Toast.LENGTH_LONG).show();
            }
        });

    }
}
