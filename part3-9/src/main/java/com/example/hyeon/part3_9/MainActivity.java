package com.example.hyeon.part3_9;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText contentView;
    Button btn;

    //퍼미션 부여 여부
    boolean fileReadPermission;
    boolean fileWritePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentView = (EditText) findViewById(R.id.content);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);

        //permission 체크
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            fileReadPermission = true;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            fileWritePermission = true;
        }
        //permission부여 안될 경우 Permission 요청
        if (!fileReadPermission || !fileWritePermission) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }
    //Permission 부여 요청 결과 확인
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length>0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                fileReadPermission= true;
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED)
                fileWritePermission = true;
        }
    }

    @Override
    public void onClick(View v) {
        String content = contentView.getText().toString();
        //퍼미션이 부여되어있다면
        if(fileReadPermission && fileWritePermission) {
            FileWriter writer;
            try {
                //외부 저장곤간 root하위에 myApp이라는 폴더 경로 획득
                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApp";
                File dir = new File(dirPath);
                //폴더가 없다면 새로 만들어준다.
                if(!dir.exists()) {
                    dir.mkdir();
                }
                //myApp 폴더 밑에 myFile.txt 파일 지정
                File file = new File(dir + "/myFile.txt");
                //파일이 없다면 새로 만들어준다.
                if(!file.exists()) {
                    file.createNewFile();
                }
                //file write
                writer = new FileWriter(file, true);
                writer.write(content);
                writer.flush();
                writer.close();

                //결과 확인을 위한 FileReadActivity 실행 클래스
                Intent intent = new Intent(this, ReadFileActivity.class);
                //FileReadActivity로 화면 전환
                startActivity(intent);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }else {
            showToast("permission이 부여 안되어 기능을 실행할 수 없습니다.");
        }
    }
    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
