package com.myapp.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 文件操作工具类
 */
public class FileUtil {

    /**
     * 获取文件输入流
     *
     * @param fileName 文件路径/名
     * @return
     */
    public static BufferedReader GetFileInputStream(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.forName("utf-8")));
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到["+fileName+"]文件");
        }
        return bufferedReader;
    }

    /**
     * 获取文件输出流
     *
     * @param fileName 文件路径/名
     * @return
     */
    public static BufferedWriter GetFileOutputStream(String fileName) {
        BufferedWriter BufferedWriter = null;
        try {
            BufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), Charset.forName("utf-8")));
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到["+fileName+"]文件");
        }
        return BufferedWriter;
    }



    public static List<String>[] readQuestion(String questionFileName, String answerFileName) {
        BufferedReader questionReader = GetFileInputStream(questionFileName);
        if (questionReader == null) {
            return null;
        }
        BufferedReader answerReader = GetFileInputStream(answerFileName);
        if (answerReader == null) {
            //先关闭题目文件流
            try {
                questionReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        String question;
        String answer;
        List<String>[] exercises = new List[2];
        exercises[0] = new ArrayList<>();
        exercises[1] = new ArrayList<>();
        try {
            while (true) {
                //题目
                question = questionReader.readLine();
                //答案
                answer = answerReader.readLine();

                if (question == null || answer == null) {
                    break;
                }
                exercises[0].add(question.substring(question.indexOf(',')+1));
                exercises[1].add(answer.substring(answer.indexOf(',')+1));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                questionReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                answerReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return exercises;
    }

    /**
     * 将问题答案写进文件
     *
     * @param exercises        Msp<问题，答案>
     * @param questionFileName 题目文件名
     * @param answerFileName   答案文件名
     */
    public static void writeQuestion(Map<String, String> exercises, String questionFileName, String answerFileName) {
        BufferedWriter questionWriter = GetFileOutputStream(questionFileName);
        if (questionFileName == null){
            return;
        }
        BufferedWriter answerWriter = GetFileOutputStream(answerFileName);
        if (answerWriter == null) {
            //先关闭题目文件流
            try {
                questionWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        Iterator<String> iterator = exercises.keySet().iterator();
        String question;
        int number = 1;
        try {
            while (iterator.hasNext()) {
                question = iterator.next();
                //题目
                questionWriter.write(number + "," + question + "\r\n");
                //答案
                answerWriter.write(number + "," + exercises.get(question) + "\r\n");
                number++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                questionWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                answerWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
