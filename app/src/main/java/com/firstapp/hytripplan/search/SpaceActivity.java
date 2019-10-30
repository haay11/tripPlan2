package com.firstapp.hytripplan.search;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.firstapp.hytripplan.make.AddLikeSpace;
import com.firstapp.hytripplan.R;
import com.firstapp.hytripplan.api.TripDataAPI;
import com.firstapp.hytripplan.api.vo.tripdata.TripData;
import com.firstapp.hytripplan.api.vo.tripimage.Item;
import com.firstapp.hytripplan.api.vo.tripimage.TripImage;
import com.firstapp.hytripplan.make.MakePlanActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SpaceActivity extends AppCompatActivity {
    private TextView spaceExplain, spaceName;
    private ImageView shareImageBtn, reviewImageBtn, addListImageBtn, heartImageBtn, spaceImg, infoImageBtn;
    private String name, changeColor;
    private int changeBtn1 = 1, changeBtn2 = 1;
    private String keyword;
    final Handler hd = new Handler();
    private String htmlPageUrl, htmlImgUrl, htmlImgFormat;



    private final static String TAG = SpaceActivity.class.getName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.space);
        spaceExplain = findViewById(R.id.space_explain);
        spaceName = findViewById(R.id.space_name);
        shareImageBtn = findViewById(R.id.share_image_bt);
        reviewImageBtn = findViewById(R.id.review_image_bt);
        addListImageBtn = findViewById(R.id.add_list_image_bt);
        heartImageBtn =  findViewById(R.id.heart_image_bt);
        spaceImg = findViewById(R.id.space_img);
        infoImageBtn = findViewById(R.id.info_image_bt);


        final Intent intent = getIntent();


        if (intent.hasExtra("key_name")){
            name = intent.getExtras().getString("key_name");
            spaceName.setText(name);

        }


        try {
            htmlImgUrl = "https://ko.wikipedia.org/wiki/"+ URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        htmlImgFormat = "";
        ImgAsysncTask imgAsysncTask = new ImgAsysncTask();
        imgAsysncTask.execute(htmlImgUrl);

        htmlPageUrl = "https://www.google.com/search?q="+name+"&oq="+name+"&aqs=chrome";
        spaceExplain.setMovementMethod(new ScrollingMovementMethod());
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();


        infoImageBtn.setBackgroundColor(getResources().getColor(R.color.windowBackground));

        heartImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (changeBtn1 == 1){
                    heartImageBtn.setImageResource(R.drawable.ic_iconfinder_heart1);
                    Toast.makeText(getApplicationContext(), "찜 등록 하였습니다!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(SpaceActivity.this, AddLikeSpace.class);
                    intent1.putExtra("key_name", name);
                    startService(intent1);
                    changeBtn1 ++;
                } else if(changeBtn1 == 2){
                    heartImageBtn.setImageResource(R.drawable.ic_iconfinder_heart);
                    Toast.makeText(getApplicationContext(), "찜을 해제하였습니다.", Toast.LENGTH_SHORT).show();
                    changeBtn1 --;
                }
            }
        });

        //retrofit
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/v1/search/blog?query="+name)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        final String serviceKey = "V0DgkqY4kvZUFHnjGQHjABxWeMsOcDgfFIJ14297%2BYBD%2BKzdyE54NCEaTwg%2Ft0WKNpfypVwYjUbM8V6Cnkg9kQ%3D%3D";
        final TripDataAPI api = retrofit.create(TripDataAPI.class);
        final Call<TripData> response = api.searchKeyword("AND"
                ,"TripPlan"
                ,serviceKey
                ,name
                ,"json");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TripData body = response.execute().body();
                    long contentsId = -1;
                    try{
                        for(com.firstapp.hytripplan.api.vo.tripdata.Item item : body.getResponse().getBody().getItems().getItem()){
                            contentsId =item.getContentid();
                            if(contentsId > -1){
                                break;
                            }
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    if(contentsId != -1){
                        final Call<TripImage> response1 = api.detailImage("AND"
                                ,"TripPlan"
                                ,serviceKey
                                , String.valueOf(contentsId)
                                , "Y"
                                , "Y"
                                , "json");


                        String imageUrl = null;
                        try{
                            Response<TripImage> response2 = response1.execute();
                            if(response2.isSuccessful()){
                                for(Item item : response2.body().getResponse().getBody().getItems().getItem()){
                                    imageUrl = item.getOriginimgurl();
                                    if(imageUrl != null){
                                        break;
                                    }
                                }
                            }

                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        if(imageUrl != null){
                            showImage(imageUrl);
//                            spaceImg.setVisibility(View.VISIBLE);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });





        addListImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spaceExplain.setText("현재 준비중 입니다^^");
                if (changeBtn2 == 1){
                    addListImageBtn.setImageResource(R.drawable.ic_icon_add_list1);
                    Toast.makeText(getApplicationContext(), "일정 리스트에 등록 하였습니다!", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(SpaceActivity.this, MakePlanActivity.class);
                    intent2.putExtra("key_name", name);
                    startService(intent2);
                    changeBtn2 ++;
                } else if(changeBtn2 == 2){
                    addListImageBtn.setImageResource(R.drawable.ic_icon_add_list);
                    Toast.makeText(getApplicationContext(), "일정 리스트에서 제거하였습니다.", Toast.LENGTH_SHORT).show();
                    changeBtn2 --;
                }
            }
        });



        infoImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cnt = 0;
                changeColor = "info_image_bt";
                changeColor(changeColor);
                spaceExplain.setMovementMethod(new ScrollingMovementMethod());
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();
                cnt++;
            }
        });

        reviewImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor = "review_image_bt";
                changeColor(changeColor);
                spaceExplain.setText("현재 준비중 입니다^^");


            }
        });


        shareImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor = "share_image_bt";
                changeColor(changeColor);

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");

                String text = "이 곳 어때요?!";
                shareIntent.putExtra(Intent.EXTRA_TEXT,text);
                Intent chooser = Intent.createChooser(shareIntent,"친구에게 공유하기");
                startActivity(chooser);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("key_name", name);
        setResult(RESULT_OK,resultIntent);
        finish();
      //  super.onBackPressed();

    }

    private class ImgAsysncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String htmlContentInStringFormat ="";
            try {
                Document doc = Jsoup.connect(params[0]).get();

                //테스트1
                Elements titles= doc.select("meta");

                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    if(e.attr("property").equals("og:image")){
                        Log.e("로그ㅡ", "로그 :"+e.attr("content"));
                        System.out.println("title: " + e.attr("content"));
                        htmlContentInStringFormat =  e.attr("content").trim();
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return htmlContentInStringFormat;
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                Glide.with(SpaceActivity.this).load(result).into(spaceImg);
                spaceImg.setVisibility(View.VISIBLE);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }

        }
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String htmlContentInStringFormat = "";
            try {

                Document doc = Jsoup.connect(htmlPageUrl).get();

                Elements titles= doc.select("div.kno-rdesc");

                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    System.out.println("title: " + e.text());
                    htmlContentInStringFormat = e.text().trim() + "\n";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return htmlContentInStringFormat;
        }

        @Override
        protected void onPostExecute(String result) {
            spaceExplain.setText(result);
        }
    }



    private void showImage(final String imageUrl){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Glide.with(SpaceActivity.this).load(imageUrl).into(spaceImg);
            }
        });
    }







    public void changeColor(String change){
        if("review_image_bt".equals(change)){
            reviewImageBtn.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            shareImageBtn.setBackgroundColor(getResources().getColor(R.color.white));
            infoImageBtn.setBackgroundColor(getResources().getColor(R.color.white));
        } else if("share_image_bt".equals(change)){
             reviewImageBtn.setBackgroundColor(getResources().getColor(R.color.white));
            shareImageBtn.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            infoImageBtn.setBackgroundColor(getResources().getColor(R.color.white));
        } else if("info_image_bt".equals(change)){
            reviewImageBtn.setBackgroundColor(getResources().getColor(R.color.white));
            shareImageBtn.setBackgroundColor(getResources().getColor(R.color.white));
            infoImageBtn.setBackgroundColor(getResources().getColor(R.color.windowBackground));
        }
    }

}
