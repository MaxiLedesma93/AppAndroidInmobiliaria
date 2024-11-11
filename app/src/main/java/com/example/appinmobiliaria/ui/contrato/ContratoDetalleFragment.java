package com.example.appinmobiliaria.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.databinding.FragmentContratoDetalleBinding;
import com.example.appinmobiliaria.models.Contrato;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContratoDetalleFragment extends Fragment {

    private ContratoDetalleViewModel mViewModel;
    private FragmentContratoDetalleBinding binding;

    public static ContratoDetalleFragment newInstance() {
        return new ContratoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContratoDetalleBinding.inflate(getLayoutInflater());

        mViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);

        mViewModel.getmContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                binding.etConId.setText(contrato.getId()+"");
                //damos formato a las fechas para que no muestre la hora
                DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDate fecInicio = LocalDate.parse(contrato.getFecInicio(), pattern);
                binding.etConFecIni.setText(fecInicio+"");
                LocalDate fecFin = LocalDate.parse(contrato.getFecFin(), pattern);
                binding.etConFecFin.setText(fecFin+"");
                binding.etConMonto.setText(contrato.getMonto()+"");
                binding.etConNomInq.setText(contrato.getInquilino().getNombre()+" "+contrato.getInquilino().getApellido());
                binding.etConDireccion.setText(contrato.getInmueble().getDireccion());
                binding.btPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pagos", contrato);
                        Navigation.findNavController(view).navigate(R.id.pagoFragment, bundle);
                    }
                });
            }
        });
        mViewModel.setContrato(getArguments());

        return binding.getRoot();
    }



}