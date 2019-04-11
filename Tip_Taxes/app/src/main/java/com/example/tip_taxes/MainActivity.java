package com.example.tip_taxes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tip_taxes.meteo.Meteo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openTipTax(View view) {
        Intent i = new Intent(this,TipTax.class);
        startActivity(i);
    }
    public void openMeteo(View view) {
        Intent i = new Intent(this, Meteo.class);
        startActivity(i);
    }
}
