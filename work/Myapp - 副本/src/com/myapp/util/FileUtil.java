package com.myapp.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
            System.out.println("系统找不到[" + fileName + "]文件");
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
            System.out.println("系统找不到[" + fileName + "]文件");
        }
        return BufferedWriter;
    }


    /**
     * 读取题目和答案
     *
     * @param questionFileName
     * @param answerFileName
     * @return
     */
    public static List<String>[] readQuestion(String questionFileName, String answerFileName) {
        List<String> questions = readFile(questionFileName);
        if (questions == null) {
            return null;
        }
        List<String> answers = readFile(answerFileName);
        if (answers == null) {
            return null;
        }
        List<String>[] exercises = new List[2];
        exercises[0] = questions;
        exercises[1] = answers;

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
        if (questionFileName == null) {
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

    /**
     * 读取文件内容
     *
     * @param fileName
     * @return
     */
    public static List<String> readFile(String fileName) {
        BufferedReader bufferReader = GetFileInputStream(fileName);
        if (bufferReader == null) {
            return null;
        }
        String content;
        List<String> exercises = new ArrayList<>();
        try {
            while (true) {
                //读取内容
                content = bufferReader.readLine();

                if (content == null) {
                    break;
                }
                exercises.add(content.substring(content.indexOf(',') + 1));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return exercises;
    }

    public static void writeFile(List<String> contents, String fileName){
        BufferedWriter bufferedWriter = GetFileOutputStream(fileName);
        if (bufferedWriter == null) {
            return;
        }
        int leng = contents.size();
        try {

            for (int index = 0; index < leng; index++) {
                //写进文件
                bufferedWriter.write(index+1 + "," + contents.get(index) + "\r\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
