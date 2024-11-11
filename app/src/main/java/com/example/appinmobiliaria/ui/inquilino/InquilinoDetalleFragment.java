package com.example.appinmobiliaria.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.databinding.FragmentInquilinoDetalleBinding;
import com.example.appinmobiliaria.models.Inquilino;

public class InquilinoDetalleFragment extends Fragment {

    private InquilinoDetalleViewModel mViewModel;
    private FragmentInquilinoDetalleBinding binding;

    public static InquilinoDetalleFragment newInstance() {
        return new InquilinoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        binding = FragmentInquilinoDetalleBinding.inflate(getLayoutInflater());

        mViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                    binding.tvCod.setText(inquilino.getId()+"");
                    binding.tvDetInqNombre.setText(inquilino.getNombre());
                    binding.tvDetInqAp.setText(inquilino.getApellido());
                    binding.tvDetInqDni.setText(inquilino.getDni());
                    binding.tvDetInqMail.setText(inquilino.getEmail());
                    binding.tvDetInqTel.setText(inquilino.getTelefono());
            }
        });


        mViewModel.mostrarInquilino(getArguments());
        return binding.getRoot();
    }


}