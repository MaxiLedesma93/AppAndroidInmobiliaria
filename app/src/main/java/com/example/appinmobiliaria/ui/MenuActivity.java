package com.example.appinmobiliaria.ui;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.databinding.ActivityMenuBinding;
import com.example.appinmobiliaria.models.Propietario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;

        NavigationView navigationView = binding.navView;
        iniciarHeader(navigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_map, R.id.nav_perfil, R.id.nav_inmueble,R.id.nav_contrato, R.id.nav_inquilino)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        solicitarPermisos();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void iniciarHeader(NavigationView navigationView){
        View header = navigationView.getHeaderView(0);

        TextView nombre = header.findViewById(R.id.tvNombreHeader);
        TextView mail = header.findViewById(R.id.tvMailHeader);
        ImageView imageView = header.findViewById(R.id.imageView);

        Propietario p = this.getIntent().getBundleExtra("propietario").getSerializable("propietario", Propietario.class);
        Glide.with(header.getContext())
                .load("http://192.168.0.3:5000/"+p.getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        nombre.setText(p.getNombre()+ " " + p.getApellido());
        mail.setText(p.getEmail()+"");
    }
    public void solicitarPermisos(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && (checkSelfPermission(ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)  ||
                (checkSelfPermission(ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)){
            requestPermissions(new String[]{ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION},1000);
        }
    }

}