package com.example.appinmobiliaria.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appinmobiliaria.ui.MenuActivity;
import com.example.appinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel{
    private Context context;
    public LoginActivityViewModel(@NonNull Application application) {

        super(application);
        context = application.getApplicationContext();
    }
    public void Logueo(String email, String password){
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        Call<String> call = api.login(email, password);
        call.enqueue(new Callback<String>() {
            //en response.body() viene el token en un String.
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.d("salida", response.body());
                    SharedPreferences sp = ApiClient.conectar(context);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "Bearer "+ response.body());
                    editor.apply();
                    Log.d("salida 2", sp.getString("token","nada"));
                    iniciarMenu();
                }else {
                    Toast.makeText(getApplication(), "Usuario y/o Contraseña Incorrectos",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Usuario y/o Contraseña Incorrectos",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void iniciarMenu(){
        Intent intent = new Intent(getApplication(), MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getApplication().startActivity(intent);
    }
}
