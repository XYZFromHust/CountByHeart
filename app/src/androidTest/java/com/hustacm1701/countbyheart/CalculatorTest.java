package com.hustacm1701.countbyheart;

import com.hustacm1701.countbyheart.object.Calculator;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculatorTest {
    /*
    * 下面是对doCalculate的测试
    * doCalculate 输入参数为symStr(String)和numList()(List<Double>)
    * symStr的格式如：“+-/*”
    * */
    @Test
    public void testDoCalculate(){
        List<String> symStrList = new ArrayList<>();
        List<List<Double>> numListList = new ArrayList<>();
        List<Double> answerLsit = new ArrayList<>();
//---------------------------------------------------
//      添加实例
//        e.g
//        symStrList.add("+=");
//        numListList.add(getList("1","2"));
//        answerLsit.add(3.0);

        symStrList.add("+=");
        numListList.add(getList("1","2"));
        answerLsit.add(3.0);

        symStrList.add("-=");
        numListList.add(getList("2","3"));
        answerLsit.add(-1.0);

        symStrList.add("*=");
        numListList.add(getList("2","3"));
        answerLsit.add(6.0);

        symStrList.add("/=");
        numListList.add(getList("8","2"));
        answerLsit.add(4.0);

        symStrList.add("++=");
        numListList.add(getList("5","6","7"));
        answerLsit.add(5+6+7.0);

        symStrList.add("+-=");
        numListList.add(getList("2","3","10"));
        answerLsit.add(2+3-10.0);

        symStrList.add("+*=");
        numListList.add(getList("2","3","4"));
        answerLsit.add(2+3*4.0);


        symStrList.add("+/=");
        numListList.add(getList("3","2","4"));
        answerLsit.add(3+2/4.0);

        symStrList.add("-+=");
        numListList.add(getList("10","2","1"));
        answerLsit.add(10-2+1.0);

        symStrList.add("--=");
        numListList.add(getList("1","1","2"));
        answerLsit.add(1-1-2.0);

        symStrList.add("-*=");
        numListList.add(getList("3","4","2"));
        answerLsit.add(3-4*2.0);


        symStrList.add("-/=");
        numListList.add(getList("5","2","3"));
        answerLsit.add(5-2/3.0);

        symStrList.add("*+=");
        numListList.add(getList("2","3","4"));
        answerLsit.add(2*3+4.0);

        symStrList.add("*-=");
        numListList.add(getList("4","2","1"));
        answerLsit.add(4*2-1.0);

        symStrList.add("**=");
        numListList.add(getList("3","2","100"));
        answerLsit.add(3*2*100.0);


        symStrList.add("*/=");
        numListList.add(getList("30","50","7"));
        answerLsit.add(30*50/7.0);

        symStrList.add("/+=");
        numListList.add(getList("4","7","2"));
        answerLsit.add(4/7.0+2);

        symStrList.add("/-=");
        numListList.add(getList("9","3","10"));
        answerLsit.add(9/3.0-10);

        symStrList.add("/*=");
        numListList.add(getList("6","2","4"));
        answerLsit.add(6.0/2*4);


        symStrList.add("//=");
        numListList.add(getList("9","9","3"));
        answerLsit.add(9.0/9/3);

        symStrList.add("/=");
        numListList.add(getList("9","0"));
        answerLsit.add(Double.POSITIVE_INFINITY);

        symStrList.add("+*=");
        numListList.add(getList("1","0","500"));
        answerLsit.add(1+0*500.0);

        symStrList.add("**=");
        numListList.add(getList("500","500","500"));
        answerLsit.add(500*500*500.0);

        symStrList.add("+/=");
        numListList.add(getList("0","0","10"));
        answerLsit.add(0+0.0/10);

//--------------------------------------------------

        for (int i = 0;i<symStrList.size();i++){
            assertEquals(answerLsit.get(i),Calculator.doCalculate(symStrList.get(i),numListList.get(i)));
        }
    }
    private List<Double> getList (String ... strings){
        List<Double> list = new ArrayList<>();
        for (String s:strings){
            list.add(Double.valueOf(s));
        }
        return list;
    }

/*
* 下面是对Calculator中的calculate进行的测试
* 因为我们自动生成的题目是绝对符合格式的，因此对不符合格式的测试就可以省去了
*
* */

    @Test
    public void testCalculate(){
        String a = "1 + 2 = ";
        String b = "1 + 2 - 3 = ";
        assert 3.0 == Calculator.calculate(a);
        assert 0.0 == Calculator.calculate(b);
    }
}
