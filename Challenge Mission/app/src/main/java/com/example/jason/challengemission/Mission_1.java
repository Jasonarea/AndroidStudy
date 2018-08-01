package com.example.jason.challengemission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Mission_1 extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstancdState) {
        super.onCreate(savedInstancdState);
        setContentView(R.layout.activity_mission1);

        Button btn = (Button)findViewById(R.id.messenger_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "OK button Click!", Toast.LENGTH_SHORT).show();
    }
}
