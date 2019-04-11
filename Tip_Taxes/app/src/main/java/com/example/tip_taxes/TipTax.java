package com.example.tip_taxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TipTax extends AppCompatActivity {
    public EditText initPrice;
    public TextView totalPrice;
    public TextView taxPrice;
    public TextView tipsPrice;
    public Switch taxSwitch;
    public Switch tipSwitch;
    public RadioGroup radioGroup;
    public RadioButton radioButton;
    public EditText otherTip;


    public double intInitPrice, intTaxPrice,intTipsPrice,intTotalPrice,tipPercentage,persPercentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_tax);
        totalPrice = (TextView)findViewById(R.id.totalValue);
        taxPrice = (TextView)findViewById(R.id.taxText);
        tipsPrice =(TextView)findViewById(R.id.tipsText);
        initPrice = (EditText)findViewById(R.id.initPrice);
        otherTip = (EditText)findViewById(R.id.otherTip);
        persPercentage = 0;
        tipPercentage=0.15;
        initPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                intInitPrice = Double.parseDouble(s.toString());
                checkTax();
                checkTips();
                refresh();

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otherTip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                persPercentage = Double.parseDouble(s.toString());
                persPercentage = persPercentage/100;
                checkTips();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        taxSwitch = (Switch)findViewById(R.id.taxSwitch);
        tipSwitch = (Switch)findViewById(R.id.tipSwitch);

    }

    public void taxSwitch(View view) {
        checkTax();

    }
    private void checkTax(){
        if(taxSwitch.isChecked()){
            intTaxPrice = intInitPrice * 0.1475;
        }
        else intTaxPrice = 0;
        refresh();
    }
    private void refresh(){
        intTotalPrice = intInitPrice + intTaxPrice + intTipsPrice;
        totalPrice.setText(String.format("%.2f$", intTotalPrice));
        taxPrice.setText(String.format("Tax : %.2f$",intTaxPrice));
        tipsPrice.setText(String.format("Tips : %.2f$",intTipsPrice));

    }

    public void tipsChanged(View view) {
        checkTips();
    }
    private void checkTips(){
        if (tipSwitch.isChecked()) {
            radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            if (radioButton.getId() == R.id.Tip1) {
                tipPercentage = 0.15;
                otherTip.setVisibility(View.GONE);

            } else if (radioButton.getId() == R.id.Tip2) {
                tipPercentage = 0.20;
                otherTip.setVisibility(View.GONE);
            } else if (radioButton.getId() == R.id.Tip3) {
                tipPercentage = 0.25;
                otherTip.setVisibility(View.GONE);
            } else if (radioButton.getId() == R.id.Tip4) {
                tipPercentage = persPercentage;
                otherTip.setVisibility(View.VISIBLE);
            }
        }
        else tipPercentage = 0 ;

        intTipsPrice = intInitPrice * tipPercentage;
        refresh();
    }
}
