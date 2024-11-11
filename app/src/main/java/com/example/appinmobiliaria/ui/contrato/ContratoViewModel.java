package com.example.appinmobiliaria.ui.contrato;

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

public class ContratoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Contrato>> mListaContratos;


    public ContratoViewModel(@NonNull Application application) {
        super(application);
        context = getApplication().getApplicationContext();
    }
    public MutableLiveData<List<Contrato>> getmListaContratos(){
        if(mListaContratos == null){
            mListaContratos = new MutableLiveData<>();
        }
        return mListaContratos;
    }
    public void inmueblesAlquilados(){
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token", "-1");
        Call<List<Contrato>> con = ApiClient.getEndPoints().obtenerInmueblesAlquilados(token);
        con.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()){
                    mListaContratos.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Toast.makeText(context, "Failure en obtener inmuebles Alquilados ", Toast.LENGTH_LONG).show();
            }
        });
    }
}