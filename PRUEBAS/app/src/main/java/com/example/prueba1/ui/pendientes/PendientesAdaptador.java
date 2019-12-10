package com.example.prueba1.ui.pendientes;

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
import com.example.prueba1.ui.agendar.ItemDoctor;
import com.example.prueba1.ui.agendar.agenda;

import java.util.List;

public class PendientesAdaptador extends RecyclerView.Adapter<PendientesAdaptador.ViewHolder>{

    Context context;
    List<ItemPendientes> datosPendientes;
    View vista;
    public PendientesAdaptador(Context context, List<ItemPendientes> listPendientes) {
        this.context = context;
        this.datosPendientes = listPendientes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pendiente2,parent,false);
        ViewHolder viewHolder = new ViewHolder(vista);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fechaP.setText(datosPendientes.get(position).getFechaP());
        holder.horaP.setText(datosPendientes.get(position).getHoraP());
        holder.costoP.setText(datosPendientes.get(position).getCostoP());
        holder.tipo_citaP.setText(datosPendientes.get(position).getTipo_citaP());
        bind(datosPendientes.get(position), vista);
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

    private void bind(ItemPendientes item, final View view) {
        final ItemPendientes nuevo =item;
        Button button = (Button) view.findViewById(R.id.detalles);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(context, detallesPendientes.class);
                context.startActivity(intent);
                //Toast.makeText(mContext,"selec"+nom, Toast.LENGTH_LONG).show();
            }
        });

    }

}
