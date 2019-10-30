package com.firstapp.hytripplan.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.firstapp.hytripplan.api.vo.Plan;

import java.util.ArrayList;
import java.util.List;

public class PlanDataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="tripPlan.db";
    private static final int DATABASE_VERSION=2;
    public PlanDataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public PlanDataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public PlanDataHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tripPlan("+
                "ID INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", tripTitle TEXT NOT NULL" +
                ", dayStart TEXT" +
                ", dayEnd TEXT" +
                ", place TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tripPlan");
        onCreate(db);
    }

    public List<Plan> getDATA(){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT tripTitle, dayStart, dayEnd FROM tripPlan");

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sb.toString(), null);

        List listItem = new ArrayList();

        Plan plan;

        while (cursor.moveToNext()){
            if(cursor.isLast()){
                listItem.add(from(cursor));
            }
        }
        return listItem;
    }


    public static Plan from(Cursor cursor){
        Plan plan = new Plan();
        plan.setTripTitle(cursor.getString(0));
        plan.setDayStart(cursor.getString(1));
        plan.setDayEnd(cursor.getString(2));
        return plan;
    }
}
