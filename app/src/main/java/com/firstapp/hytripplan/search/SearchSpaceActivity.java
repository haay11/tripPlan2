package com.firstapp.hytripplan.search;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firstapp.hytripplan.R;

import java.util.ArrayList;


public class SearchSpaceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //객체 선언
    private ListView addSearchList;
    private EditText addSearchEdit;
    private ArrayAdapter<String> adapter;
    private Intent intent;
    private String keyword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_search_space);

        addSearchEdit = findViewById(R.id.add_search_edit);
        addSearchList = findViewById(R.id.add_search_list);

        addSearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {

                    keyword = v.getText().toString();
                    searchByKeyword(keyword);

                    return false;
                }
                return true;
            }
        });

    }

    private void searchByKeyword(String keyword) {
        intent = new Intent(SearchSpaceActivity.this, SpaceActivity.class);
        intent.putExtra("key_name", keyword);
        startActivityForResult(intent, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // SearchActivity 에서 요청할 때 보낸 요청 코드 (3000)
                case 3000:
                    addSearchEdit.setText(data.getStringExtra("key_name"));
                    break;
            }
        }
    }
        @Override
        public void onItemClick (AdapterView < ? > adapterView, View view,int i, long l){
        }
}

