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
    private Random random = new Random();
    private int[] numbers;
    private char[] ops,op_set;
    private int countRange;
    private int numberRange;
//    随机生成题目
    public Task(int level){
//        生成左等式：
        switch (level){
            case 1:
                numberRange = 10;
                countRange = 2;
                op_set = new char[]{'+','-'};
                getNumberAndOps();
                break;
            case 2:
                int t = random.nextInt(2);
                switch (t){
                    case 0:
                        numberRange = 100;
                        countRange = 2;
                        op_set = new char[]{'+','-'};
                        getNumberAndOps();
                        break;
                    case 1:
                        numberRange = 30;
                        countRange = random.nextInt(2)+2;
                        op_set = new char[]{'+','-'};
                        getNumberAndOps();
                        break;
                }
                break;
            case 3:
                t = random.nextInt(2);
                switch (t){
                    case 0:
                        numberRange = 100;
                        countRange = random.nextInt(2)+2;
                        op_set = new char[]{'+','-'};
                        getNumberAndOps();
                        break;
                    case 1:
                        numberRange = 9;
                        countRange = 2;
                        op_set = new char[]{'*','/'};
                        getNumberAndOps();
                        break;
                }
                break;
            case 4:
                t = random.nextInt(2);
                switch (t){
                    case 0:
                        numberRange = 100;
                        countRange = random.nextInt(2)+2;
                        op_set = new char[]{'+','-'};
                        getNumberAndOps();
                        break;
                    case 1:
                        numberRange = 9;
                        countRange = random.nextInt(2)+2;
                        op_set = new char[]{'+','-','*','/'};
                        getNumberAndOps();
                        break;
                }
                break;
            case 5:
                t = random.nextInt(2);
                switch (t){
                    case 0:
                        numberRange = 500;
                        countRange = random.nextInt(2)+2;
                        op_set = new char[]{'+','-'};
                        getNumberAndOps();
                        break;
                    case 1:
                        numberRange = 100;
                        countRange = 2;
                        op_set = new char[]{'*','/'};
                        getNumberAndOps();
                        break;
                }
                break;
            case 6:
                t = random.nextInt(2);
                switch (t){
                    case 0:
                        numberRange = 100;
                        countRange = 2;
                        op_set = new char[]{'*','/'};
                        getNumberAndOps();
                        break;
                    case 1:
                        numberRange = 100;
                        countRange = 3;
                        op_set = new char[]{'*','/','+','-'};
                        getNumberAndOps();
                        break;
                }
                break;
        }
//        显示：
        content = toString();
    }

    private void getNumberAndOps(){
        numbers = new int[countRange];
        ops = new char[countRange-1];
        double testAns;
        int t;
        do {
            for (int i = 0;i<countRange;i++){
                numbers[i] = random.nextInt(numberRange)+1;
            }
            for (int i = 0;i<countRange-1;i++){
                ops[i] = op_set[random.nextInt(op_set.length)];
            }
            testAns = getAnswerPri(toString());
            t = (int) testAns;
        }while (testAns<0 || t!=testAns);
        answer = testAns;
    }

    public String toString(){
        if (countRange == 2){
            return numbers[0]+" "+ops[0]+" "+numbers[1]+" = ";
        }else {
            return numbers[0]+" "+ops[0]+" "+numbers[1]+" "+ops[1]+" "+numbers[2]+" = ";
        }
    }
//    根据输入生成题目
    public Task(String string){
        content = string+"=";
        answer = getAnswerPri(content);
    }
//  通过计算器计算答案
    private double getAnswerPri(String string){
        if (string==null)
            return 0.0;
        return Calculator.calculate(string);
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
