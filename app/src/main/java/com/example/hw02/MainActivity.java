package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView moNumber;
    private Calculator moCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moNumber = findViewById(R.id.number);
        moCalc = new Calculator();

        findViewById(R.id.button_clr).setOnClickListener(v -> {
            moCalc = new Calculator();
            moNumber.setText("");
        });

        findViewById(R.id.button_del).setOnClickListener(v -> moNumber.setText(""));

        //Вычисления
        findViewById(R.id.button_pls).setOnClickListener(v -> moNumber.setText(moCalc.onActionBtn(Action.PLS,getFloatFromScr())));
        findViewById(R.id.button_mns).setOnClickListener(v -> moNumber.setText(moCalc.onActionBtn(Action.MNS,getFloatFromScr())));
        findViewById(R.id.button_mlt).setOnClickListener(v -> moNumber.setText(moCalc.onActionBtn(Action.MLT,getFloatFromScr())));
        findViewById(R.id.button_div).setOnClickListener(v -> moNumber.setText(moCalc.onActionBtn(Action.DIV,getFloatFromScr())));
        findViewById(R.id.button_run).setOnClickListener(v -> moNumber.setText(moCalc.onActionBtn(null,getFloatFromScr())));

        //Набор
        findViewById(R.id.button_0).setOnClickListener(v -> addCharToNumber(getString(R.string._0)));
        findViewById(R.id.button_1).setOnClickListener(v -> addCharToNumber(getString(R.string._1)));
        findViewById(R.id.button_2).setOnClickListener(v -> addCharToNumber(getString(R.string._2)));
        findViewById(R.id.button_3).setOnClickListener(v -> addCharToNumber(getString(R.string._3)));
        findViewById(R.id.button_4).setOnClickListener(v -> addCharToNumber(getString(R.string._4)));
        findViewById(R.id.button_5).setOnClickListener(v -> addCharToNumber(getString(R.string._5)));
        findViewById(R.id.button_6).setOnClickListener(v -> addCharToNumber(getString(R.string._6)));
        findViewById(R.id.button_7).setOnClickListener(v -> addCharToNumber(getString(R.string._7)));
        findViewById(R.id.button_8).setOnClickListener(v -> addCharToNumber(getString(R.string._8)));
        findViewById(R.id.button_9).setOnClickListener(v -> addCharToNumber(getString(R.string._9)));
        findViewById(R.id.button_dot).setOnClickListener(v -> addCharToNumber(getString(R.string._dot)));

    }

    private void addCharToNumber(String ivStr){
        moNumber.setText(moNumber.getText().toString() + ivStr);
    }

    private Float getFloatFromScr(){
        try {
            return  Float.valueOf(moNumber.getText().toString());
        } catch (NumberFormatException loEx) {
            return  null;
        }
    }

    // Сохранение данных
    @Override
    public void onSaveInstanceState(Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putSerializable(Key.CALC.toString(), moCalc);
        instanceState.putString(Key.NUMB.toString(), moNumber.getText().toString());
    }

    //Восстановление данных
    @Override
    protected void onRestoreInstanceState(Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        moCalc = (Calculator) instanceState.getSerializable(Key.CALC.toString());
        moNumber.setText(instanceState.getString(Key.NUMB.toString()));
    }

}