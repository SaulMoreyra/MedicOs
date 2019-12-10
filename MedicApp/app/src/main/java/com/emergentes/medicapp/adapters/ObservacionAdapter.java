package com.emergentes.medicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.emergentes.medicapp.R;
import com.emergentes.medicapp.clases.Observacion;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ObservacionAdapter extends RecyclerView.Adapter<ObservacionAdapter.ViewHolder> {
    private Context context;
    private List<Observacion> observacion;
    //private int tag;
    public ObservacionAdapter(Context context, List<Observacion> observacion) {
        this.context = context;
        this.observacion = observacion;
        //this.tag =tag;
    }

    @NonNull
    @Override
    public ObservacionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        //if(tag==1)
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_observaciones,null);
        /*else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_observaciones,null);
        System.out.println("TAG: "+tag);*/
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObservacionAdapter.ViewHolder holder, int position) {
        //if(tag==1) {
            holder.tipoT.setText(observacion.get(position).getTipo());
            holder.descripcionT.setText(observacion.get(position).getDescripcion());
            holder.antiguedadT.setText("" + observacion.get(position).getAntiguedad());
        /*}
        else{
            holder.tipoE.setText(observacion.get(position).getTipo());
            holder.descripcionE.setText(observacion.get(position).getDescripcion());
            holder.antiguedadE.setText("" + observacion.get(position).getAntiguedad());
        }*/
    }

    @Override
    public int getItemCount() {
        return observacion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tipoT;
        TextView descripcionT;
        TextView antiguedadT;
        /*TextView tipoE;
        TextView descripcionE;
        TextView antiguedadE;*/

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //if(tag==1) {
                tipoT = itemView.findViewById(R.id.tipo_obs);
                descripcionT = itemView.findViewById(R.id.descripcion_obs);
                antiguedadT = itemView.findViewById(R.id.antiguedad_obs);
            /*}
            else{
                tipoE = itemView.findViewById(R.id.tipo_obs);
                descripcionE = itemView.findViewById(R.id.descripcion_obs);
                antiguedadE = itemView.findViewById(R.id.antiguedad_obs);
            }*/
        }
    }
}
