package com.example.hyeon.part2_6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    TextView bellTextView;
    TextView labelTextVeiw;
    CheckBox repeatCheckView;
    CheckBox vibrateCheckView;
    Switch switchView;

    float initX;
    long initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //id 값 받기
        bellTextView = (TextView)findViewById(R.id.bell_name);
        labelTextVeiw=(TextView)findViewById(R.id.label);
        repeatCheckView=(CheckBox)findViewById(R.id.repeatCheck);
        vibrateCheckView=(CheckBox)findViewById(R.id.vibrate);
        switchView=(Switch)findViewById(R.id.onOff);
        //onClickListener event 등록하기
        bellTextView.setOnClickListener(this);
        labelTextVeiw.setOnClickListener(this);
        //onCheckedChangedListener event 등록하기
        repeatCheckView.setOnCheckedChangeListener(this);
        vibrateCheckView.setOnCheckedChangeListener(this);
        switchView.setOnCheckedChangeListener(this);
    }


    // Toast 출력해주는 function
    private void showToast(String message){
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // button 클릭 이벤트 구현
    @Override
    public void onClick(View v) {
        if(v==bellTextView){
            showToast("bell text click event..");
        }else if(v==labelTextVeiw){
            showToast("label text click event...");
        }
    }
    // checkbox 또는 switchView 체크 변경 이벤트 구현
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView==repeatCheckView){
            showToast("repeat checkbox is "+isChecked);
        }else if(buttonView==vibrateCheckView){
            showToast("vibrate checkbox is "+isChecked);
        }else if(buttonView==switchView){
            showToast("switch is "+isChecked);
        }
    }
    // 터치 이벤트 구현
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            initX = event.getRawX();
        }else if(event.getAction() == MotionEvent.ACTION_UP) {
            float diffX = initX-event.getRawX();
            if(diffX<30) {
                showToast("왼쪽으로 화면을 밀었습니다.");
            }else if(diffX<-30) {
                showToast("오른쪽으로 화면을 밀었습니다.");
            }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 4000){
                showToast("종료하려면 한번 더 누르세요.");
                initTime=System.currentTimeMillis();
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}