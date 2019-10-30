package com.firstapp.hytripplan.make;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firstapp.hytripplan.R;
import com.firstapp.hytripplan.search.SearchSpaceActivity;

public class AddLikeSpace extends AppCompatActivity {

    //객체 선언
    private TextView add_likeSpaceName, addLikeSpaceArr, addSpaceText1;
    private ImageView addSpaceImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_like_space);
        addLikeSpaceArr = findViewById(R.id.add_like_space_arr);
        add_likeSpaceName = findViewById(R.id.add_like_space_name);
        addSpaceText1 = findViewById(R.id.add_space_text1);
        addSpaceImg = findViewById(R.id.space_img);


        addSpaceImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLikeSpace.this, SearchSpaceActivity.class);
                startActivity(intent);
            }
        });

    }


}
