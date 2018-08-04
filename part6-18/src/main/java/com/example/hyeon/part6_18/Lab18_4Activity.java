package com.example.hyeon.part6_18;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Lab18_4Activity extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    CoordinatorLayout coordinatorLayout;
    private BottomSheetBehavior<View> persistentBottomSheet;
    private BottomSheetDialog modalBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab18_4);

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.lab4_coordinator);
        btn = (Button)findViewById(R.id.lab4_button);
        btn.setOnClickListener(this);

        initPersistentBottomSheet();
    }

    public void onClick(View v) {
        createDialog();
    }

    private void createDialog() {
        List<DataVO> list = new ArrayList<>();

        DataVO vo = new DataVO();
        vo.title = "Keep";
        vo.image = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_1, null);
        list.add(vo);

        vo = new DataVO();
        vo.title = "Inbox";
        vo.image = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_2, null);
        list.add(vo);

        vo = new DataVO();
        vo.title = "Messanger";
        vo.image = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_3, null);

        vo = new DataVO();
        vo.title = "Google+";
        vo.image = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_4, null);

        Lab4RecyclerViewAdapter adapter = new Lab4RecyclerViewAdapter(list);
        View view = getLayoutInflater().inflate(R.layout.lab4_modal_sheet, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.lab4_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        modalBottomSheet = new BottomSheetDialog(this);
        modalBottomSheet.setContentView(view);
        modalBottomSheet.show();
    }

    private void initPersistentBottomSheet() {
        View bottomSheet = coordinatorLayout.findViewById(R.id.lab4_bottom_sheet);
        persistentBottomSheet = BottomSheetBehavior.from(bottomSheet);
    }
}
