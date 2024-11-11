package com.example.appinmobiliaria.ui.contrato;

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
import com.example.appinmobiliaria.databinding.FragmentPagoBinding;
import com.example.appinmobiliaria.models.Pago;

import java.util.List;

public class PagoFragment extends Fragment {

    private PagoViewModel mViewModel;
    private FragmentPagoBinding binding;
    private RecyclerView rvPagos;
    private PagoAdapter adapter;

    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPagoBinding.inflate(getLayoutInflater());
        mViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        rvPagos = binding.rvPagos;
        mViewModel.getmListaPagos().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),
                        1, GridLayoutManager.VERTICAL, false);
                rvPagos.setLayoutManager(gridLayoutManager);
                adapter = new PagoAdapter(pagos, binding.getRoot(), getLayoutInflater());
                rvPagos.setAdapter(adapter);
            }
        });
        mViewModel.mostrarPagos(getArguments());
        return binding.getRoot();
    }



}