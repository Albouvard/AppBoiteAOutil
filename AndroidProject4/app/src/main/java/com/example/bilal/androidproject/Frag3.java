package com.example.bilal.androidproject;

import android.database.Observable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bilal.androidproject.model.Country;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag3 extends Fragment {
    private TextInputEditText priceStart;
    private TextInputEditText search1;
    private TextInputEditText search2;

    private ArrayList<Country> countryList;

    private ArrayList<Country> spinner1_countrylist;
    private ArrayList<Country> spinner2_countrylist;


    private TextView priceResult;
    private Button convertButton;
    private Spinner spinner1;
    private Spinner spinner2;

    String dep,arr;

    public interface AsyncResponse {
        void processFinish(Float output);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag3_layout,container,false);



        priceStart = (TextInputEditText) view.findViewById(R.id.prixInit);
        search1 = (TextInputEditText) view.findViewById(R.id.InputSearch1);
        search2 = (TextInputEditText) view.findViewById(R.id.InputSearch2);
        priceResult = (TextView) view.findViewById(R.id.resultPrice);
        convertButton = (Button) view.findViewById(R.id.convertButton);
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);

        countryList = new ArrayList<Country>();
        initCountryList();

        spinner1_countrylist = new ArrayList<>(countryList);
        spinner2_countrylist = new ArrayList<>(countryList);



        ArrayAdapter<Country> adapter1 = new ArrayAdapter<Country>(getContext(), android.R.layout.simple_spinner_dropdown_item,spinner1_countrylist);
        ArrayAdapter<Country> adapter2 = new ArrayAdapter<Country>(getContext(), android.R.layout.simple_spinner_dropdown_item,spinner2_countrylist);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);



        priceStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                execTask();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        search1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                        getSearchList2(spinner1_countrylist,s.toString());
// spinner1_countrylist.clear();
//                    spinner1_countrylist.addAll(getSearchList(s.toString()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        search2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getSearchList2(spinner2_countrylist,s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


        convertButton.setOnClickListener((View v) -> {

            execTask();
        });

        return view;
    }

    private void execTask(){
        dep= spinner1.getSelectedItem().toString();
        arr = spinner2.getSelectedItem().toString();
        dep= dep.substring(0,3);
        arr= arr.substring(0,3);
        Log.d("DIM",dep);
        Frag3.JsonTash task = new Frag3.JsonTash();
        task.execute(dep, arr);
    }

    private class JsonTash extends AsyncTask<String,Void,Float>{

        //public AsyncResponse delegate = null;


        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getActivity(), "on cherche la data",
                    Toast.LENGTH_LONG).show();

        }

        @Override
        protected Float doInBackground(String... strings) {
            try {
                String result = new CurrencyHttpClient().getCurrency(strings[0], strings[1]);

                result = result.substring(11, result.length() - 2);
                Float currency = Float.parseFloat(result);
                return currency;

            }
            catch (Throwable t){
                Log.d("DIM", t.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Float resultat) {
            super.onPostExecute(resultat);
            Float prixFinal;
            Float prixDeb;

            if (!(priceStart.getText().toString()).isEmpty()) {
                prixDeb = Float.parseFloat(String.valueOf(priceStart.getText()));
                prixFinal = prixDeb * resultat;
                priceResult.setText(String.format("%.2f "+arr, prixFinal));
            } else {
                Toast.makeText(getActivity(), "IMPOSSIBLE NULL",
                        Toast.LENGTH_LONG).show();
            }

        }

    }

    public void initCountryList(){
        countryList.add(new Country("CAD", "CANADA"));
        countryList.add(new Country("EUR", "EUROPE UNION"));
        countryList.add(new Country("USD", "USA"));
        countryList.add(new Country("AUD", "AUSTRALIAN"));
        countryList.add(new Country("JPY", "JAPAN"));
        countryList.add(new Country("KRW", "SOUTH KOREA"));
        countryList.add(new Country("ILS", "ISRAEL"));
        countryList.add(new Country("MXN", "MEXICO"));
        countryList.add(new Country("CDF", "CONGO"));
        countryList.add(new Country("CHF", "SWITZERLAND"));
        countryList.add(new Country("HRK", "CROATIA"));
        countryList.add(new Country("GBP", "GREAT BRITAIN"));
        countryList.add(new Country("BTC", "BITCOIN"));
    }


    public void getSearchList2(ArrayList<Country> sublist,String name){
        sublist.clear();

        for (int i = 0; i < countryList.size(); i++) {
            if(countryList.get(i).getAlpha3().toString().contains(name) ){
                Log.d("TEST", "res "+countryList.get(i).getAlpha3().toString());
                sublist.add(new Country(countryList.get(i).getAlpha3().toString(),countryList.get(i).getCurrencyName().toString()));

            }
//            Log.d("TEST", "res "+countryList.get(i).getAlpha3().toString().contains("C"));
        }

    }
}
