package com.myapp;


import com.myapp.entity.Fraction;
import com.myapp.production.CreateQuestion;
import com.myapp.util.FileUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTest {

    @Test
    public void main() {
//        Main.exercisesCheck("题目.txt", "答案.txt");
//        System.out.println(Fraction.getCommonDivisor(2,2));
//        System.out.println(new Fraction(0, 19, 6));
        Fraction fraction = new Fraction(1, 16, 6);
        Fraction fraction2 = new Fraction(1, 16, 6);
        System.out.println("原值"+fraction);
        //乘
        fraction.ride(3);
        System.out.println("乘3："+fraction);
        fraction.divide(2);
        System.out.println("除以2："+fraction);
        fraction.ride(fraction2);
        System.out.println("乘3'2/3："+fraction);
        //除
        fraction.divide(fraction2);
        System.out.println("除以3'2/3："+fraction);
        //减
        fraction.reduce(10);
        System.out.println("减10："+fraction);
        fraction.reduce(fraction2);
        System.out.println("减3'2/3："+fraction);
        //加
        fraction.add(fraction2);
        System.out.println("加3'2/3："+fraction);
        fraction.add(12);
        System.out.println("加12："+fraction);

        fraction.divide(2);
        System.out.println("除以2："+fraction);
    }

    @Test
    public void test(){
        String tt = "25";
//        long start1 = System.currentTimeMillis();
//        System.out.println("+".equals(tt) || "-".equals(tt));
//        System.out.println(start1);
//        System.out.println(System.currentTimeMillis());
//        long start2 = System.currentTimeMillis();
        System.out.println(tt.matches("25|t"));
//        System.out.println(start2);
//        System.out.println(System.currentTimeMillis());
    }

    @Test
    public static void main(String[] args) {
        Map<String, String> m = new HashMap<>();
        String numOp = "1";
        String ans = "2";
        Pattern pattern = Pattern.compile("\\(" + ans + "\\)");

        ans = "(" + ans + ")";
        m.put(numOp, ans);
        Matcher matcher = pattern.matcher(m.get(numOp));
        ans = "0";
        ans = m.get(numOp) + "(" + ans + ")";
        m.put(numOp, ans);
        System.out.println(m.get(numOp));

        if (matcher.find()) System.out.println("Error");
        else {
            System.out.println("Yes");
        }
    }
//
//    @Test
//    public void creatAnswerFile() {
//
//        Main.creatAnswerFile("题目.txt");
//    }
//
//    @Test
//    public void readQuestion() {
//        Main.exercisesCheck("./题目.txt", "./答案.txt");
//    }
}