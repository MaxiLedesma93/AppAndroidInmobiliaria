package com.example.appinmobiliaria.ui.contrato;

import androidx.lifecycle.AndroidViewModel;
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
import com.example.appinmobiliaria.databinding.FragmentContratoBinding;
import com.example.appinmobiliaria.models.Contrato;

import java.util.List;

public class ContratoFragment extends Fragment {

    private ContratoViewModel mViewModel;
    private FragmentContratoBinding binding;
    private ContratoAdapter adapter;
    private RecyclerView rvContratos;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContratoBinding.inflate(getLayoutInflater());
        mViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);

        rvContratos = binding.rvContratos;
        mViewModel.getmListaContratos().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),
                        2, GridLayoutManager.VERTICAL, false);
                rvContratos.setLayoutManager(gridLayoutManager);
                adapter = new ContratoAdapter(contratos, binding.getRoot(), getLayoutInflater());
                rvContratos.setAdapter(adapter);

            }
        });






        mViewModel.inmueblesAlquilados();
        return binding.getRoot();
    }



}