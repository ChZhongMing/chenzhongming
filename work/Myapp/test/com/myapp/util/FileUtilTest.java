package com.myapp.util;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileUtilTest {

    @Test
    public void readQuestion() {
        List<String>[] exercises = FileUtil.readQuestion("./题目.txt", "./答案.txt");
        if (exercises != null){
            for (int index = 0; index < exercises[0].size(); index++){
                System.out.println(exercises[0].get(index)+" = "+exercises[1].get(index));
            }
        }
    }

    @Test
    public void writeQuestion() {

        Map<String, String> exercises = new HashMap<>();
        exercises.put("1+3","4");
        exercises.put("2*(3+4)","14");
        exercises.put("9÷3","3");
        FileUtil.writeQuestion(exercises,"./题目.txt","./答案.txt");

    }
}