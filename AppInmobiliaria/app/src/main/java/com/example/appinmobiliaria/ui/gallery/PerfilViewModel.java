package com.example.appinmobiliaria.ui.gallery;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appinmobiliaria.models.Propietario;
import com.example.appinmobiliaria.request.ApiClient;
import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    MutableLiveData<Propietario> mPropietario;
    Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }

    public MutableLiveData<Propietario> getPropietario(){
        if(mPropietario == null){
            mPropietario = new MutableLiveData<>();
        }
        return mPropietario;
    }


    public void obtenerPropietarioActual() {
        SharedPreferences sp = ApiClient.conectar(context);
        String token =sp.getString("token", "vacio");
        Call<Propietario> prop =  ApiClient.getEndPoints().get(token);
        prop.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    mPropietario.postValue(response.body());
                }else{

                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
    public void guardarPropietario(Propietario p){
       SharedPreferences sp = ApiClient.conectar(context);
       String t = sp.getString("token", "vacio");

        Call<Propietario> prop = ApiClient.getEndPoints().editarPerfil(t, p);
        prop.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    mPropietario.setValue(response.body());
                    Toast.makeText(context, "Se editaron los datos con éxito", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error al editar "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}