package com.example.appinmobiliaria.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appinmobiliaria.models.Contrato;
import com.example.appinmobiliaria.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Contrato>> listMutableLiveData;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context = getApplication().getApplicationContext();
    }
    public MutableLiveData<List<Contrato>> getContrato() {
        if(listMutableLiveData == null){
            listMutableLiveData = new MutableLiveData<>();
        }
        return listMutableLiveData;
    }

    /*public LiveData<List<Inmueble>> getInmueble(){
        if(inmueble == null){
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }*/

    public void mostrarInmuebles() {
        SharedPreferences sp = ApiClient.conectar(context);
        String t = sp.getString("token", "vacio");

        Call<List<Contrato>> con = ApiClient.getEndPoints().obtenerInmueblesAlquilados(t);
        con.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()){
                    listMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}