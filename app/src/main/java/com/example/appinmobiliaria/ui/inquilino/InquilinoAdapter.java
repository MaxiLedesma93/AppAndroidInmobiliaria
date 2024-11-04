package com.example.appinmobiliaria.ui.inquilino;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.models.Contrato;
import com.example.appinmobiliaria.models.Inmueble;
import com.example.appinmobiliaria.models.Inquilino;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder> {
    private List<Contrato> listaContratos;
    private View view;
    private LayoutInflater layoutInflater;
    private Context context;

    public InquilinoAdapter(List<Contrato> listaContratos, View view, LayoutInflater layoutInflater) {
        this.listaContratos = listaContratos;
        this.view = view;
        this.layoutInflater = layoutInflater;
        this.context = view.getContext();
    }
    @NonNull
    @Override
    public InquilinoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_inquilino, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull InquilinoAdapter.ViewHolder holder, int position) {
        Contrato c = listaContratos.get(position);
        Inmueble inmu = c.getInmueble();
        Inquilino inq = c.getInquilino();
        holder.tvDirec.setText(inmu.getDireccion());
        Glide.with(context)
                .load("http://192.168.0.104:5001/"+inmu.getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivfotoInq);

        holder.btInqDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inquilino", inq);
                Navigation.findNavController(view).navigate(R.id.inmuebleDetalleFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaContratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivfotoInq;
        TextView tvDirec;
        Button btInqDetalle;

        public ViewHolder(View itemview){
            super(itemview);
            ivfotoInq = itemview.findViewById(R.id.ivfotoInq);
            tvDirec = itemview.findViewById(R.id.tvInqDirec);
            btInqDetalle = itemview.findViewById(R.id.btnInqDetalle);
        }
    }


}
