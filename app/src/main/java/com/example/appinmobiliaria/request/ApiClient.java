package com.example.appinmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.session.MediaSession;
import android.net.Uri;

import com.example.appinmobiliaria.models.Contrato;
import com.example.appinmobiliaria.models.Inmueble;
import com.example.appinmobiliaria.models.Pago;
import com.example.appinmobiliaria.models.Propietario;
import com.example.appinmobiliaria.models.Tipo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class ApiClient {
    private static final String URL = "http://192.168.0.3:5000/";

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

        //permite al propietario editar su perfil.
        @PATCH("propietarios/editar")
        Call<Propietario> editarPerfil(@Header("Authorization") String token, @Body Propietario p);

        //cambia el password del propietario logueado.
        @FormUrlEncoded
        @PATCH("propietarios/cambiarpass")
        Call<Propietario> cambiarpass(@Header("Authorization") String token,
                                      @Field("clVieja")String v,
                                      @Field("clNueva")String n);

        //envia un mail con el link para resetear la clave de ingreso del propietario.
        @FormUrlEncoded
        @POST("propietarios/email")
        Call<String>resetearpass(@Field("email")String email);

        //Inmuebles
        //devuelve la lista de los inmuebles del propietario.
        @GET("inmuebles/listainmuebles")
        Call<List<Inmueble>> listaInmuebles(@Header("Authorization") String token);

        // edita el estado del inmueble, disponible/no disponible.
        @PATCH("inmuebles/{id}")
        Call<Inmueble> editarDisponible(@Header("Authorization") String token, @Path("id") int id);

        @Multipart
        @POST("inmuebles/")
        Call<Inmueble> crearInmueble(@Header("Authorization")String token,
                                     @Part MultipartBody.Part imagen,
                                     @Part("direccion")RequestBody direccion,
                                     @Part("ambientes")RequestBody ambientes,
                                     @Part("importe")RequestBody importe,
                                     @Part("uso")RequestBody uso, @Part("tipoId")RequestBody tipoId,
                                     @Part("disponible")RequestBody disponible);
        //Tipos
        //devuelve la lista de los tipos de inmueble.
        @GET("tipos/listatipos")
        Call<List<Tipo>> listaTipos(@Header("Authorization")String token);


        //Contratos


        //devuelve la lista de los inmuebles alquilados del propietario logueado.
        @GET("Contratos")
        Call<List<Contrato>> obtenerInmueblesAlquilados(@Header("Authorization") String token);

        //Pagos
        //devuelve la lista de los pagos de un contrato.
        @GET("Pagos/{id}")
        Call<List<Pago>> obtenerPagos(@Header("Authorization") String token, @Path("id") int idCon);
    }

}
