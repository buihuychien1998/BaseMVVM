package com.example.baseapplication.presentation.ui.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.baseapplication.R;
import com.example.baseapplication.databinding.ActivityLoginBinding;
import com.example.baseapplication.model.User;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding loginBinding;

    AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginBinding.btnLogin.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        viewModel.getUserLiveData().observe(this, user -> {
            if (user == null){
                Toast.makeText(this, "Hehe", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
        });

        viewModel.getErrorModelLiveData().observe(this, errorModel -> {
            if (errorModel.getMessage() == null){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, errorModel.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin){
            viewModel.getUserList();
        }
    }
}