package com.myapp;


import com.myapp.entity.Fraction;
import org.junit.Test;

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
        System.out.println("乘3’2/3："+fraction);
        //除
        fraction.divide(fraction2);
        System.out.println("除以3’2/3："+fraction);
        //减
        fraction.reduce(10);
        System.out.println("减10："+fraction);
        fraction.reduce(fraction2);
        System.out.println("减3’2/3："+fraction);
        //加
        fraction.add(fraction2);
        System.out.println("加3’2/3："+fraction);
        fraction.add(12);
        System.out.println("加12："+fraction);

        fraction.divide(2);
        System.out.println("除以2："+fraction);
    }
}