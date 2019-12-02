package com.example.prueba1.ui.agendar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import com.example.prueba1.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoctoresAdaptador extends RecyclerView.Adapter<DoctoresAdaptador.ViewHolder> {


    Context context;
    List<ItemDoctor> datosDoctor;
    View vista;
    public DoctoresAdaptador(Context context, List<ItemDoctor> datosHistorial){
        this.context = context;
        this.datosDoctor = datosHistorial;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctores,parent,false);
        ViewHolder viewHolder = new ViewHolder(vista);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreM.setText(datosDoctor.get(position).getNombre());
        holder.especialidadM.setText(datosDoctor.get(position).getEspecialidad());
        holder.telefonoM.setText(datosDoctor.get(position).getTelefono());
        holder.direccionM.setText(datosDoctor.get(position).getDireccion());
        holder.procedenciaM.setText(datosDoctor.get(position).getProcedencia());
        bind(datosDoctor.get(position), vista);
    }

    @Override
    public int getItemCount() {
        return datosDoctor.size()    ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreM;
        TextView especialidadM;
        TextView telefonoM;
        TextView direccionM;
        TextView procedenciaM;

        public ViewHolder(View item){

            super(item);
            nombreM = (TextView)item.findViewById(R.id.nombreM);
            especialidadM = (TextView)item.findViewById(R.id.EspecialidadM);
            telefonoM = (TextView)item.findViewById(R.id.telefonoM);
            direccionM = (TextView)item.findViewById(R.id.direcionM);
            procedenciaM = (TextView)item.findViewById(R.id.procedenciaM);
        }
    }

    private void bind(final ItemDoctor item, final View view) {

        Button button = (Button) view.findViewById(R.id.cita);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(context,agenda.class);
                context.startActivity(intent);
                //Toast.makeText(mContext,"selec"+nom, Toast.LENGTH_LONG).show();
            }
        });

    }

}
