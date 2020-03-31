package com.myapp;

import com.myapp.util.CalculateUtil;
import com.myapp.util.FileUtil;
import com.myapp.util.TransformUtil;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        exercisesCheck("题目.txt", "答案.txt");
    }

    /**
     * 检验答案
     *
     * @param questionFile
     * @param answerFile
     */
    protected static void exercisesCheck(String questionFile, String answerFile) {
        List<Integer> Correct = new ArrayList<>();
        List<Integer> Wrong = new ArrayList<>();
        List<String>[] exercises = FileUtil.readQuestion(questionFile, answerFile);


        //题目
        String answerString ;
        //答案
        String expression;
        //题目序号
        int i = 1;
        long start = System.currentTimeMillis();
        int length = exercises[0].size();
        for (int index = 0; index < length; index++) {
            //获取对应答案
            expression = exercises[0].get(index);
            answerString = exercises[1].get(index);
//            System.out.println(CalculateUtil.Calculate(expression));
            if (answerString.equals(CalculateUtil.Calculate(expression))) {
                Correct.add(i++);
            } else {
                Wrong.add(i++);
            }
        }
        System.out.println("Correct: " + Correct.size() + Correct);
        System.out.println("Wrong: " + Wrong.size() + Wrong);
        System.out.println("耗时" + (System.currentTimeMillis() - start));
    }
}
