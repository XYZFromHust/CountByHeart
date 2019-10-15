package com.hustacm1701.countbyheart.object;
/*
* 待开发 --- 用来保存小朋友的错题
* 包含能够记录自从使用软件以来的错题
* 能够添加小朋友的错题
* 能对之前的错题进行重新展示，并且删除
*
* 可在version 2 中开发
*
* */
public class ErrorLib {
    private static ErrorLib errorLib = null;
//    获取实例
    public static ErrorLib getInstance(){
        if (errorLib==null)
            errorLib = new ErrorLib();
        return errorLib;
    }
}
