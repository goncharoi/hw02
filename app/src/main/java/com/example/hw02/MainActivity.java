package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static com.example.hw02.Calculator.ERROR;

public class MainActivity extends AppCompatActivity {

    private static class Key {
        public static final String CALCULATOR = "Calculator";
        public static final String NUMBER = "Number";
    }

    private TextView moNumber;
    private Calculator moCalc;
    private boolean mvNoInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moNumber = findViewById(R.id.number);
        moCalc = new Calculator();

        findViewById(R.id.button_clear).setOnClickListener(v -> {
            moCalc = new Calculator();
            moNumber.setText("");
        });

        findViewById(R.id.button_delete).setOnClickListener(v -> moNumber.setText(""));

        //Вычисления
        findViewById(R.id.button_plus).setOnClickListener(v -> {
            moNumber.setText(moCalc.onActionBtn(Calculator.Action.PLUS,getFloatFromScr()));
            mvNoInput = true;
        });
        findViewById(R.id.button_minus).setOnClickListener(v -> {
            moNumber.setText(moCalc.onActionBtn(Calculator.Action.MINUS,getFloatFromScr()));
            mvNoInput = true;
        });
        findViewById(R.id.button_multiply).setOnClickListener(v -> {
            moNumber.setText(moCalc.onActionBtn(Calculator.Action.MULTIPLY,getFloatFromScr()));
            mvNoInput = true;
        });
        findViewById(R.id.button_divide).setOnClickListener(v -> {
            moNumber.setText(moCalc.onActionBtn(Calculator.Action.DIVIDE,getFloatFromScr()));
            mvNoInput = true;
        });
        findViewById(R.id.button_equals).setOnClickListener(v -> {
            moNumber.setText(moCalc.onActionBtn(Calculator.Action.EQUALS,getFloatFromScr()));
            mvNoInput = true;
        });

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
        if (moNumber.getText().toString().equals(ERROR) || mvNoInput) {
            moNumber.setText("");
        }
        if (ivStr.equals(getString(R.string._dot)) && moNumber.getText().toString().contains(getString(R.string._dot))){
            return;
        }
        moNumber.setText(moNumber.getText().toString() + ivStr);
        mvNoInput = false;
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
        instanceState.putSerializable(Key.CALCULATOR, moCalc);
        instanceState.putString(Key.NUMBER, moNumber.getText().toString());
    }

    //Восстановление данных
    @Override
    protected void onRestoreInstanceState(Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        moCalc = (Calculator) instanceState.getSerializable(Key.CALCULATOR);
        moNumber.setText(instanceState.getString(Key.NUMBER));
    }

}