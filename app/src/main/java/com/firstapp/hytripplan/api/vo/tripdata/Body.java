
package com.firstapp.hytripplan.api.vo.tripdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body {

    @SerializedName("items")
    @Expose
    private Items items;
    @SerializedName("numOfRows")
    @Expose
    private Long numOfRows;
    @SerializedName("pageNo")
    @Expose
    private Long pageNo;
    @SerializedName("totalCount")
    @Expose
    private Long totalCount;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Long getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(Long numOfRows) {
        this.numOfRows = numOfRows;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

}
