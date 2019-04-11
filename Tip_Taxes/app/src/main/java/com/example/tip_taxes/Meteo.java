package com.example.tip_taxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Meteo extends AppCompatActivity {
    TextView ville,textTemp,temperature,vent,pression;
    ImageView image;
    String temps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        ville=findViewById(R.id.ville);
        textTemp=findViewById(R.id.textTemp);
        temperature=findViewById(R.id.temperature);
        vent=findViewById(R.id.vent);
        pression = findViewById(R.id.pression);
        image = findViewById(R.id.image);
        temps="02d";
        chooseImageSource();
    }

    private void chooseImageSource() {
        if(temps.equals("01d")){
            image.setImageResource(R.drawable.soleil);
        }
        else if(temps.equals("02d")){
            image.setImageResource(R.drawable.soleilnuageux);
        }
        else if(temps.equals("03d")|| temps.equals("04d") || temps.equals("50d")){
            image.setImageResource(R.drawable.nuage);
        }
        else if(temps.equals("09d") || temps.equals("10d")){
            image.setImageResource(R.drawable.pluvieux);
        }
        else if(temps.equals("11d")){
            image.setImageResource(R.drawable.tonnerre);
        }
        else if(temps.equals("13d")){
            image.setImageResource(R.drawable.neige);
        }
    }

}
