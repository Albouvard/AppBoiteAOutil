package com.example.bilal.androidproject;

import android.util.Log;

import com.example.bilal.androidproject.model.Country;
import com.example.bilal.androidproject.model.Location;
import com.example.bilal.androidproject.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ParserJSONCountryList {
    public static Country parse(String data){
        try {
            JSONObject jObj = new JSONObject(data);

            //Country country = new Country();


            JSONObject pays = getObject("results", jObj);


            /*
            for(int i=0;i<pays.length();i++){
                Log.d("KOUKOU", pays.getJSONObject("i"));
            }
*/
            //country.setAlpha3(pays.getString("AF"));
            //Log.d("KOUKOU",country.toString());


            //return country;
            return null;
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
