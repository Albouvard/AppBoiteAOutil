package com.example.tip_taxes.meteo;

import com.example.tip_taxes.meteo.model.Location;
import com.example.tip_taxes.meteo.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParserJSON {

    public static Weather parse(String data){
        try {
            JSONObject jObj = new JSONObject(data);

            Location loc = new Location();
            Weather weather = new Weather();

            JSONObject coordObj = getObject("coord", jObj);
            loc.setLatitude(getFloat("lat", coordObj));
            loc.setLongitude(getFloat("lon", coordObj));

            JSONObject sysObj = getObject("sys", jObj);
            loc.setCountry(getString("country", sysObj));
            loc.setSunrise(getInt("sunrise", sysObj));
            loc.setSunset(getInt("sunset", sysObj));
            loc.setCity(getString("name", jObj));
            weather.location = loc ;

            JSONArray jArr = jObj.getJSONArray("weather");

            JSONObject JSONWeather = jArr.getJSONObject(0);
            weather.currentCondition.setWeatherId(getInt("id", JSONWeather));
            weather.currentCondition.setDescr(getString("description", JSONWeather));
            weather.currentCondition.setCondition(getString("main", JSONWeather));
            weather.currentCondition.setIcon(getString("icon", JSONWeather));

            JSONObject mainObj = getObject("main", jObj);
            weather.currentCondition.setHumidity(getInt("humidity", mainObj));
            weather.currentCondition.setPressure(getInt("pressure", mainObj));
            weather.temperature.setMaxTemp(getFloat("temp_max", mainObj));
            weather.temperature.setMinTemp(getFloat("temp_min", mainObj));
            weather.temperature.setTemp(getFloat("temp", mainObj));

            JSONObject wObj = getObject("wind", jObj);
            weather.wind.setSpeed(getFloat("speed", wObj));
            weather.wind.setDeg(getFloat("deg", wObj));

            JSONObject cObj = getObject("clouds", jObj);
            weather.clouds.setPerc(getInt("all", cObj));

            return weather;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getJSONObject(tagName);
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
}
