package com.hustacm1701.countbyheart.object;
/*
* 待开发
* */
public class ErrorLib {
    private static ErrorLib errorLib = null;
    public static ErrorLib getInstance(){
        if (errorLib==null)
            errorLib = new ErrorLib();
        return errorLib;
    }
}
