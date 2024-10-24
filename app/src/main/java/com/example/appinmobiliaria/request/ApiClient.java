package com.example.appinmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.session.MediaSession;

import com.example.appinmobiliaria.models.Inmueble;
import com.example.appinmobiliaria.models.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {
    private static final String URL = "http://192.168.0.9:5000/";

    private static MisEndPoints mep;
    public static SharedPreferences sp;
    public static SharedPreferences conectar(Context context){
        if (sp==null){
            sp= context.getSharedPreferences("token.dat",0);
        }
        return  sp;
    }

    public static MisEndPoints getEndPoints() {
        Gson gson = new GsonBuilder().setLenient().create();
        // Construimos un cliente HTTP utilizando OkHttpClient para manejar las solicitudes
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // Crear un interceptor para el logging
        // agregar a gradle: implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14'
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mep = retrofit.create(MisEndPoints.class);
        return mep;
    }

    public interface MisEndPoints{

        //Realiza el login
        @FormUrlEncoded
        @POST("propietarios/login")
        Call<String> login(@Field("Email")String e, @Field("Clave")String c);


        //Accede al perfil del usuario Logueado enviando el token en el header.
        @GET("propietarios")
        Call<Propietario> get(@Header("Authorization") String authorization);

        @PATCH("propietarios/editar")
        Call<Propietario> editarPerfil(@Header("Authorization") String token, @Body Propietario p);

        @FormUrlEncoded
        @PATCH("propietarios/cambiarpass")
        Call<Propietario> cambiarpass(@Header("Authorization") String token,
                                      @Field("clVieja")String v,
                                      @Field("clNueva")String n);

        //Inmuebles

        @GET("inmuebles/listainmuebles")
        Call<List<Inmueble>> listaInmuebles(@Header("Authorization") String token);

        @PATCH("inmuebles/disponible{id}")
        Call<Inmueble> editarDisponible(@Header("Authorization") String token, @Path("id") int id);

    }


}
