package com.example.prueba1.ui.historial;

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

public class HistorialAdaptador extends RecyclerView.Adapter<HistorialAdaptador.ViewHolder> {
    View vista;
    Context context;
    List <ItemHistorial> datosHistorial;

    public HistorialAdaptador(Context context, List<ItemHistorial> datosHistorial){
        this.context = context;
        this.datosHistorial = datosHistorial;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial,parent,false);
        ViewHolder viewHolder = new ViewHolder(vista);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreAntecedente.setText(datosHistorial.get(position).getNombreAntecedente());
        holder.descAntecedente.setText(datosHistorial.get(position).getDescripcion());
        bind(datosHistorial.get(position), vista);
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

    private void bind( ItemHistorial item, final View view) {
         final ItemHistorial nuevo =item;
        Button button = (Button) view.findViewById(R.id.editar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(context,HistorialEdit.class);
                intent.putExtra("id_observacion", nuevo.getId_antecedente());
                intent.putExtra("tipo_obs", nuevo.getTipo());
                intent.putExtra("nombre_obs", nuevo.getNombreAntecedente());
                intent.putExtra("descripcion_obs",nuevo.getDescripcion());
                intent.putExtra("antiguedad",nuevo.getAntiguedad());
                System.out.println("EDITAR"+nuevo.getId_antecedente());
                context.startActivity(intent);
                //Toast.makeText(mContext,"selec"+nom, Toast.LENGTH_LONG).show();
            }
        });

    }
}
