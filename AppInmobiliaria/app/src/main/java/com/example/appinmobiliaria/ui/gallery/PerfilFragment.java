package com.example.appinmobiliaria.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.appinmobiliaria.databinding.FragmentPerfilBinding;
import com.example.appinmobiliaria.models.Propietario;

public class PerfilFragment extends Fragment {
    private PerfilViewModel pvm;

    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         pvm =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        pvm.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.etId.setText(propietario.getId()+"");
                binding.etDni.setText(propietario.getDni());
                binding.etNombre.setText(propietario.getNombre());
                binding.etApellido.setText(propietario.getApellido());
                binding.etMail.setText(propietario.getEmail());
                binding.etTel.setText(propietario.getTelefono());
                //etPass.setText(propietario.getContraseña());

            }
        });
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Propietario p = new Propietario();
                p.setId(Integer.parseInt(binding.etId.getText().toString()+""));
                p.setDni(binding.etDni.getText().toString());
                p.setNombre(binding.etNombre.getText().toString());
                p.setApellido(binding.etApellido.getText().toString());
                p.setEmail(binding.etMail.getText().toString());
                // p.setContraseña(etPass.getText().toString());
                p.setTelefono(binding.etTel.getText().toString());

                pvm.guardarPropietario(p);
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