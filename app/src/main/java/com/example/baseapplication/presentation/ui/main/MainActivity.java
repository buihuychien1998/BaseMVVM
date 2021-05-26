package com.example.baseapplication.presentation.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;

import com.example.baseapplication.R;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}