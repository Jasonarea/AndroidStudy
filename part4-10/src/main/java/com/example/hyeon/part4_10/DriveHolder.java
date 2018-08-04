package com.example.hyeon.part4_10;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class DriveHolder {
    public ImageView typeImageView;
    public TextView titleView;
    public TextView dateView;
    public ImageView menuImageView;

    public DriveHolder(View root) {
        typeImageView = (ImageView)root.findViewById(R.id.custom_item_type_image);
        titleView = (TextView)root.findViewById(R.id.custom_item_title);
        dateView = (TextView)root.findViewById(R.id.custom_item_date);
        menuImageView = (ImageView)root.findViewById(R.id.custom_item_menu);
    }
}
