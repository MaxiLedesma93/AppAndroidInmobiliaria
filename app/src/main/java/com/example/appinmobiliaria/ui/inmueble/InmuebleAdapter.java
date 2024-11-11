package com.example.appinmobiliaria.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.models.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter <InmuebleAdapter.ViewHolder> {
    private List<Inmueble> lista;
    private View root;
    private LayoutInflater inflater;

    public InmuebleAdapter(List<Inmueble> lista, View root, LayoutInflater inflater) {
        this.lista = lista;
        this.root= root;
    //    this.inflater = inflater;
        this.inflater = LayoutInflater.from(root.getContext());
    }

    @NonNull
    @Override
    //Referenciar a la vista item y pasarsela al viewholder
    public InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.ViewHolder holder, int position) {
        Inmueble i = lista.get(position);

        holder.tvDireccion.setText(lista.get(position).getDireccion());
        holder.tvImporte.setText("$ " + lista.get(position).getImporte()+"");
        Glide.with(root.getContext())
                .load("http://192.168.0.3:5000/" + lista.get(position).getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivFotoInmu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", i);
                Navigation.findNavController(root).navigate(R.id.inmuebleDetalleFragment, bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFotoInmu;
        private TextView tvDireccion, tvImporte;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFotoInmu = itemView.findViewById(R.id.ivFotoInmu);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvImporte = itemView.findViewById(R.id.tvImporte);
        }
    }
}
