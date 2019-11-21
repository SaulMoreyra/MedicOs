package com.example.prueba1.ui.historial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba1.R;

import java.util.List;

public class HistorialAdaptador extends RecyclerView.Adapter<HistorialAdaptador.ViewHolder> {

    Context context;
    List <ItemHistorial> datosHistorial;

    public HistorialAdaptador(Context context, List<ItemHistorial> datosHistorial){
        this.context = context;
        this.datosHistorial = datosHistorial;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial,parent,false);
        ViewHolder viewHolder = new ViewHolder(vista);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreAntecedente.setText(datosHistorial.get(position).getNombreAntecedente());
        holder.descAntecedente.setText(datosHistorial.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return datosHistorial.size()    ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreAntecedente;
        TextView descAntecedente;

        public ViewHolder(View item){

            super(item);
            nombreAntecedente = (TextView)item.findViewById(R.id.nombreAntecedente);
            descAntecedente = (TextView)item.findViewById(R.id.descAntecedente);
        }
    }
}
