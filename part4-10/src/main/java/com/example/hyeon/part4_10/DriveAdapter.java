package com.example.hyeon.part4_10;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DriveAdapter extends ArrayAdapter<DriveVO> {
    Context context;
    int resId;
    ArrayList<DriveVO> datas;

    public DriveAdapter(Context context, int resId, ArrayList<DriveVO> datas) {
        super(context, resId);
        this.context = context;
        this.resId = resId; // xml
        this.datas = datas; // list data ArrayList
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {   // 처음 뷰가 호출될때만
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);        // xml 한번 호출
            DriveHolder holder = new DriveHolder(convertView);  //findviewId 호출
            convertView.setTag(holder);
        }

        DriveHolder holder = (DriveHolder)convertView.getTag(); // 그다음부터는 getTag이용

        ImageView typeImageView = holder.typeImageView; // id 담아놓은 driveHolder에서 id호출
        TextView titleView = holder.titleView;
        TextView dateView = holder.dateView;
        ImageView menuImageView = holder.menuImageView;

        final DriveVO vo = datas.get(position); // driveVO에 저장된 데이터 get

        titleView.setText(vo.title);
        dateView.setText(vo.date);

        if(vo.type.equals("doc")) {
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_type_doc, null));
        }
        else if(vo.type.equals("file")){
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_type_file, null));
        }
        else if(vo.type.equals("img")) {
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_type_image, null));
        }

        menuImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(context, vo.title + " menu click", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return convertView;
    }
}
