package com.firstapp.hytripplan.api.vo;

import androidx.annotation.NonNull;

public class Plan {
    private int _id;

    private String tripTitle;
    private String dayStart;
    private String dayEnd;
    private String place;

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public String getDayStart() {
        return dayStart;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @NonNull
    @Override
    public String toString() {
        try{
            return tripTitle+", "+dayStart+", "+dayEnd;
        }catch (NullPointerException e){
            return "-";
        }

    }
}
