package com.myapp;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
        String expression = "10 * 2 + 6 / (3 + 1) * 9";

        try {
            String result = String.valueOf(scriptEngine.eval(expression));
            System.out.println(result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        int i = Integer.parseInt("1");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");



        Map<String,Integer> map = new HashMap<>();
    }

    public void tt(){

    }
}
