package com.example.hyeon.androidstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button trueBtn;
    TextView targetTextView;
    Button falseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View 객체 획득
        trueBtn = (Button)findViewById(R.id.btn_visible_true);
        targetTextView = (TextView)findViewById(R.id.text_visible_target);
        falseBtn = (Button)findViewById(R.id.btn_visible_false);

        //Button이벤트
        trueBtn.setOnClickListener(this);
        falseBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v == trueBtn) {
            targetTextView.setVisibility(View.VISIBLE);
        }
        else if(v == falseBtn) {
            targetTextView.setVisibility(View.INVISIBLE);
        }
    }
}