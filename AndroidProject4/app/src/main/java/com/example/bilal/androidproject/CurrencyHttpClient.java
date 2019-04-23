package com.example.bilal.androidproject;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyHttpClient {
    private static String APPID = "90fe8b03cf2e02c7755e";
    private static String CountyListURL = "free.currconv.com/api/v7/countries?apiKey=90fe8b03cf2e02c7755e";


    public String getCountyList() {
        HttpURLConnection con = null ;
        BufferedReader br = null;
        InputStream stream=null;


        try {

            URL url = new URL("http://"+ "free.currconv.com/api/v7/countries?apiKey=90fe8b03cf2e02c7755e");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();

            //Log.d("KOUKOU","TEST OK ?");


            stream = con.getInputStream();
            //Log.d("KOUKOU", "Alors ?");

            br = new BufferedReader(new InputStreamReader(stream));
            //Log.d("KOUKOU", "buffer br");

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ( (line = br.readLine()) != null ) {
                buffer.append(line + "\n");
                //Log.d("KOUKOU",line);
            }
            stream.close();
            con.disconnect();

            //Log.d("KOUKOU", "presque");
            return buffer.toString();
        }

        catch(Throwable t) {
            t.printStackTrace();
            //Log.d("KOUKOU",t.toString());
        }
        finally {
            try { stream.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        //Log.d("KOUKOU","T MORT");
        return null;

    }

    public String getCurrency(String from, String to) {
        HttpURLConnection con = null ;
        BufferedReader br = null;
        InputStream stream=null;
        String ID_CONV = from + "_" + to;
        //String ID_CONV2 = to + "_" + from;



        try {

            URL url = new URL("http://"+ "free.currconv.com/api/v7/convert?q="+ ID_CONV+","+ ID_CONV+"&compact=ultra&apiKey=90fe8b03cf2e02c7755e");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();

            Log.d("KOUKOU","TEST OK ?");


            stream = con.getInputStream();
            Log.d("KOUKOU", "Alors ?");

            br = new BufferedReader(new InputStreamReader(stream));
            Log.d("KOUKOU", "buffer br");

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ( (line = br.readLine()) != null ) {
                buffer.append(line + "\n");
                Log.d("KOUKOU",line);
            }
            stream.close();
            con.disconnect();

            Log.d("KOUKOU", "presque");
            return buffer.toString();
        }

        catch(Throwable t) {
            t.printStackTrace();
            Log.d("KOUKOU",t.toString());
        }
        finally {
            try { stream.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        Log.d("KOUKOU","T MORT");
        return null;

    }
}
