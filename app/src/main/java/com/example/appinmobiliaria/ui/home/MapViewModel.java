package com.example.appinmobiliaria.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapViewModel extends AndroidViewModel {

    MutableLiveData<MapaActual> mapa;

    public MapViewModel(@NonNull Application application) {
        super(application);
    }
    MutableLiveData<MapaActual> getMapa(){
        if(mapa==null){
            mapa = new MutableLiveData<>();
        }
        return mapa;
    }
    public void obtenerMapa(){
        MapaActual mapaActual = new MapaActual();
        mapa.setValue(mapaActual);
    }
    public class MapaActual implements OnMapReadyCallback {
        LatLng INMOBILIARIA = new LatLng(-33.30213, -66.3369);


        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

            CameraPosition camPos = new CameraPosition.Builder()
                    .target(INMOBILIARIA)
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();

            CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);

            googleMap.addMarker(new MarkerOptions().position(INMOBILIARIA).title("Inmobiliaria Ledesma"));
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.animateCamera(camUpd);
        }
    }
}