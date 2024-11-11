package com.example.appinmobiliaria.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.appinmobiliaria.models.Contrato;


public class ContratoDetalleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Contrato> mContrato;
    private Contrato contrato;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
        context = getApplication().getApplicationContext();
    }
    public MutableLiveData<Contrato> getmContrato(){
        if(mContrato == null){
            mContrato = new MutableLiveData<>();

        }
        return mContrato;
    }
    public void setContrato(Bundle bundle){
        contrato = (Contrato) bundle.getSerializable("contrato");
        mContrato.setValue(contrato);

    }
}