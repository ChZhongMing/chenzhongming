package com.myapp;

import com.myapp.util.FileUtil;
import com.myapp.util.TransformUtil;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

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
        Map<String, String> exercises = FileUtil.readQuestion(questionFile, answerFile);
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
        double answer = 0;
        double formula = 0;
        String answerString;
        //题目序号
        int i = 1;
        long start = System.currentTimeMillis();
        for (String expression : exercises.keySet()) {
        /*迭代器方式（目前效率差不多）
        final Iterator<String> iterator = exercises.keySet().iterator();
        String expression;
        while(iterator.hasNext()){
            expression = iterator.next();*/
            //获取对应答案
            answerString=exercises.get(expression);
            //判断是否是分式
            if (answerString.indexOf('/') > 0){
                answer = TransformUtil.FractionToDecimal(answerString);
            }else {
                answer = Double.valueOf(answerString);
            }
            //转换÷号
            expression = expression.replaceAll("÷", "/");
            //计算表达式
            try {
                formula = Double.valueOf(scriptEngine.eval(expression).toString());
            } catch (ScriptException e) {
                e.printStackTrace();
            }

            if (formula == answer) {
                Correct.add(i++);
            }else {
                Wrong.add(i++);
            }
        }
        System.out.println("Correct: "+Correct.size()+Correct);
        System.out.println("Wrong: "+Wrong.size()+Wrong);
        System.out.println("耗时"+(System.currentTimeMillis()-start));
    }
}
