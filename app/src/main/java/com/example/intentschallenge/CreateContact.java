package com.example.intentschallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity implements View.OnClickListener {

    ImageView ivHappy, ivNeutral, ivSad;
    EditText etName, etNumber, etLocation, etWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etLocation = findViewById(R.id.etLocation);
        etWeb = findViewById(R.id.etWeb);
        ivHappy = findViewById(R.id.ivHappy);
        ivNeutral = findViewById(R.id.ivNeutral);
        ivSad = findViewById(R.id.ivSad);

        ivHappy.setOnClickListener(this);
        ivSad.setOnClickListener(this);
        ivNeutral.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (etName.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty()
                || etLocation.getText().toString().isEmpty() || etWeb.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please complete all fields.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("name", etName.getText().toString().trim());
            intent.putExtra("number", etNumber.getText().toString().trim());
            intent.putExtra("location", etLocation.getText().toString().trim());
            intent.putExtra("web", etWeb.getText().toString().trim());

            if (v.getId() == R.id.ivHappy) {
                intent.putExtra("mood", "happy");
            } else if (v.getId() == R.id.ivNeutral) {
                intent.putExtra("mood", "neutral");
            } else {
                intent.putExtra("mood", "sad");
            }

            setResult(RESULT_OK, intent);

            CreateContact.this.finish();


        }


    }
}