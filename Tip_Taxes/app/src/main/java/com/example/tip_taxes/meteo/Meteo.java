package com.example.tip_taxes.meteo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tip_taxes.R;
import com.example.tip_taxes.meteo.model.Weather;
import com.example.tip_taxes.meteo.model.WeatherHttpClient;

public class Meteo extends AppCompatActivity {
    TextView ville,textTemp,temperature,vent,pression;
    ImageView image;
    String temps;

    private float CELSIUS_CONVERT = -273.15f;
    private float KMH_CONVERT = 3.6f;

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

        String city = "Chicoutimi,ca";

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
    }

    private void chooseImageSource() {
        if(temps.equals("01d") || temps.equals("01n")){
            image.setImageResource(R.drawable.soleil);
            textTemp.setText(getString(R.string.soleil));
        }
        else if(temps.equals("02d") || temps.equals("02n")){
            image.setImageResource(R.drawable.soleilnuageux);
            textTemp.setText(getString(R.string.soleilnuageux));
        }
        else if(temps.equals("03d")|| temps.equals("04d") || temps.equals("50d") || temps.equals("03n")|| temps.equals("04n") || temps.equals("50n")){
            image.setImageResource(R.drawable.nuage);
            textTemp.setText(getString(R.string.nuage));
        }
        else if(temps.equals("09d") || temps.equals("10d") || temps.equals("09n") || temps.equals("10n")){
            image.setImageResource(R.drawable.pluvieux);
            textTemp.setText(getString(R.string.pluvieux));
        }
        else if(temps.equals("11d") || temps.equals("11n")){
            image.setImageResource(R.drawable.tonnerre);
            textTemp.setText(getString(R.string.tonnerre));

        }
        else if(temps.equals("13d") || temps.equals("13n")){
            image.setImageResource(R.drawable.neige);
            textTemp.setText(getString(R.string.neige));
        }
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String[] params) {
            Weather weather = new Weather();
            String data = new WeatherHttpClient().getWeatherData(params[0]);

            try {
                weather = ParserJSON.parse(data);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return weather;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            float tmp = weather.temperature.getTemp() + CELSIUS_CONVERT;
            float windSpeed = weather.wind.getSpeed()*KMH_CONVERT;

            String city = weather.location.getCity();
            String icon = weather.currentCondition.getIcon();
            int pressure = weather.currentCondition.getPressure();

            temps=icon;
            ville.setText(city);
            temperature.setText(String.format("Température : %.1f °C", tmp));
            vent.setText(String.format("Vent : %.1f Km/h", windSpeed));
            pression.setText(String.format("Pression : %d hPa", pressure));
            chooseImageSource();
        }
    }

}
