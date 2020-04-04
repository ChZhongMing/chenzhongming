package com.myapp.util;

import com.myapp.entity.Fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 计算工具类
 */
public class CalculateUtil {

    /***
     *    获得前缀表达式
     * @param exp
     * @return
     */
    public static List<String> getExpression(String exp) {
//        String exp = "1 + ( ( 2 + 3 ) × 4 ) - 5";
        Stack<String> charStack = new Stack<>();
        Stack<String> expression = new Stack<>();
        String[] split = exp.trim().split("\\s+");
        int leng = split.length;
        for (int i = leng - 1; i >= 0; i--) {

            // 符号
            if (split[i].matches("[+|\\-|×|÷|)]")) {
                while (true) {
                    //如果栈为空或栈顶为")"或者运算符为×÷)
                    if (charStack.empty() || ")".equals(charStack.peek()) || "×".equals(split[i]) || "÷".equals(split[i]) || ")".equals(split[i])) {
                        charStack.push(split[i]);
                        break;
                        //运算符与栈顶同时为+或-
                    } else if (split[i].matches("[+|\\-]") && charStack.peek().matches("[+|\\-]")) {
                        charStack.push(split[i]);
                        break;
                    } else {
                        expression.push(charStack.pop());
                    }
                }
            } else if ("(".equals(split[i])) {
                while (true) {
                    if (charStack.peek().equals(")")) {
                        charStack.pop();
                        break;
                    }
                    expression.push(charStack.pop());
                }
            } else {
                expression.push(split[i]);
            }

//            System.out.println(split[i]);

        }
        while (!charStack.empty()) {
            expression.push(charStack.pop());
        }
        List<String> expList = new ArrayList<>(expression.size());
        while (!expression.empty()){
            expList.add(expression.pop());
        }
        return expList;
    }

    /***
     * 计算前缀表达式
     * @param exp
     * @return
     */
    public static String Calculate(String exp) {
        List<String> expression = getExpression(exp);
        String num ;
        if (exp.indexOf('÷') > 0 || exp.indexOf('/') > 0) {
            Stack<Fraction> numStack = new Stack<>();
            Fraction fraction = null;
            for (int i = expression.size() - 1; i >= 0; i--) {
                num = expression.get(i);
                switch (num) {
                    case "+":
                        fraction = numStack.pop();
                        fraction.add(numStack.pop());
                        numStack.push(fraction);
                        break;
                    case "-":
                        fraction = numStack.pop();
                        fraction.reduce(numStack.pop());
                        numStack.push(fraction);
                        //出现负值直接返回空
                        if (numStack.peek().getVaule() < 0){
                            return null;
                        }
                        break;
                    case "×":
                        fraction = numStack.pop();
                        fraction.ride(numStack.pop());
                        numStack.push(fraction);
                        break;
                    case "÷":
                        fraction = numStack.pop();
                        fraction.divide(numStack.pop());
                        numStack.push(fraction);
                        break;
                    default:
                        numStack.push(TransformUtil.expToFraction(num));
                }

            }
            return numStack.pop().toString();
        } else {
            Stack<Integer> numStack = new Stack<>();
            for (int i = expression.size() - 1; i >= 0; i--) {
                num = expression.get(i);
                switch (num) {
                    case "+":
                        numStack.push(numStack.pop() + numStack.pop());
                        break;
                    case "-":
                        numStack.push(numStack.pop() - numStack.pop());
                        //出现负值直接返回空
                        if (numStack.peek() < 0){
                            return null;
                        }
                        break;
                    case "×":
                        numStack.push(numStack.pop() * numStack.pop());
                        break;
                    default:
                        numStack.push(Integer.parseInt(num));
                }

            }
            return numStack.pop().toString();
        }
    }
    public static void main(String[] args) {

        String exp = "( 3 + 1’7/8 ) × ( 1/2 )";
        String exp2 = "3 × 1 + ( ( 2 + 3 ) × 4 ) - 5";
        System.out.println(Calculate(exp));
        System.out.println(Calculate(exp2));
    }


}
