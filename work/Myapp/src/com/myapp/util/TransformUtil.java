package com.myapp.util;

/**
 * 分数小数转型工具
 */
public class TransformUtil {

    public static double FractionToDecimal(String fraction){
        int i = fraction.indexOf('’');
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
}
