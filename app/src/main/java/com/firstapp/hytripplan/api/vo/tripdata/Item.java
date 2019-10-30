
package com.firstapp.hytripplan.api.vo.tripdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("addr1")
    @Expose
    private String addr1;
    @SerializedName("addr2")
    @Expose
    private String addr2;
    @SerializedName("areacode")
    @Expose
    private Long areacode;
    @SerializedName("booktour")
    @Expose
    private Long booktour;
    @SerializedName("cat1")
    @Expose
    private String cat1;
    @SerializedName("cat2")
    @Expose
    private String cat2;
    @SerializedName("cat3")
    @Expose
    private String cat3;
    @SerializedName("contentid")
    @Expose
    private Long contentid;
    @SerializedName("contenttypeid")
    @Expose
    private Long contenttypeid;
    @SerializedName("createdtime")
    @Expose
    private Long createdtime;
    @SerializedName("firstimage")
    @Expose
    private String firstimage;
    @SerializedName("firstimage2")
    @Expose
    private String firstimage2;
    @SerializedName("mapx")
    @Expose
    private Double mapx;
    @SerializedName("mapy")
    @Expose
    private Double mapy;
    @SerializedName("mlevel")
    @Expose
    private Long mlevel;
    @SerializedName("modifiedtime")
    @Expose
    private Long modifiedtime;
    @SerializedName("readcount")
    @Expose
    private Long readcount;
    @SerializedName("sigungucode")
    @Expose
    private Long sigungucode;
    @SerializedName("tel")
    @Expose
    private String tel;
    @SerializedName("title")
    @Expose
    private String title;

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public Long getAreacode() {
        return areacode;
    }

    public void setAreacode(Long areacode) {
        this.areacode = areacode;
    }

    public Long getBooktour() {
        return booktour;
    }

    public void setBooktour(Long booktour) {
        this.booktour = booktour;
    }

    public String getCat1() {
        return cat1;
    }

    public void setCat1(String cat1) {
        this.cat1 = cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public void setCat2(String cat2) {
        this.cat2 = cat2;
    }

    public String getCat3() {
        return cat3;
    }

    public void setCat3(String cat3) {
        this.cat3 = cat3;
    }

    public Long getContentid() {
        return contentid;
    }

    public void setContentid(Long contentid) {
        this.contentid = contentid;
    }

    public Long getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(Long contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    public Long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Long createdtime) {
        this.createdtime = createdtime;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getFirstimage2() {
        return firstimage2;
    }

    public void setFirstimage2(String firstimage2) {
        this.firstimage2 = firstimage2;
    }

    public Double getMapx() {
        return mapx;
    }

    public void setMapx(Double mapx) {
        this.mapx = mapx;
    }

    public Double getMapy() {
        return mapy;
    }

    public void setMapy(Double mapy) {
        this.mapy = mapy;
    }

    public Long getMlevel() {
        return mlevel;
    }

    public void setMlevel(Long mlevel) {
        this.mlevel = mlevel;
    }

    public Long getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Long modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public Long getReadcount() {
        return readcount;
    }

    public void setReadcount(Long readcount) {
        this.readcount = readcount;
    }

    public Long getSigungucode() {
        return sigungucode;
    }

    public void setSigungucode(Long sigungucode) {
        this.sigungucode = sigungucode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
