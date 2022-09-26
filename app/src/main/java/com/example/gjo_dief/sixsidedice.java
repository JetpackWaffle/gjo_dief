package com.example.gjo_dief;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class sixsidedice extends AppCompatActivity {

    private ImageView imageViewDice;
    private Random rng = new Random();

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
    }

    private void rollD6()
    {
        int randomNumber = rng.nextInt(6) + 1;

        switch(randomNumber)
        {
            case 1:
                imageViewDice.setImageResource(R.drawable.d61);
                Toast.makeText(this, "Oooh, unfortunate, a 1.", Toast.LENGTH_LONG).show();
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
                Toast.makeText(this, "YOOOOOO LET'S GOOOO", Toast.LENGTH_LONG).show();
                break;
        }
    }
}