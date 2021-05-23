package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Float mvArgument1;
    private Action mvAction;
    private TextView moNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loBtnClr = findViewById(R.id.button_clr);
        Button loBtnDel = findViewById(R.id.button_del);

        Button loBtnRun = findViewById(R.id.button_run);
        Button loBtnPls = findViewById(R.id.button_pls);
        Button loBtnMns = findViewById(R.id.button_mns);
        Button loBtnMlt = findViewById(R.id.button_mlt);
        Button loBtnDiv = findViewById(R.id.button_div);

        Button loBtn0 = findViewById(R.id.button_0);
        Button loBtn1 = findViewById(R.id.button_1);
        Button loBtn2 = findViewById(R.id.button_2);
        Button loBtn3 = findViewById(R.id.button_3);
        Button loBtn4 = findViewById(R.id.button_4);
        Button loBtn5 = findViewById(R.id.button_5);
        Button loBtn6 = findViewById(R.id.button_6);
        Button loBtn7 = findViewById(R.id.button_7);
        Button loBtn8 = findViewById(R.id.button_8);
        Button loBtn9 = findViewById(R.id.button_9);
        Button loBtnDot = findViewById(R.id.button_dot);

        moNumber = findViewById(R.id.number);

        loBtnClr.setOnClickListener(v -> {
            mvArgument1 = null;
            mvAction = null;
        });

        loBtnDel.setOnClickListener(v -> moNumber.setText(""));

        loBtnPls.setOnClickListener(v -> onActionBtn(Action.PLS));
        loBtnMns.setOnClickListener(v -> onActionBtn(Action.MNS));
        loBtnMlt.setOnClickListener(v -> onActionBtn(Action.MLT));
        loBtnDiv.setOnClickListener(v -> onActionBtn(Action.DIV));
        loBtnRun.setOnClickListener(v -> onActionBtn(null));

        loBtn0.setOnClickListener(v -> onNumBtn("0"));
        loBtn1.setOnClickListener(v -> onNumBtn("1"));
        loBtn2.setOnClickListener(v -> onNumBtn("2"));
        loBtn3.setOnClickListener(v -> onNumBtn("3"));
        loBtn4.setOnClickListener(v -> onNumBtn("4"));
        loBtn5.setOnClickListener(v -> onNumBtn("5"));
        loBtn6.setOnClickListener(v -> onNumBtn("6"));
        loBtn7.setOnClickListener(v -> onNumBtn("7"));
        loBtn8.setOnClickListener(v -> onNumBtn("8"));
        loBtn9.setOnClickListener(v -> onNumBtn("9"));
        loBtnDot.setOnClickListener(v -> onNumBtn("."));

    }

    protected Float run(Float ivArg1, Float ivArg2) {
        if (mvAction != null && ivArg1 != null && ivArg2 != null) {
            switch (mvAction) {
                case PLS:
                    return ivArg1 + ivArg2;
                case MLT:
                    return ivArg1 * ivArg2;
                case MNS:
                    return ivArg1 - ivArg2;
                case DIV:
                    return ivArg2 == 0f ? null : (ivArg1 / ivArg2);
            }
        }
        return null;
    }

    protected void onActionBtn(Action ivAct){
        if (mvAction != null) {
            mvArgument1 = run(mvArgument1, Float.valueOf(moNumber.getText().toString()));
        } else
            mvArgument1 = Float.valueOf(moNumber.getText().toString());

        if (mvArgument1 != null)
            moNumber.setText(mvArgument1.toString());
        else
            moNumber.setText("Error");

        mvAction = ivAct;
    }

    protected void onNumBtn(String ivStr){
        moNumber.setText(moNumber.getText().toString() + ivStr);
    }
}