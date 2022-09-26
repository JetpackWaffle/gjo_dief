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

public class sixsidedice extends AppCompatActivity {

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
        setContentView(R.layout.activity_sixsidedice);

        imageViewDice = findViewById(R.id.image_view_dice);
        imageViewDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        int randomNumber = rng.nextInt(6) + 1;
        MediaPlayer one= MediaPlayer.create(sixsidedice.this,R.raw.dominated);
        MediaPlayer crit= MediaPlayer.create(sixsidedice.this,R.raw.domination);
        switch(randomNumber)
        {
            case 1:
                imageViewDice.setImageResource(R.drawable.d61);
                Toast.makeText(this, "Oooh, unfortunate, a 1.", Toast.LENGTH_LONG).show();
                one.start();
                break;
            case 2:
                imageViewDice.setImageResource(R.drawable.d62);
                break;
            case 3:
                imageViewDice.setImageResource(R.drawable.d63);
                break;
            case 4:
                imageViewDice.setImageResource(R.drawable.d64);
                break;
            case 5:
                imageViewDice.setImageResource(R.drawable.d65);
                break;
            case 6:
                imageViewDice.setImageResource(R.drawable.d66);
                Toast.makeText(this, "YOOOOOO A CRIT", Toast.LENGTH_LONG).show();
                crit.start();
                break;
        }
    }
}