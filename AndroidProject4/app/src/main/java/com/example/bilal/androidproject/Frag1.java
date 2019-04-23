package com.example.bilal.androidproject;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag1 extends Fragment {
    private TextInputEditText initPrice;
    private TextView totalPrice;
    private TextView taxPrice;
    private TextView tipsPrice;
    private Switch taxSwitch;
    private Switch tipSwitch;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextInputEditText otherTip;
    private View view;

    private double intInitPrice, intTaxPrice, intTipsPrice, intTotalPrice, tipPercentage, persPercentage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag1_layout, container, false);
        taxSwitch = (Switch) view.findViewById(R.id.taxSwitch);
        taxSwitch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkTax();
            }
        });
        tipSwitch = (Switch) view.findViewById(R.id.tipSwitch);
        tipSwitch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkTips();
            }
        });
        RadioButton button1 = (RadioButton) view.findViewById(R.id.Tip1);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkTips();
            }
        });
        RadioButton button2 = (RadioButton)view.findViewById(R.id.Tip2);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkTips();
            }
        });
        RadioButton button3 = (RadioButton) view.findViewById(R.id.Tip3);
        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkTips();
            }
        });
        RadioButton button4 = (RadioButton) view.findViewById(R.id.Tip4);
        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkTips();
            }
        });

        totalPrice = (TextView) view.findViewById(R.id.totalValue);
        taxPrice = (TextView) view.findViewById(R.id.taxText);
        tipsPrice = (TextView) view.findViewById(R.id.tipsText);
        initPrice = (TextInputEditText) view.findViewById(R.id.initPrice);
        otherTip = (TextInputEditText) view.findViewById(R.id.otherTip);
        persPercentage = 0;
        tipPercentage = 0.15;
        initPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    intInitPrice = Double.parseDouble(s.toString());
                    checkTax();
                    checkTips();
                    refresh();
                }
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
                if (!s.toString().isEmpty()){
                    persPercentage = Double.parseDouble(s.toString());
                    persPercentage = persPercentage / 100;
                    checkTips();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        return view;
    }



    private void checkTax() {
        if (taxSwitch.isChecked()) {
            intTaxPrice = intInitPrice * 0.1475;
        } else intTaxPrice = 0;
        refresh();
    }

    private void refresh() {
        intTotalPrice = intInitPrice + intTaxPrice + intTipsPrice;
        totalPrice.setText(String.format("%.2f$", intTotalPrice));
        taxPrice.setText(String.format("Tax : %.2f$", intTaxPrice));
        tipsPrice.setText(String.format("Tips : %.2f$", intTipsPrice));

    }



    private void checkTips() {
        if (tipSwitch.isChecked()) {
            radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
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
        } else tipPercentage = 0;

        intTipsPrice = intInitPrice * tipPercentage;
        refresh();
    }
}
