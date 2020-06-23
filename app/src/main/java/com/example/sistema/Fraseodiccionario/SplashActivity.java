package com.example.sistema.Fraseodiccionario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.imageViewSplash);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irMenu();
            }
        });
    }

    public void irMenu()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}