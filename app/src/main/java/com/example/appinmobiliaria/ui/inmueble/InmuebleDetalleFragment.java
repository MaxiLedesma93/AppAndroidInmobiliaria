package com.example.appinmobiliaria.ui.inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.databinding.FragmentInmuebleDetalleBinding;
import com.example.appinmobiliaria.models.Inmueble;

public class InmuebleDetalleFragment extends Fragment {

    private InmuebleDetalleViewModel mViewModel;
    private FragmentInmuebleDetalleBinding binding;

    public static InmuebleDetalleFragment newInstance() {
        return new InmuebleDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);

        binding = FragmentInmuebleDetalleBinding.inflate(getLayoutInflater());

        View root = inflater.inflate(R.layout.fragment_inmueble_detalle, container, false);

        mViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                Glide.with(root.getContext())
                        .load("http://192.168.0.3:5000/"+inmueble.getImgUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivFotoDetInmu);
                //tvDetId.setText(inmueble.getIdInmueble()+"");
                binding.tvDetDir.setText("Dirección: "+inmueble.getDireccion());
                binding.tvDetUso.setText("Uso: "+inmueble.getUso());
                binding.tvDetAmb.setText("Ambientes: "+inmueble.getAmbientes()+"");
                binding.tvDetTipo.setText("Tipo: "+inmueble.getTipo().getDescripcion());
                binding.tvDetPrecio.setText(String.valueOf("Precio: $ "+inmueble.getImporte()));
                binding.cbEstado.setChecked(inmueble.isDisponible());
                binding.cbEstado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewModel.guardarEstado(inmueble.getId(), binding.cbEstado.isChecked());
                    }
                });

            }
        });
        mViewModel.setInmueble(getArguments());
        return binding.getRoot();
    }

}