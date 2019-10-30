package com.firstapp.hytripplan.make;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firstapp.hytripplan.DB.PlanDataHelper;
import com.firstapp.hytripplan.R;
import com.firstapp.hytripplan.api.vo.Plan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class MakePlanActivity extends AppCompatActivity {
    private EditText planTitle, dayStart, dayEnd;
    private ImageView calImage, mapImage;
    private Button planSaveBtn;
    private ListView listItem;
    private String s_planTitle;
    private String s_dayStart;
    private String s_dayEnd;
    private SimpleDateFormat dateFormat;

    //달력 객체 선언
    private int mYear, mMonth, mDay;
    private DatePickerDialog startDialog, endDialog;


    //DB
    private PlanDataHelper planData;
    private SQLiteDatabase db;

    private PlanDataListAdapter planDataListAdapter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_plan1);



        planTitle = findViewById(R.id.plan_title);
        dayStart = findViewById(R.id.day_start);
        dayEnd = findViewById(R.id.day_end);
        calImage = findViewById(R.id.cal_image);
        mapImage = findViewById(R.id.map_image);
        planSaveBtn = findViewById(R.id.plan_save_btn);
        listItem = findViewById(R.id.list_item);

        dateFormat = new SimpleDateFormat("yyyy.MM.dd");


        planData = new PlanDataHelper(this);
        try {
            db = planData.getReadableDatabase();
        } catch (SQLiteException e) {
            db = planData.getWritableDatabase();
        }


        planSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("클릭호출", "클릭호출");
                if (planTitle == null || dayStart == null || dayEnd == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MakePlanActivity.this);
                    builder.setTitle("입력 정보가 부족합니다.").setMessage("입력하지 않은 정보가 있는지 확인해주세요.");
                    builder.create().show();

                } else {
                    if (validationDate(dayStart.getText().toString()) && validationDate(dayEnd.getText().toString())){
                        validationDate(dayStart.getText().toString());
                        validationDate(dayEnd.getText().toString());
                        s_planTitle = planTitle.getText().toString();
                        s_dayStart = dayStart.getText().toString();
                        s_dayEnd = dayEnd.getText().toString();
                        db.execSQL("insert into tripPlan (tripTitle,dayStart,dayEnd) values('" + s_planTitle + "','" + s_dayStart + "'," + "'" + s_dayEnd + "')");
                        Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();

                        if( planData == null ) {
                            planData = new PlanDataHelper(MakePlanActivity.this);
                        }else {
                            List listData = planData.getDATA();
                            planDataListAdapter.setData(listData);
                            planDataListAdapter.notifyDataSetChanged();
                        }

                    }else {
                        Toast.makeText(MakePlanActivity.this, "2019.01.01의 형식으로 입력해주세요.", Toast.LENGTH_SHORT).show();
                        dayStart.setText(null);
                        dayEnd.setText(null);
                    }

                }

            }

            // todo 데이터 저장하여, 새로고침 하는 부분
        });

        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        startDialog = new DatePickerDialog(this, new OnDateSetListener(dayStart), mYear, mMonth, mDay);
        endDialog = new DatePickerDialog(this, new OnDateSetListener(dayEnd), mYear, mMonth, mDay);
        calImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDialog.show();
                startDialog.show();

            }
        });



        planDataListAdapter = new PlanDataListAdapter();
        listItem.setAdapter(planDataListAdapter);

    }



    public class PlanDataListAdapter extends BaseAdapter{
        private List<Plan> listData;

        public void setData(List<Plan> listData){
            this.listData = listData;
        }

        @Override
        public int getCount() {
            if(listData == null){
                return 0;
            }else{
                return listData.size();
            }

        }

        @Override
        public Object getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row;
            LayoutInflater inflater = LayoutInflater.from(MakePlanActivity.this);
            row = inflater.inflate(R.layout.custom_row, parent, false);
            TextView tv = row.findViewById(R.id.rowText);
            tv.setText(listData.get(position).toString());
            return row;
        }
    }

    public static class OnDateSetListener implements DatePickerDialog.OnDateSetListener{
        private EditText et;
        public OnDateSetListener(EditText et){
            this.et = et;
        }
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            et.setText(new StringBuilder()
                    .append(year).append(".")
                    .append(month + 1).append(".")
                    .append(dayOfMonth));
        }
    }

    public boolean validationDate(String checkDate) {

        try {
            dateFormat.setLenient(false);
            dateFormat.parse(checkDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
