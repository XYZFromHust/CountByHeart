package com.hustacm1701.countbyheart.object;

import org.litepal.crud.LitePalSupport;

public class History extends LitePalSupport {
    private String date;
    private int precision;

    public History(String date, int precision) {
        this.date = date;
        this.precision = precision;
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
}
