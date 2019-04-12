package com.example.tip_taxes.meteo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {

    private static String APPID = "00f433a93656dbd119f60b5ad60277b5";
    private static String BASE_URL = "api.openweathermap.org/data/2.5/weather?lat=";

    public String getWeatherData(Double latitude, Double longitude) {

        HttpURLConnection con = null ;
        InputStream is = null;

        try {

            URL url = new URL("http://"+ BASE_URL + Double.toString(latitude) +"&lon=" + Double.toString(longitude) + "&APPID=" + APPID);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();



            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ( (line = br.readLine()) != null )
                buffer.append(line + "rn");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }
}
