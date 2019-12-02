package com.example.prueba1.ui.hcitas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prueba1.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class HmedicamentosAdaptador  extends RecyclerView.Adapter<HmedicamentosAdaptador.ViewHolder> {
    View vista;
    Context context;
    List<ItemMedicamento> medicamentoslist;
    public HmedicamentosAdaptador(Context context, List<ItemMedicamento> medicamentoslist) {
        this.context = context;
        this.medicamentoslist = medicamentoslist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicamento,parent,false);
        ViewHolder viewHolder = new ViewHolder(vista);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HmedicamentosAdaptador.ViewHolder holder, int position) {
        holder.medicamento.setText(medicamentoslist.get(position).getMedicamento());
        holder.dosis.setText(medicamentoslist.get(position).getDosis());
        holder.horario.setText(medicamentoslist.get(position).getHorario_aplicacion());
        holder.descripcion.setText(medicamentoslist.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return medicamentoslist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView medicamento;
        TextView dosis;
        TextView horario;
        TextView descripcion;

        public ViewHolder(View item){
            super(item);
            medicamento = (TextView)item.findViewById(R.id.medicamento);
            dosis = (TextView)item.findViewById(R.id.dosis);
            horario = (TextView)item.findViewById(R.id.horario);
            descripcion = (TextView)item.findViewById(R.id.descripcion);

        }
    }
}
