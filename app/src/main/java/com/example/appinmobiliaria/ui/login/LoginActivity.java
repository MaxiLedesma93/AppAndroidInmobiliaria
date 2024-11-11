package com.example.appinmobiliaria.ui.login;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginActivityViewModel vmLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        vmLogin = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(LoginActivityViewModel.class);
        setContentView(binding.getRoot());
        binding.imageView2.setImageResource(R.drawable.logoapp);
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
}