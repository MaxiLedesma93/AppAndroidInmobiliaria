package com.example.appinmobiliaria.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appinmobiliaria.models.Propietario;
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
        Bundle bundle = new Bundle();
        Call<String> call = api.login(email, password);
        call.enqueue(new Callback<String>() {
            //en response.body() viene el token en un String.
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    SharedPreferences sp = ApiClient.conectar(context);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "Bearer "+ response.body());
                    editor.apply();
                    Call<Propietario> callPropietario = api.get("Bearer "+response.body());
                    callPropietario.enqueue(new Callback<Propietario>() {
                        @Override
                        public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                            bundle.putSerializable("propietario", response.body());
                            iniciarMenu(bundle);
                        }

                        @Override
                        public void onFailure(Call<Propietario> call, Throwable throwable) {
                            Toast.makeText(getApplication(), "Error obteniendo el usuario para" +
                                    " header", Toast.LENGTH_LONG).show();
                        }
                    });


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

    public void RecuperarPass(String email){
        Call<String> call = ApiClient.getEndPoints().resetearpass(email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(context, "Email enviado con exito revise su correo.",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Toast.makeText(context, "Error al enviar el Email.",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void iniciarMenu(Bundle p){


        Intent intent = new Intent(getApplication(), MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("propietario", p);
        getApplication().startActivity(intent);
    }
}
