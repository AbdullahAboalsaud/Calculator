package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button num0;
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;

    Button ac;
    Button del;
    Button div;
    Button times;
    Button min;
    Button equal;
    Button plus;
    Button point;

    Button off;
    TextView screen;

    TextView screen2;
    Button on;

    // additional variables
    double firstNum;
    String operation;

    public void initView() {
        num0 = findViewById(R.id.num0);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);

        ac = findViewById(R.id.ac);
        del = findViewById(R.id.del);
        div = findViewById(R.id.div);
        times = findViewById(R.id.times);
        min = findViewById(R.id.min);
        equal = findViewById(R.id.equal);
        plus = findViewById(R.id.plus);
        point = findViewById(R.id.point);

        off = findViewById(R.id.off);
        screen = findViewById(R.id.screen);
        screen2 =findViewById(R.id.screen2);
        on = findViewById(R.id.on);

    }

    String operationString = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClick();

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for (Button b : nums) {
            b.setOnClickListener(view -> {
                if (!screen.getText().toString().equals("0")) {
                    screen.setText(screen.getText().toString() + b.getText().toString());

                } else {
                    screen.setText(b.getText().toString());  //1
                    screen2.setText(screen2.getText().toString() + b.getText().toString());
                }

            });

        }

        ArrayList<Button> opers = new ArrayList<>();
        opers.add(plus);
        opers.add(min);
        opers.add(times);
        opers.add(div);
        for (Button b : opers) {
            b.setOnClickListener(view -> {
                firstNum = Double.parseDouble(screen.getText().toString());
                operation = b.getText().toString();


                screen2.setText(screen.getText().toString()+b.getText().toString());
                screen.setText("0");

                screen2.setVisibility(View.VISIBLE);
                screen.setVisibility(View.INVISIBLE);
            });
        }

        ac.setOnClickListener(view -> {
            firstNum = 0;
            screen.setText("0");
        });

        del.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if (num.length() > 1) {
                screen.setText(num.substring(0, num.length() - 1));
            } else if (num.length() == 1 && !num.equals("0")) {
                screen.setText("0");
            }
        });

        point.setOnClickListener(view -> {
            if (!screen.getText().toString().contains(".")) {
                screen.setText(screen.getText().toString() + ".");
            }
        });

        equal.setOnClickListener(view -> {
            double secondNum = Double.parseDouble(screen.getText().toString());

            double result;
            switch (operation) {
                case "/":
                    if (secondNum == 0) {
                        screen.setText("Math error");
                        screen.setVisibility(View.VISIBLE);
                        screen2.setVisibility(View.INVISIBLE);

                        Toast.makeText(this, "cant divide by zero", Toast.LENGTH_LONG).show();
                        return;
                    }
                    result = firstNum / secondNum;
                    break;
                case "x":
                    result = firstNum * secondNum;
                    break;
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                default:
                    result = firstNum + secondNum;
            }

           screen.setText(String.valueOf(result));
            firstNum = result;

            screen.setVisibility(View.VISIBLE);
            screen2.setVisibility(View.INVISIBLE);
        });


    }


    public void onClick() {
        off.setOnClickListener(view -> screen.setVisibility(View.GONE));
        on.setOnClickListener(view -> {
            screen.setVisibility(View.VISIBLE);
            screen.setText("0");
        });

    }


}