package com.example.appinmobiliaria.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appinmobiliaria.models.Inmueble;
import com.example.appinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmueble;
    private Inmueble i;
    private Context context;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }


    public LiveData<Inmueble> getInmueble(){
        if(inmueble==null){
            inmueble = new MutableLiveData<>();
        }

        return inmueble;
    }

    public void setInmueble(Bundle bundle){
        i = (Inmueble) bundle.getSerializable("inmueble");
        inmueble.setValue(i);
    }

    public void guardarEstado(int id, boolean b){
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token", "no token");
        Call<Inmueble> inmuebleCall = ApiClient.getEndPoints().editarDisponible(token, id);
        inmuebleCall.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()){

                    Toast.makeText(context, "El estado se guardó con éxito", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "error"+response.message(), Toast.LENGTH_LONG).show();

                }
            }
            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Falla en guardar estado(On failure)", Toast.LENGTH_LONG).show();
            }
        });

    }

}