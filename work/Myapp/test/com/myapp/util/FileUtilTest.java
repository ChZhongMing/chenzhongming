package com.myapp.util;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileUtilTest {

    @Test
    public void readQuestion() {
        List<String>[] exercises = FileUtil.readQuestion("./题目.txt", "./答案.txt");
        int length = exercises[0].size() < exercises[1].size() ? exercises[0].size() : exercises[1].size();
        if (exercises != null){
            for (int index = 0; index < length; index++){
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

    @Test
    public void writeFile(){
        List<String> content = new ArrayList<>();
        content.add("2");
        content.add("45");
        content.add("23");
        content.add("123");
        content.add("2’2/3");

        FileUtil.writeFile(content,"./测试写入.txt");
    }
}