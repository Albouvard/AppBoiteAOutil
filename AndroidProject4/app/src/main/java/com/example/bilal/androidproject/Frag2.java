package com.example.bilal.androidproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bilal.androidproject.model.Weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag2 extends Fragment {

    TextView ville,textTemp,temperature,vent,pression;
    ImageView image;
    String temps;
    LinearLayout meteoView, progressBar;

    private float CELSIUS_CONVERT = -273.15f;
    private float KMH_CONVERT = 3.6f;

    private double latitude;
    private double longitude;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2_layout,container,false);

        ville= view.findViewById(R.id.ville);
        textTemp= view.findViewById(R.id.textTemp);
        temperature=view.findViewById(R.id.temperature);
        vent= view.findViewById(R.id.vent);
        pression = view.findViewById(R.id.pression);
        image = view.findViewById(R.id.image);
        meteoView = view.findViewById(R.id.meteoView);
        progressBar = view.findViewById(R.id.progressBar);


        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Log.i("DEBUG","le mobile c super");
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Frag2.JSONWeatherTask task = new Frag2.JSONWeatherTask();
                task.execute(latitude, longitude);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };

        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }

        return view;
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

    private class JSONWeatherTask extends AsyncTask<Double, Void, Weather> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Weather doInBackground(Double[] coord) {


            Weather weather = new Weather();
            String data = new WeatherHttpClient().getWeatherData(coord[0], coord[1]);

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

            progressBar.setVisibility(View.GONE);
            meteoView.setVisibility(View.VISIBLE);

            float tmp = weather.temperature.getTemp() + CELSIUS_CONVERT;
            float windSpeed = weather.wind.getSpeed()*KMH_CONVERT;

            String city = weather.location.getCity();
            String icon = weather.currentCondition.getIcon();
            int pressure = weather.currentCondition.getPressure();

            temps=icon;
            ville.setText(city);
            temperature.setText(String.format(" %.1f ", tmp));
            vent.setText(String.format(" %.1f ", windSpeed));
            pression.setText(String.format(" %d ", pressure));
            chooseImageSource();
        }
    }
}
