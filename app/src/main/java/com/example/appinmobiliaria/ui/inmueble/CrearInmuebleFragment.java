package com.example.appinmobiliaria.ui.inmueble;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.databinding.FragmentCrearInmuebleBinding;
import com.example.appinmobiliaria.models.Tipo;

import java.util.List;

public class CrearInmuebleFragment extends Fragment {

    private CrearInmuebleViewModel mViewModel;
    private FragmentCrearInmuebleBinding binding;
    private Intent intent;

    private ActivityResultLauncher<Intent> arl;

    private ActivityResultLauncher<String> perm;
    private Spinner spinner;
    private Uri uriImagen;


    public static CrearInmuebleFragment newInstance() {
        return new CrearInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCrearInmuebleBinding.inflate(getLayoutInflater());
        mViewModel = new ViewModelProvider(this).get(CrearInmuebleViewModel.class);
        abrirGaleria();
        spinner = binding.getRoot().findViewById(R.id.spTipo);

        mViewModel.getMSpinner().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_dropdown_item, strings);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapter);
            }
        });
        mViewModel.getMTipos().observe(getViewLifecycleOwner(), new Observer<List<Tipo>>() {
            @Override
            public void onChanged(List<Tipo> tipos) {
                mViewModel.cargarSpinner(tipos);
            }
        });

     //   adapter.getItem(spinner.getSelectedItemPosition());
        mViewModel.getMUri().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.ivFotoDetInmu.setImageURI(uri);
                uriImagen =uri;
            }
        });


        binding.btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(intent);
            }
        });
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccion = binding.tvDetDir.getText().toString();
                int ambientes = Integer.parseInt(binding.tvDetAmb.getText().toString());
                String uso = binding.tvDetUso.getText().toString();
                int importe = Integer.parseInt(binding.tvDetPrecio.getText().toString());
                String tipoDesc = spinner.getSelectedItem().toString();
                mViewModel.guardarInmueble(direccion, ambientes, uso, importe, tipoDesc, uriImagen);


            }
        });

        mViewModel.cargarDatosTipo();
        return binding.getRoot();
    }
    private void abrirGaleria(){
        //open_Document
        intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        arl=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        mViewModel.recibirFoto(result);
                    }
                });
    }

}