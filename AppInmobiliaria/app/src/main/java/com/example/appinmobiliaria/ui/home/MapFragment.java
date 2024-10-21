package com.example.appinmobiliaria.ui.home;




import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.appinmobiliaria.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

    public class MapFragment extends Fragment {

        private OnMapReadyCallback callback = new OnMapReadyCallback() {

            /**
             * Manipulates the map once available.
             * This callback is triggered when the map is ready to be used.
             * This is where we can add markers or lines, add listeners or move the camera.
             * In this case, we just add a marker near Sydney, Australia.
             * If Google Play services is not installed on the device, the user will be prompted to
             * install it inside the SupportMapFragment. This method will only be triggered once the
             * user has installed Google Play services and returned to the app.
             */
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng Inmobiliaria = new LatLng(-33.314566, -66.343357);
                CameraPosition camPos = new CameraPosition.Builder()
                        .target(Inmobiliaria)
                        .zoom(19)
                        .bearing(45)
                        .tilt(70)
                        .build();

                CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);

                googleMap.addMarker(new MarkerOptions().position(Inmobiliaria).title("Inmobiliaria Ledesma"));
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                googleMap.animateCamera(camUpd);
            }
        };

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {

            SupportMapFragment mapFragment =
                    (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
            if (mapFragment != null) {
                mapFragment.getMapAsync(callback);
            }
            return inflater.inflate(R.layout.fragment_maps, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            SupportMapFragment mapFragment =
                    (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
            if (mapFragment != null) {
                mapFragment.getMapAsync(callback);
            }
        }
    }



