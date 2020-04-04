package com.myapp.production;

import com.myapp.util.CalculateUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CreateQuestion {

    //控制题目个数，默认为10
    private int n = 10;
    //控制题目中的数值
    private int r = -1;

    public CreateQuestion() {

    }
    public CreateQuestion(int n, int r) {
        this.n = n;
        this.r = r;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Map<String, String> CreateQuestions(){
        Map<String, String> questions = new HashMap<>();
        int totalNum = n;
        while(n > 0) {
            String question = this.createArithmeticExpression();
            if (question.equals("Error")) return null;
            String answer = CalculateUtil.Calculate(question);
            if (answer == null) continue;
//            System.out.println(n+"Q:" + question + "\nA:" + answer);
            questions.put(question, answer);
            n--;
            if (n == 0) {
                n = totalNum - questions.keySet().size();
            }
        }

        return questions;
    }

    public String createArithmeticExpression(){
        /**
         * @param question 题目
         * @param opQuantity 运算符数
         * @param step 步骤数
         * @param parenthesisPosition 左括号的位置
         * @param division 除号的位置
         * @param leftParenthesis 左括号数
         * @param rightParenthesis 右括号数
         * @param adjacent 左括号是否相邻
         */
        if (r == -1) {
            System.out.println("请先使用\"-r\"设置参数r以控制题目中的数值范围.");
            return "Error";
        }
        if (r <= 1){
            System.out.println("参数r不能小于2.");
            return "Error";
        }

        StringBuffer question = new StringBuffer();
        Random random = new Random();

        //随机决定运算符的个数（1-3个）
        int opQuantity = random.nextInt(3) + 1;
        //根据运算符个数决定操作步数
        int step = 2*opQuantity + 1;

        //决定括号总数
        int leftParenthesis = random.nextInt(opQuantity);
        int rightParenthesis = 0;

        int parenthesisPosition = 0;
        int division = 0;
        boolean adjacent = false;

        //当前步数
        int i = 1;
        while (i <= step){
            //单数步骤时生成数字
            if (i%2 == 1){
                //是否生成括号
                switch (leftParenthesis){
                    case 0: break;
                    case 1: {
                        if (i == step - 2) {
                            question.append("( ");
                            parenthesisPosition = i;
                            leftParenthesis--;
                            rightParenthesis++;
                        }
                        else {
                            switch (random.nextInt(2)){
                                case 0: break;
                                case 1: {
                                    question.append("( ");
                                    parenthesisPosition = i;
                                    leftParenthesis--;
                                    rightParenthesis++;
                                }
                            }
                        }
                    }break;
                    case 2:{
                        if (i == 3){
                            switch (random.nextInt(2)){
                                case 0: {
                                    question.append("( ");
                                    leftParenthesis--;
                                    rightParenthesis++;
                                }break;
                                case 1: {
                                    question.append("( ( ");
                                    leftParenthesis -= 2;
                                    rightParenthesis += 2;
                                    adjacent = true;
                                }
                            }
                            parenthesisPosition = i;
                        }
                        if (i == 1){
                            switch (random.nextInt(3)){
                                case 0: break;
                                case 1: {
                                    question.append("( ");
                                    leftParenthesis--;
                                    rightParenthesis++;
                                    parenthesisPosition = i;
                                }break;
                                case 2: {
                                    question.append("( ( ");
                                    leftParenthesis -= 2;
                                    rightParenthesis += 2;
                                    adjacent = true;
                                    parenthesisPosition = i;
                                }
                            }
                        }
                    }
                }

                //生成数字
                switch (random.nextInt(2)){
                    //生成整数
                    case 0: {
                        //除数不能为0
                        if (i - 1 == division) question.append(random.nextInt(r) + 1);
                        else question.append(random.nextInt(r + 1));
                    }break;
                    //生成分数
                    case 1: {
                        /**
                         * @param integer 整数
                         * @param molecule 分子
                         * @param denominator 分母
                         *
                         */
                        int integer = random.nextInt(r);
                        int molecule;
                        int denominator = random.nextInt(r - 1) + 2;
                        //分子小于分母
                        molecule = random.nextInt(denominator - 1) + 1;

                        if (integer != 0){
                            question.append(integer).append("'");
                        }

                        question.append(molecule).append("/").append(denominator);
                    }
                }

                //生成右括号
                if (rightParenthesis != 0 && parenthesisPosition != i){
                    if (question.indexOf("(") == 0 && rightParenthesis == 1 && i == step -2){
                        question.append(" )");
                        rightParenthesis--;
                    }
                    switch (rightParenthesis){
                        case 1: {
                            if (i == step) question.append(" )");
                            else {
                                switch (random.nextInt(2)){
                                    case 0: break;
                                    case 1: {
                                        question.append(" )");
                                        rightParenthesis--;
                                    }
                                }
                            }
                        }break;
                        case 2: {
                            if (adjacent){
                                question.append(" )");
                                rightParenthesis--;
                            }
                            else {
                                question.append(" ) )");
                                rightParenthesis -= 2;
                            }
                        }
                    }
                }
            }
            //偶数步骤时生成运算符
            else {
                switch (random.nextInt(4)){
                    case 0: question.append(" + ");break;
                    case 1: question.append(" - ");break;
                    case 2: question.append(" × ");break;
                    case 3: {
                        question.append(" ÷ ");
                        division = i;
                    }
                }
            }
            i++;
        }

        return question.toString();
    }
}
