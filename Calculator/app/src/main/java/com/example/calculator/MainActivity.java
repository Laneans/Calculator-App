package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Calculator calc;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] digitIds = new int[] {R.id.zero,
                R.id.one,
                R.id.two,
                R.id.three,
                R.id.four,
                R.id.five,
                R.id.six,
                R.id.seven,
                R.id.eight,
                R.id.nine
        };

        int[] operationIds = new int[] {R.id.division,
                R.id.multiply,
                R.id.minus,
                R.id.plus,
                R.id.clear_text,
                R.id.equal
        };
        text = findViewById(R.id.textView);
        calc = new Calculator();

        View.OnClickListener digitButtonCLickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.onDigitPressed(v.getId());
                text.setText(calc.getText());
            }
        };

        View.OnClickListener operationButtonCLickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    calc.onActionPressed(v.getId());
                    text.setText(calc.getText());
                } catch (ArithmeticException e) {
                    Toast toast = Toast.makeText(MainActivity.this, "Error: Division by zero!",Toast.LENGTH_SHORT);
                    toast.show();
                    calc.reset();
                    text.setText(calc.getText());
                    text.setText("0");
                }
            }
        };

        for (int digitId : digitIds) {
            findViewById(digitId).setOnClickListener(digitButtonCLickListener);
        }
        for (int operationId : operationIds) {
            findViewById(operationId).setOnClickListener(operationButtonCLickListener);
        }

        findViewById(R.id.clear_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.reset();
                text.setText(calc.getText());
                text.setText("0");
            }
        });
    }
}