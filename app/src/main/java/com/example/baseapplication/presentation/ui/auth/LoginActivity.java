package com.example.baseapplication.presentation.ui.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.baseapplication.R;
import com.example.baseapplication.databinding.ActivityLoginBinding;
import com.example.baseapplication.presentation.base.BaseActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends BaseActivity<ActivityLoginBinding, AuthViewModel> {
    @Override
    public void initViewModel() {
        viewModel = getViewModel(AuthViewModel.class);
    }

    @Override
    public void initObservers() {
        viewModel.getUserLiveData().observe(this, user -> {
            if (user == null) {
                Toast.makeText(this, "Hehe", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
        });

        viewModel.getErrorModelLiveData().observe(this, errorModel -> {
            if (errorModel.getMessage() == null) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, errorModel.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void initListeners() {
        viewBinding.btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            viewModel.getUserList();
        }
    }
}