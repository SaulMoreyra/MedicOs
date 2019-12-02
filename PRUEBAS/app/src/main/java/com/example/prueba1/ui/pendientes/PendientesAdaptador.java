package com.example.prueba1.ui.pendientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba1.R;

import java.util.List;

public class PendientesAdaptador extends RecyclerView.Adapter<PendientesAdaptador.ViewHolder>{

    Context context;
    List<ItemPendientes> datosPendientes;

    public PendientesAdaptador(Context context, List<ItemPendientes> listPendientes) {
        this.context = context;
        this.datosPendientes = listPendientes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pendiente2,parent,false);
        ViewHolder viewHolder = new ViewHolder(vista);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fechaP.setText(datosPendientes.get(position).getFechaP());
        holder.horaP.setText(datosPendientes.get(position).getHoraP());
        holder.costoP.setText(datosPendientes.get(position).getCostoP());
        holder.tipo_citaP.setText(datosPendientes.get(position).getTipo_citaP());
    }

    @Override
    public int getItemCount() {
        return datosPendientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView fechaP;
        TextView horaP;
        TextView costoP;
        TextView tipo_citaP;

        public ViewHolder(View item){

            super(item);
            fechaP = (TextView)item.findViewById(R.id.fechaP);
            horaP = (TextView)item.findViewById(R.id.horaP);
            costoP = (TextView)item.findViewById(R.id.costoP);
            tipo_citaP = (TextView)item.findViewById(R.id.tipo_citaP);
        }

    }

}
