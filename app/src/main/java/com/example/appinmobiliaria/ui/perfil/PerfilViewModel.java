package com.example.appinmobiliaria.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.models.Propietario;
import com.example.appinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    MutableLiveData<Propietario> mPropietario;
    MutableLiveData<Integer> mVisibilidad;
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
    public MutableLiveData<Integer> getmVisibilidad(){
        if(mVisibilidad==null){
            mVisibilidad = new MutableLiveData<>();
        }
        return mVisibilidad;
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
        Call<Propietario> prop = ApiClient.getEndPoints().editarPerfil(t,p);
        prop.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    mPropietario.postValue(response.body());
                    Toast.makeText(context, "Se editaron los datos con éxito", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error al editar datos. "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void cambiarPass(String contra, String rep, String claveActual){
        if(contra!=null&&contra.equals(rep)){
            //cambiamos password
            SharedPreferences sp = ApiClient.conectar(context);
            String token =sp.getString("token", "vacio");
            Call<Propietario> prop = ApiClient.getEndPoints().cambiarpass(token,claveActual, contra);
            prop.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {

                    Toast.makeText(context, "Se edito el password con éxito", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable throwable) {
                    Toast.makeText(context, "Error al editar Password", Toast.LENGTH_LONG).show();
                }
            });
        }else if(contra!=null&&rep!=null){
            Toast.makeText(context,"Las claves son distintas.",Toast.LENGTH_LONG).show();
        }
    }

}