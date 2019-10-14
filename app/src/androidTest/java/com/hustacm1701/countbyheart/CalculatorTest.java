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
        symStrList.add("+=");
        numListList.add(getList("1","2"));
        answerLsit.add(3.0);

        symStrList.add("+-=");
        numListList.add(getList("1","2","3"));
        answerLsit.add(1.0+2-3);
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
}
