package com.hustacm1701.countbyheart.object;
/*
* Task类主要的作用就是保存题目的相关信息,通过调用calculator获得答案
* 包含的内容：
*   题目的字符串描述,字符串描述必须以=结尾
*   题目的答案
*
* task的创建可以为随机生成，也可以为用户的字符串输入
* task可以按照一定的格式展示
* */
public class Task {
    private String content;
    private double answer;
//    随机生成题目
    public Task(){

        getAnswer();
    }
//    根据输入生成题目
    public Task(String string){
        content = string+"=";
        getAnswer();
    }
//  通过计算器计算答案
    private void getAnswer(){
        answer = Calculator.calculate(content);
    }
//  判断是否正确
    public boolean isRight(double input){
        return input == answer;
    }
}
