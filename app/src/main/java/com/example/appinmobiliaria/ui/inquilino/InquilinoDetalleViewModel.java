package com.example.appinmobiliaria.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appinmobiliaria.models.Inquilino;

public class InquilinoDetalleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inquilino> inquilino;
    private Context contexto;
    public InquilinoDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<Inquilino> getInquilino(){
        if(inquilino == null){
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }

    public void mostrarInquilino(Bundle bundle){
        Inquilino i = (Inquilino) bundle.getSerializable("inquilino");
        inquilino.setValue(i);

    }

}