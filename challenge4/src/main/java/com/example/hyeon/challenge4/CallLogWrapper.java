package com.example.hyeon.challenge4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CallLogWrapper {
    public ImageView personImageView;
    public TextView nameView;
    public TextView dateView;
    public ImageView dialerImageView;
    public CallLogWrapper(View root){
        personImageView=(ImageView)root.findViewById(R.id.main_item_person);
        nameView=(TextView)root.findViewById(R.id.main_item_name);
        dateView=(TextView)root.findViewById(R.id.main_item_date);
        dialerImageView=(ImageView)root.findViewById(R.id.main_item_dialer);
    }
}
