package com.example.appinmobiliaria.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.databinding.FragmentInquilinoBinding;
import com.example.appinmobiliaria.models.Contrato;

import java.util.List;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel mViewModel;
    private FragmentInquilinoBinding binding;
    private RecyclerView rvInquilinos;
    private InquilinoAdapter adapter;

    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInquilinoBinding.inflate(getLayoutInflater());
        mViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        rvInquilinos = (RecyclerView) binding.rvInquilinos;
        mViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),
                        2, GridLayoutManager.VERTICAL, false);
                rvInquilinos.setLayoutManager(gridLayoutManager);
                adapter = new InquilinoAdapter(contratos, binding.getRoot(), getLayoutInflater());
                rvInquilinos.setAdapter(adapter);
            }
        });
        mViewModel.mostrarInmuebles();



        return binding.getRoot();
    }


}