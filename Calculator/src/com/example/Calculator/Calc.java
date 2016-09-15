package com.example.Calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by usman on 09/01/15.
 */
public class Calc extends Activity implements OnClickListener {

    Button one, two, three, four, five, six, seven, eight, nine, zero,
            add, sub, mul, div, cancel, equal, backspace, dot;
    EditText disp;

    int op1;
    int op2;
    double op3;
    double op4;
    String optr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);

        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        cancel = (Button) findViewById(R.id.cancel);
        equal = (Button) findViewById(R.id.equal);
        backspace = (Button) findViewById(R.id.backspace);
        dot = (Button) findViewById(R.id.dot);

        disp = (EditText) findViewById(R.id.display);

        try {
            one.setOnClickListener(this);
            two.setOnClickListener(this);
            three.setOnClickListener(this);
            four.setOnClickListener(this);
            five.setOnClickListener(this);
            six.setOnClickListener(this);
            seven.setOnClickListener(this);
            eight.setOnClickListener(this);
            nine.setOnClickListener(this);
            zero.setOnClickListener(this);
            cancel.setOnClickListener(this);
            add.setOnClickListener(this);
            sub.setOnClickListener(this);
            mul.setOnClickListener(this);
            div.setOnClickListener(this);
            equal.setOnClickListener(this);
            backspace.setOnClickListener(this);
            dot.setOnClickListener(this);

        } catch (Exception e) {

        }
    }

    public void operation() {
        if (optr.equals("+")) {
            op4 = Double.parseDouble(disp.getText().toString());
            disp.setText("");
            op3 = op3 + op4;
            disp.setText("Result : " + Double.toString(op3));
        } else if (optr.equals("-")) {
            op4 = Integer.parseInt(disp.getText().toString());
            disp.setText("");
            op3 = op3 - op4;
            disp.setText("Result : " + Double.toString(op3));
        } else if (optr.equals("*")) {
            op4 = Double.parseDouble(disp.getText().toString());
            disp.setText("");
            op3 = op3 * op4;
            disp.setText("Result : " + Double.toString(op3));
        } else if (optr.equals("/")) {
            op4 = Double.parseDouble(disp.getText().toString());
            disp.setText("");
            op3 = op3 / op4;
            disp.setText("Result : " + Double.toString(op3));
        }
    }

    @Override
    public void onClick(View v) {
        Editable str = disp.getText();

        switch (v.getId()) {
            case R.id.one:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(one.getText());
                disp.setText(str);
                break;
            case R.id.two:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(two.getText());
                disp.setText(str);
                break;
            case R.id.three:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(three.getText());
                disp.setText(str);
                break;
            case R.id.four:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(four.getText());
                disp.setText(str);
                break;
            case R.id.five:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(five.getText());
                disp.setText(str);
                break;
            case R.id.six:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(six.getText());
                disp.setText(str);
                break;
            case R.id.seven:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(seven.getText());
                disp.setText(str);
                break;
            case R.id.eight:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(eight.getText());
                disp.setText(str);
                break;
            case R.id.nine:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(nine.getText());
                disp.setText(str);
                break;
            case R.id.zero:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str = str.append(zero.getText());
                disp.setText(str);
                break;

            case R.id.cancel:
                op1 = 0;
                op2 = 0;
                op3 = 0;
                op4 = 0;
                disp.setText("");

            case R.id.add:
                optr = "+";
                if(op3 == 0){
                    op3 = Double.parseDouble(disp.getText().toString());
                    disp.setText("");
                }

                else if(op4 != 0){
                  op4 = 0; disp.setText("");
                    }

                else{
                        op4 = Double.parseDouble(disp.getText().toString());
                        disp.setText("");
                        op3 = op3 + op4;
                        disp.setText("Result : "+ Double.toString(op3));
            }
                break;

            case R.id.sub:
                optr = "-";
                if(op3 == 0){
                    op3 = Double.parseDouble(disp.getText().toString());
                    disp.setText("");
                } else if(op4 != 0){
                    op4 = 0; disp.setText("");
                } else{
                    op4 = Double.parseDouble(disp.getText().toString());
                    disp.setText("");
                    op3 = op3 - op4;
                    disp.setText("Result : " + Double.toString(op3));
                }
                break;

            case R.id.mul:
                optr = "*";
                if(op3 == 0){
                    op3 = Double.parseDouble(disp.getText().toString());
                    disp.setText("");
                }
                else if(op4 != 0){
                    op4 = 0;
                    disp.setText("");
                }
                else{
                    op4 = Double.parseDouble(disp.getText().toString());
                    disp.setText("");
                    op3 = op3 * op4;
                    disp.setText("Result : " + Double.toString(op3));
                }
                break;

            case R.id.div:
                optr = "/";
                if(op3 == 0){
                    op3 = Double.parseDouble(disp.getText().toString());
                    disp.setText("");
                }
                else if(op4 != 0){
                    op4 = 0;
                    disp.setText("");
                }
                else{
                    op4 = Double.parseDouble(disp.getText().toString());
                    disp.setText("");
                    op3 = op3 / op4;
                    disp.setText("Result : " + Double.toString(op3));
                }
                break;

            case R.id.equal:
                if(!optr.equals(null)){
                    if(op4 != 0){
                        if(optr.equals("+")){
                            disp.setText("");
                            disp.setText("Result : " + Double.toString(op3));
                        } else if(optr.equals("-")){
                            disp.setText("");
                            disp.setText("Result : " + Double.toString(op3));
                        }
                        else if(optr.equals("*")){
                            disp.setText("");
                            disp.setText("Result : " + Double.toString(op3));
                        } else if(optr.equals("/")){
                            disp.setText("");
                            disp.setText("Result : " + Double.toString(op3));
                        }
                    } else{ operation();
                    }
                }
                break;

                        case R.id.dot: if(op4 != 0){
                            op4 = 0; disp.setText("");
                        }
                            str = str.append(dot.getText());
                            disp.setText(str);
                            break;

                        case R.id.backspace:
                            if(op4 != 0){
                                op4 = 0;
                                disp.setText("");
                            }
                            String textInBox = disp.getText().toString();
                            if(textInBox.length() > 0) {

                                String newText = textInBox.substring(0, textInBox.length()-1);
                                disp.setText(newText);
                            }
                            break;
        }
    }
}