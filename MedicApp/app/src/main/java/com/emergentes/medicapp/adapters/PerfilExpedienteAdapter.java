package com.emergentes.medicapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.emergentes.medicapp.MedicamentoActivity;
import com.emergentes.medicapp.R;
import com.emergentes.medicapp.clases.Cita;
import com.emergentes.medicapp.ui.ActivityCitasAnteriores;

import java.util.List;


public class PerfilExpedienteAdapter extends  RecyclerView.Adapter<PerfilExpedienteAdapter.ViewHolder>{
    private Context context;
    private List<Cita> citas;

    public PerfilExpedienteAdapter(Context context, List<Cita> citas) {
        this.context = context;
        this.citas = citas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_historial,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        holder.fecha.setText(citas.get(position).getFecha());//fecha
        holder.doctor.setText(citas.get(position).getDoctor());//doctor
        holder.cedula.setText(citas.get(position).getCedula_doc());//doctor
        holder.sintomas.setText(citas.get(position).getSintomas());
        holder.diagnostico.setText(citas.get(position).getDiagnostico());//doctor

        holder.botonMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MedicamentoActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id_cita",citas.get(position).getIdcita()+"");
                view.getContext().startActivity(i);
                Toast.makeText(view.getContext(),"Medicamentos del paciente",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return citas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctor;
        TextView fecha;
        TextView cedula;
        TextView sintomas;
        TextView diagnostico;
        Button botonMedicamento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sintomas = itemView.findViewById(R.id.sintomas);
            diagnostico = itemView.findViewById(R.id.diagnostico);
            fecha = itemView.findViewById(R.id.fecha_cita);
            doctor = itemView.findViewById(R.id.doctor);
            cedula = itemView.findViewById(R.id.cedula);
            botonMedicamento = itemView.findViewById(R.id.botonMedicamentos);
        }
    }
}
