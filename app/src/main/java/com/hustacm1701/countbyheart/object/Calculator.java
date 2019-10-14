package com.hustacm1701.countbyheart.object;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/**
 * 这里直接使用已经写好的一个项目，版权归作者所有
 * @Title: Calculator.java
 * @desc: 加减乘除计算器，支持括号，小数，负数
 * @author mengchuan.li
 * @date 2016年11月14日 下午1:22:39
 */


public class Calculator {
    public static Double calculate(String str) {
        // 空值校验
        if (null == str || "".equals(str)) {
            return null;
        }
        
        // 长度校验
        if (str.length() > MyUtils.FORMAT_MAX_LENGTH) {
            System.out.println("表达式过长！");
            return null;
        }
        // 预处理
        str = str.replaceAll(" ", "");// 去空格
        if ('-' == str.charAt(0)) {// 开头为负数，如-1，改为0-1
            str = 0 + str;
        }
        // 校验格式
        if (!MyUtils.checkFormat(str)) {
            System.out.println("表达式错误！");
            return null;
        }
        // 处理表达式，改为标准表达式
        str = MyUtils.change2StandardFormat(str);
        // 拆分字符和数字
        String[] nums = str.split("[^.0-9]");
        List<Double> numLst = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            if (!"".equals(nums[i])) {
                numLst.add(Double.parseDouble(nums[i]));
            }
        }
        String symStr = str.replaceAll("[.0-9]", "");
        return doCalculate(symStr, numLst);
    }
    public static Double doCalculate(String symStr, List<Double> numLst) {
//    private static Double doCalculate(String symStr, List<Double> numLst) {
        LinkedList<Character> symStack = new LinkedList<>();// 符号栈
        LinkedList<Double> numStack = new LinkedList<>();// 数字栈
        double result = 0;
        int i = 0;// numLst的标志位
        int j = 0;// symStr的标志位
        char sym;// 符号
        double num1, num2;// 符号前后两个数
        while (symStack.isEmpty() || !(symStack.getLast() == '=' && symStr.charAt(j) == '=')) {// 形如：
            // =8=
            // 则退出循环，结果为8
            if (symStack.isEmpty()) {
                symStack.add('=');
                numStack.add(numLst.get(i++));
            }
            if (MyUtils.symLvMap.get(symStr.charAt(j)) > MyUtils.symLvMap.get(symStack.getLast())) {// 比较符号优先级，若当前符号优先级大于前一个则压栈
                if (symStr.charAt(j) == '(') {
                    symStack.add(symStr.charAt(j++));
                    continue;
                }
                numStack.add(numLst.get(i++));
                symStack.add(symStr.charAt(j++));
            } else {// 当前符号优先级小于等于前一个 符号的优先级
                if (symStr.charAt(j) == ')' && symStack.getLast() == '(') {// 若（）之间没有符号，则“（”出栈
                    j++;
                    symStack.removeLast();
                    continue;
                }
                if (symStack.getLast() == '(') {// “（”直接压栈
                    numStack.add(numLst.get(i++));
                    symStack.add(symStr.charAt(j++));
                    continue;
                }
                num2 = numStack.removeLast();
                num1 = numStack.removeLast();
                sym = symStack.removeLast();
                switch (sym) {
                    case '+':
                        numStack.add(MyUtils.plus(num1, num2));
                        break;
                    case '-':
                        numStack.add(MyUtils.reduce(num1, num2));
                        break;
                    case '*':
                        numStack.add(MyUtils.multiply(num1, num2));
                        break;
                    case '/':
                        if (num2 == 0) {// 除数为0
                            System.out.println("存在除数为0");
                            return null;
                        }
                        numStack.add(MyUtils.divide(num1, num2));
                        break;
                }
            }
        }
        return numStack.removeLast();
    }
}
class MyUtils {
    public static final int FORMAT_MAX_LENGTH = 500;// 表达式最大长度限制
    public static final int RESULT_DECIMAL_MAX_LENGTH = 8;// 结果小数点最大长度限制
    public static final Map<Character, Integer> symLvMap = new HashMap<Character, Integer>();// 符号优先级map
    static {
        symLvMap.put('=', 0);
        symLvMap.put('-', 1);
        symLvMap.put('+', 1);
        symLvMap.put('*', 2);
        symLvMap.put('/', 2);
        symLvMap.put('(', 3);
        symLvMap.put(')', 1);
        // symLvMap.put('^', 3);
        // symLvMap.put('%', 3);
    }

    static boolean checkFormat(String str) {
        // 校验是否以“=”结尾
        if ('=' != str.charAt(str.length() - 1)) {
            return false;
        }
        // 校验开头是否为数字或者“(”
        if (!(isCharNum(str.charAt(0)) || str.charAt(0) == '(')) {
            return false;
        }
        char c;
        // 校验
        for (int i = 1; i < str.length() - 1; i++) {
            c = str.charAt(i);
            if (!isCorrectChar(c)) {// 字符不合法
                return false;
            }
            if (!(isCharNum(c))) {
                if (c == '-' || c == '+' || c == '*' || c == '/') {
                    if (c == '-' && str.charAt(i - 1) == '(') {// 1*(-2+3)的情况
                        continue;
                    }
                    if (!(isCharNum(str.charAt(i - 1)) || str.charAt(i - 1) == ')')) {// 若符号前一个不是数字或者“）”时
                        return false;
                    }
                }
                if (c == '.') {
                    if (!isCharNum(str.charAt(i - 1)) || !isCharNum(str.charAt(i + 1))) {// 校验“.”的前后是否位数字
                        return false;
                    }
                }
            }
        }
        return isBracketCouple(str);// 校验括号是否配对
    }

    static String change2StandardFormat(String str) {
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (i != 0 && c == '(' && (isCharNum(str.charAt(i - 1)) || str.charAt(i - 1) == ')')) {
                sb.append("*(");
                continue;
            }
            if (c == '-' && str.charAt(i - 1) == '(') {
                sb.append("0-");
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
    private static boolean isBracketCouple(String str) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.add(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.removeLast();
            }
        }
        return stack.isEmpty();

    }
    public static String formatResult(String str) {
        String[] ss = str.split("\\.");
        if (Integer.parseInt(ss[1]) == 0) {
            return ss[0];// 整数
        }
        String s1 = new StringBuilder(ss[1]).reverse().toString();
        int start = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != '0') {
                start = i;
                break;
            }
        }
        return ss[0] + "." + new StringBuilder(s1.substring(start, s1.length())).reverse();
    }
    private static boolean isCorrectChar(Character c) {
        return ('0' <= c && c <= '9') || c == '-' || c == '+' || c == '*' || c == '/' || c == '(' || c == ')'
                || c == '.';
    }
    private static boolean isCharNum(Character c) {
        return c >= '0' && c <= '9';

    }
    static double plus(double num1, double num2) {
        return num1 + num2;
    }

    static double reduce(double num1, double num2) {
        return num1 - num2;
    }

    static double multiply(double num1, double num2) {
        return num1 * num2;

    }

    public static double divide(double num1, double num2) {
        return num1 / num2;
    }
}
