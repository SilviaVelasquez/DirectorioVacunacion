package com.example.dell.directorioturismo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.directorioturismo.models.Vacunacion;

import java.util.ArrayList;

public class AdaptadorVacunacion extends RecyclerView.Adapter<AdaptadorVacunacion.ViewHolder> {
    private ArrayList<Vacunacion> dataset;

    private Context context;

    public AdaptadorVacunacion(Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adaptador_vacunacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vacunacion a=dataset.get(position);
        holder.Correo.setText(a.getAO().toString());
        holder.Direccion.setText(a.getMes().toString());
        holder.Nombre.setText(a.getMunicipio().toString());
        holder.Telefono.setText(a.getValor().toString());



    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }

    public void agregarTurismo(ArrayList<Vacunacion> list) {
        dataset.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        private TextView Correo;
        private TextView Direccion;
        private TextView Nombre;
        private TextView Telefono;



        public ViewHolder(View itemView) {
            super(itemView);


            Correo=(TextView)itemView.findViewById(R.id.txt1);
            Direccion=(TextView)itemView.findViewById(R.id.txt2);
            Nombre=(TextView)itemView.findViewById(R.id.txt3);
            Telefono=(TextView)itemView.findViewById(R.id.txt4);

        }
    }
}

