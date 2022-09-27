package com.example.gjo_dief;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void SixSided(View view) {
        Intent intent = new Intent(this, sixsidedice.class);
        startActivity(intent);
    }
    public void TwentySided(View view) {
        Intent intent = new Intent(this, twentysidedice.class);
        startActivity(intent);
    }
}