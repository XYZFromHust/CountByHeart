package com.hustacm1701.countbyheart.object;

import java.util.Random;

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
        Random random = new Random();
        content = String.valueOf(random.nextInt(10));
        content+=" + ";
        content+=String.valueOf(random.nextInt(10));
        content+=" = ";
        getAnswerPri();
    }
//    根据输入生成题目
    public Task(String string){
        content = string+"=";
        getAnswerPri();
    }
//  通过计算器计算答案
    private void getAnswerPri(){
        answer = Calculator.calculate(content);
    }

    public int getAnswer() {
        return (int)answer;
    }

    //  判断是否正确
    public boolean isRight(double input){
        return input == answer;
    }
    public String getContent(){
        return content;
    }
}
