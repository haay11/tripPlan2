package com.firstapp.hytripplan.main;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firstapp.hytripplan.BackPressCloseHandler;
import com.firstapp.hytripplan.DB.PlanDataHelper;
import com.firstapp.hytripplan.R;
import com.firstapp.hytripplan.make.MakePlanActivity;
import com.firstapp.hytripplan.search.SearchSpaceActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainFragment extends Fragment implements MainActivity.OnBackPressedListener{

    //객체 선언
    private Intent intent;
    private TextView searchTv;
    private ImageView addImage;
    private TextView text1;
    private ListView listView,listView_;
    private Cursor cursor;
    private Adapter adapter;

    //DB
    private PlanDataHelper planData;
    private SQLiteDatabase db;



    @Nullable
    @Override
    public View onCreateView (@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        setUpView(view);

        return view;

    }


    private void setUpView(View view) {
        text1 = view.findViewById(R.id.text_1);
        listView = view.findViewById(R.id.list_view);




        //일정 추가
        addImage = view.findViewById(R.id.add_image);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), MakePlanActivity.class);
                startActivity(intent);
            }
        });

        //장소 검색
        searchTv = view.findViewById(R.id.tv_edit);
        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), SearchSpaceActivity.class);
                startActivity(intent);
            }
        });
    }



    public static MainFragment newInstance(){
        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        MainActivity activity = (MainActivity)getActivity();
        activity.setOnBackPressedListener(null);

        // Activity 에서도 뭔가 처리하고 싶은 내용이 있다면 하단 문장처럼 호출
        // activity.onBackPressed();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((MainActivity)context).setOnBackPressedListener(this);
    }
}

