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

    private float KELVIN = -273.15f;

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

        String city = "Chicoutimi,CA";

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
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

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String[] params) {
            Weather weather = new Weather();
            String data = new WeatherHttpClient().getWeatherData(params[0]);

            if(data == null){
                Log.i("DEBUG", "data is null");
            }

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

            float tmp = weather.temperature.getTemp() + KELVIN;
/*            String temperatureString = Float.toString(tmp);*/
            float windSpeed = weather.wind.getSpeed()*3.6f;
            /*String windSpeed = Float.toString(tmp);*/

            String city = weather.location.getCity();
            String icon = weather.currentCondition.getIcon();
            String description = weather.currentCondition.getDescr();
            int pressure = weather.currentCondition.getPressure();

/*            Log.i("TEST","ville : "+city);
            Log.i("TEST","icon : "+icon);
            Log.i("TEST", "description : "+description);
            Log.i("TEST","temperature : "+temperatureString+" °C");
            Log.i("TEST", "vent : "+ windSpeed);
            Log.i("TEST", "pression : "+pressure);*/

            temps=icon;
            ville.setText(city);
            textTemp.setText(description);
            temperature.setText(String.format("Température : %.1f °C", tmp));
            vent.setText(String.format("Vent : %.1f Km/h", windSpeed));
            pression.setText(String.format("Pression : %d hPa", pressure));
            chooseImageSource();
        }
    }

}
