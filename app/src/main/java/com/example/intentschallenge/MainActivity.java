package com.example.intentschallenge;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;
    ImageView ivLocation, ivMood, ivPhone, ivWeb;
    final int CREATE_CONTACT_REQUEST_CODE = 0;
    String name = "", number = "", web = "", location = "", mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.btnSubmit);
        ivMood = findViewById(R.id.ivMood);
        ivPhone = findViewById(R.id.ivPhone);
        ivWeb = findViewById(R.id.ivWeb);
        ivLocation = findViewById(R.id.ivLocation);

        ivMood.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.intentschallenge.CreateContact.class);
                startActivityForResult(intent, CREATE_CONTACT_REQUEST_CODE);
            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + location));
                startActivity(intent);
            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + web));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_CONTACT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                ivMood.setVisibility(View.VISIBLE);
                ivPhone.setVisibility(View.VISIBLE);
                ivWeb.setVisibility(View.VISIBLE);
                ivLocation.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                number = data.getStringExtra("number");
                location = data.getStringExtra("location");
                web = data.getStringExtra("web");
                mood = data.getStringExtra("mood");

                if (mood.equals("happy")) {
                    ivMood.setImageResource(R.drawable.happy);
                } else if (mood.equals("sad")) {
                    ivMood.setImageResource(R.drawable.sad);
                } else {
                    ivMood.setImageResource(R.drawable.neutral);
                }

            } else {
                Toast.makeText(this, "No data was passed", Toast.LENGTH_SHORT).show();
            }

        }
    }
}