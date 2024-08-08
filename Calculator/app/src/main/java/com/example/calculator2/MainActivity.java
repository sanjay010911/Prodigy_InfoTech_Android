package com.example.calculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private String op = "", opd1 = "", opd2 = "";
    private TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.txt1);


    }


    public void on1(View view) {
        MaterialButton b= (MaterialButton) view;
        String buttontext=b.getText().toString();
        if(buttontext.equals("+")||buttontext.equals("-")||buttontext.equals("*")||buttontext.equals("/")||buttontext.equals("%")) {
            opd1 = t1.getText().toString();
            t1.setText("");
            op=buttontext;
        }
        else
        {
            String s=t1.getText()+buttontext;
            t1.setText(t1.getText()+buttontext);
        }
    }

    public void on2(View view)
    {
        t1.setText("");
    }
    public void equal(View view) {
        opd2 = t1.getText().toString();
        Log.d("Calculator", "Operand 1: " + opd1 + ", Operand 2: " + opd2 + ", Operator: " + op);

        if (opd1.isEmpty() || opd2.isEmpty()) {
            Log.e("Calculator", "One of the operands is empty");
            t1.setText("Error");
            return;
        }

        try {
            int a = Integer.parseInt(opd1);
            int b = Integer.parseInt(opd2);
            int result = 0;

            if (op.equals("+")) {
                result = a + b;
            } else if (op.equals("-")) {
                result = a - b;
            } else if (op.equals("*")) {
                result = a * b;
            } else if (op.equals("/")) {
                if (b == 0) {
                    Log.e("Calculator", "Division by zero");
                    t1.setText("Error");
                    return;
                }
                result = a / b;
            } else if (op.equals("%")) {
                result = a % b;
            } else {
                Log.e("Calculator", "Unknown operator: " + op);
                t1.setText("Error");
                return;
            }
            t1.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            Log.e("Calculator", "Number format exception: " + e.getMessage());
            t1.setText("Error");
        } catch (ArithmeticException e) {
            Log.e("Calculator", "Arithmetic exception: " + e.getMessage());
            t1.setText("Error");
        }

        // Reset after calculation
        opd1 = "";
        opd2 = "";
        op = "";
    }
}
