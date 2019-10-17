package com.hustacm1701.countbyheart.object;

import android.util.Log;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class History extends LitePalSupport {
    private int cmp;
    @Column(unique = true)
    private String date;
    private int precision;


    public History(String date, int precision) {
        this.date = date;
        this.precision = precision;
        if (date.equals(""))
            return;
        String[] numStr = date.split("/");
        this.cmp = Integer.valueOf(numStr[0])*10000+Integer.valueOf(numStr[1])*100+Integer.valueOf(numStr[2]);
//        Log.e("NUMBER", "History: "+numStr[0]+"/"+numStr[1]+"/"+numStr[2] );
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getCmp() {
        return cmp;
    }

    public void setCmp(int cmp) {
        this.cmp = cmp;
    }
}
