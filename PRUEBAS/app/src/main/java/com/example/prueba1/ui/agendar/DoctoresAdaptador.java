package com.example.prueba1.ui.agendar;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import com.example.prueba1.R;
import com.example.prueba1.ui.hcitas.ItemHcitas;

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
        holder.direccionM.setText(calculardireccion(datosDoctor.get(position).getLatitud(),datosDoctor.get(position).getLongitud()));
        holder.procedenciaM.setText(datosDoctor.get(position).getProcedencia());
        holder.costoxc.setText("$"+datosDoctor.get(position).getCostoxconsulta());
        datosDoctor.get(position).setDireccion(calculardireccion(datosDoctor.get(position).getLatitud(),datosDoctor.get(position).getLongitud()));
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
        TextView procedenciaM,costoxc;


        public ViewHolder(View item){

            super(item);
            nombreM = (TextView)item.findViewById(R.id.nombreM);
            especialidadM = (TextView)item.findViewById(R.id.EspecialidadM);
            telefonoM = (TextView)item.findViewById(R.id.telefonoM);
            direccionM = (TextView)item.findViewById(R.id.direcionM);
            procedenciaM = (TextView)item.findViewById(R.id.procedenciaM);
            costoxc=(TextView)item.findViewById(R.id.costoxc);
        }
    }

    private void bind(ItemDoctor item, final View view) {
        final ItemDoctor nuevo =item;
        Button button = (Button) view.findViewById(R.id.cita);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(context,agenda.class);
                intent.putExtra("id_medico", nuevo.getId_medico());
                intent.putExtra("nombre", nuevo.getNombre());
                intent.putExtra("especialidad", nuevo.getEspecialidad());
                intent.putExtra("telefono", nuevo.getTelefono());
                intent.putExtra("direccion",nuevo.getDireccion());
                intent.putExtra("procedencia",nuevo.getProcedencia());
                intent.putExtra("costo",nuevo.getCostoxconsulta());
                context.startActivity(intent);
                //Toast.makeText(mContext,"selec"+nom, Toast.LENGTH_LONG).show();
            }
        });

    }


    public String calculardireccion(String la,String lo) {
        try {
            double la2=Double.parseDouble(la),lo2=Double.parseDouble(lo);
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> list = geocoder.getFromLocation(
                    la2, lo2, 1);
            if (!list.isEmpty()) {
                Address DirCalle = list.get(0);
                return DirCalle.getAddressLine(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
