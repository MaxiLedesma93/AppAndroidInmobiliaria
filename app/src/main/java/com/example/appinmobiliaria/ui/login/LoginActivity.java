package com.example.appinmobiliaria.ui.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {
    private ActivityLoginBinding binding;
    private LoginActivityViewModel vmLogin;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private long lastShakeTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        vmLogin = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(LoginActivityViewModel.class);
        setContentView(binding.getRoot());
        binding.imageView2.setImageResource(R.drawable.logoapp);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        // Solicitar permisos en tiempo de ejecución
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CODE); }

        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1004);
        binding.btnLoguearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.etEmail.getText().toString();
                String pass = binding.etPassword.getText().toString();
                vmLogin.Logueo(email, pass);
            }
        });
        binding.tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vmLogin.RecuperarPass(binding.etEmail.getText().toString());
            }
        });

    }
    @Override public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float acceleration = (float) Math.sqrt(x * x + y * y + z * z);
        long currentTime = System.currentTimeMillis();
        if (acceleration > 12 && (currentTime - lastShakeTime > 1000)) {
            lastShakeTime = currentTime;
            realizarLlamada();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void realizarLlamada() {
        String phoneNumber = "tel:2664935541";
        // Reemplaza con el número que desees llamar
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        } else {
            Toast.makeText(this, "Permiso para llamadas no concedido",
                    Toast.LENGTH_SHORT).show();
        }
    }

}