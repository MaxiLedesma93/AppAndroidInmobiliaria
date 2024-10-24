package com.example.appinmobiliaria.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.databinding.FragmentInmuebleBinding;
import com.example.appinmobiliaria.models.Inmueble;

import java.util.List;

public class InmuebleFragment extends Fragment {

    private FragmentInmuebleBinding binding;

    private InmuebleViewModel inmuebleViewModel;
    private RecyclerView rvInmuebles;
    private InmuebleAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inmueble, container, false);
        inmuebleViewModel =
                new ViewModelProvider(this).get(InmuebleViewModel.class);
        rvInmuebles = root.findViewById(R.id.rvInmuebles);

        inmuebleViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rvInmuebles.setLayoutManager(gridLayoutManager);
                adapter = new InmuebleAdapter(inmuebles,root,getLayoutInflater());
                rvInmuebles.setAdapter(adapter);
            }
        });
        inmuebleViewModel.mostrarInmuebles();

        return root;
    }
}