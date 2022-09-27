package com.example.gjo_dief;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;

public class twentysidedice extends AppCompatActivity {

    private ImageView imageViewDice;
    private Random rng = new Random();

    //shake things
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twentysidedice);

        imageViewDice = findViewById(R.id.twentysideddice);
        imageViewDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer tap = MediaPlayer.create(twentysidedice.this, R.raw.rolltap);
                tap.start();
                rollD6();
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12)
            {
                MediaPlayer shake= MediaPlayer.create(twentysidedice.this,R.raw.rollshake);
                shake.start();
                rollD6();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    @Override
    protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    private void rollD6()
    {
        int randomNumber = rng.nextInt(20) + 1;
        MediaPlayer one= MediaPlayer.create(twentysidedice.this,R.raw.dominated);
        MediaPlayer crit= MediaPlayer.create(twentysidedice.this,R.raw.domination);
        switch(randomNumber)
        {
            case 1:
                imageViewDice.setImageResource(R.drawable.d201);
                Toast.makeText(this, "Oooh, unfortunate, a 1.", Toast.LENGTH_LONG).show();
                one.start();
                break;
            case 2:
                imageViewDice.setImageResource(R.drawable.d202);
                break;
            case 3:
                imageViewDice.setImageResource(R.drawable.d203);
                break;
            case 4:
                imageViewDice.setImageResource(R.drawable.d204);
                break;
            case 5:
                imageViewDice.setImageResource(R.drawable.d205);
                break;
            case 6:
                imageViewDice.setImageResource(R.drawable.d206);
                break;
            case 7:
                imageViewDice.setImageResource(R.drawable.d207);
                break;
            case 8:
                imageViewDice.setImageResource(R.drawable.d208);
                break;
            case 9:
                imageViewDice.setImageResource(R.drawable.d209);
                break;
            case 10:
                imageViewDice.setImageResource(R.drawable.d2010);
                break;
            case 11:
                imageViewDice.setImageResource(R.drawable.d2011);
                break;
            case 12:
                imageViewDice.setImageResource(R.drawable.d2012);
                break;
            case 13:
                imageViewDice.setImageResource(R.drawable.d2013);
                break;
            case 14:
                imageViewDice.setImageResource(R.drawable.d2014);
                break;
            case 15:
                imageViewDice.setImageResource(R.drawable.d2015);
                break;
            case 16:
                imageViewDice.setImageResource(R.drawable.d2016);
                break;
            case 17:
                imageViewDice.setImageResource(R.drawable.d2017);
                break;
            case 18:
                imageViewDice.setImageResource(R.drawable.d2018);
                break;
            case 19:
                imageViewDice.setImageResource(R.drawable.d2019);
                break;
            case 20:
                imageViewDice.setImageResource(R.drawable.d2020);
                Toast.makeText(this, "YOOOOOO A CRIT", Toast.LENGTH_LONG).show();
                crit.start();
                break;
        }
    }
}