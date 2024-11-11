package com.example.appinmobiliaria.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.databinding.FragmentPerfilBinding;
import com.example.appinmobiliaria.models.Propietario;
import com.example.appinmobiliaria.ui.contrato.PagoAdapter;

public class PerfilFragment extends Fragment {
    private PerfilViewModel pvm;

    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,@Nullable Bundle savedInstanceState) {
         pvm =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        pvm.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
             //   binding.etId.setText(propietario.getId()+"");
                binding.etNombre.setText(propietario.getNombre());
                binding.etApellido.setText(propietario.getApellido());
                binding.etDni.setText(propietario.getDni());
                binding.etMail.setText(propietario.getEmail());
                binding.etTel.setText(propietario.getTelefono());
                Glide.with(getContext())
                        .load("http://192.168.0.3:5000/"+propietario.getAvatarUrl())
                        .placeholder(R.drawable.ic_perfilvacio)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivProp);
            }
        });
        binding.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etNombre.setEnabled(true);
                binding.etApellido.setEnabled(true);
                binding.etDni.setEnabled(true);
                binding.etTel.setEnabled(true);
                binding.btnEditar.setVisibility(View.GONE);
                binding.btnGuardar.setVisibility(View.VISIBLE);
            }
        });

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Propietario p = new Propietario();
              //  p.setId(Integer.parseInt(binding.etId.getText().toString()+""));
                p.setDni(binding.etDni.getText().toString());
                p.setNombre(binding.etNombre.getText().toString());
                p.setApellido(binding.etApellido.getText().toString());
                p.setEmail(binding.etMail.getText().toString());
                p.setTelefono(binding.etTel.getText().toString());
                binding.etNombre.setEnabled(false);
                binding.etApellido.setEnabled(false);
                binding.etDni.setEnabled(false);
                binding.etTel.setEnabled(false);
                binding.btnEditar.setVisibility(View.VISIBLE);
                binding.btnGuardar.setVisibility(View.GONE);
                pvm.guardarPropietario(p);
            }
        });
        binding.btnCClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etClaveActual.setVisibility(View.VISIBLE);
                binding.etContraseA.setVisibility(View.VISIBLE);
                binding.etRepetirContraseA.setVisibility(View.VISIBLE);
                binding.btnGuardarClave.setVisibility(View.VISIBLE);
                binding.btnCClave.setVisibility(View.GONE);
                binding.btnGuardar.setVisibility(View.GONE);
            }
        });
        binding.btnGuardarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String claveActual = binding.etClaveActual.getText().toString();
                String contra = binding.etContraseA.getText().toString();
                String rep = binding.etRepetirContraseA.getText().toString();
                pvm.cambiarPass(contra, rep, claveActual);
                binding.etClaveActual.setVisibility(View.GONE);
                binding.etContraseA.setVisibility(View.GONE);
                binding.etRepetirContraseA.setVisibility(View.GONE);
                binding.btnGuardarClave.setVisibility(View.GONE);
                binding.btnCClave.setVisibility(View.VISIBLE);
                binding.btnGuardar.setVisibility(View.VISIBLE);
            }
        });
        pvm.obtenerPropietarioActual();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}