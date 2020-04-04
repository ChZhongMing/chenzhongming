package com.myapp.util;

import com.myapp.entity.Fraction;

/**
 * 分数小数转型工具
 */
public class TransformUtil {

    public static double FractionToDecimal(String fraction){
        int i = fraction.indexOf('\'');
        double integer = 0;
        double aDouble = 0;
        String[] split = fraction.split("/");
        if (i > 0) {
            integer = Double.valueOf(fraction.substring(0, i));
            split[0]=split[0].substring(i+1);
//            System.out.println(i+"又"+integer);
        }
        aDouble = Double.valueOf(split[0]) / Double.valueOf(split[1]);
        return aDouble + integer;
    }

    /**
     * 分式表达式转Fraction对象
     * @param fraction
     * @return
     */
    public static Fraction expToFraction(String fraction){
        int i = fraction.indexOf('\'');
        int integer = 0;
        String[] split = fraction.split("/");
        if (i > 0) {
            integer = Integer.parseInt(fraction.substring(0, i));
            split[0]=split[0].substring(i+1);
        }
        if (split.length == 2){
            return new Fraction(integer, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }else {
            return new Fraction(integer, Integer.parseInt(split[0]), 1);
        }

    }
}
