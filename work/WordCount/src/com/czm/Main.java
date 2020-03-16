package com.czm;


import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author czm
 */
public class Main {

    /**
     * 正确传参
     */
    private static int FLAG_TWO = 2;

    /**
     * 出错误返回值
     */
    private static int ERROR_NUM = -1;

    public static void main(String[] args) {

        if(args.length >= FLAG_TWO){
            switch (args[0]){
//                字符数
                case "-c":
                    long startC = System.currentTimeMillis();
                    System.out.println("字符数:"+CharCount(args[1]));
                    System.out.printf("耗时%d（毫秒）\n", System.currentTimeMillis()-startC);
                    break;
//                 单词数
                case "-w":
                    long startW = System.currentTimeMillis();
                    System.out.println("单词数："+WordCount(args[1]));
                    System.out.printf("耗时%d（毫秒）\n", System.currentTimeMillis()-startW);
                    break;
//                  行数
                case "-l":
                    long startL = System.currentTimeMillis();
                    System.out.println("行数："+LineCount(args[1]));
                    System.out.printf("耗时%d（毫秒）\n", System.currentTimeMillis()-startL);
                    break;
//                  递归处理目录下符合条件的文件。
                case "-s":
                    if(args.length == 4){
                        long startS = System.currentTimeMillis();
                        FolderCount(args[1], args[2], args[3]);
                        System.out.printf("耗时%d（毫秒）\n", System.currentTimeMillis()-startS);
                    }else{
                        System.out.println("正确格式为：wc.exe -s 【操作】 文件夹路径 文件名");
                    }
                    break;
//                  返回更复杂的数据（代码行 / 空行 / 注释行）。
                case "-a":
                    long startA = System.currentTimeMillis();
                    Complex(args[1]);
                    System.out.printf("耗时%d（毫秒）\n", System.currentTimeMillis()-startA);
                    break;
                default:
                    System.out.println("请输入正确参数【操作】+文件路径");
            }
        }else {
            System.out.println("请输入正确参数【操作】+文件路径");
        }

    }

    private static BufferedReader GetFileInmputStream(String fileName){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
           System.out.println("系统找不到指定路径文件");
        }
        return bufferedReader;
    }

    /**
     * 计算字符数
     * @param fileName
     * @return
     */
    public static int CharCount(String fileName){
        BufferedReader in = null;
        int count = 0;
        in = GetFileInmputStream(fileName);
        if (in == null){
            return ERROR_NUM;
        }
        int result = ERROR_NUM;
        try {

            while((result = in.read()) != -1){
                if(result != '\r' && result!='\n'){
                    count++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        return count;
    }

    /**
     * 统计单词数
     * @param fileName
     * @return
     */
    public static int WordCount(String fileName){
        BufferedReader in = null;
        int count = 0;
        in = GetFileInmputStream(fileName);
        if (in == null){
            return ERROR_NUM;
        }
        try {
            String str = null;
            while((str = in.readLine()) != null){
                //\\s+表示 空格,回车,换行等空白符
                String[] split = str.split("\\s+");
                count += split.length;
            }


        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return count;
    }

    public static int LineCount(String fileName){
        BufferedReader read = null;
        int count = 0;
        read = GetFileInmputStream(fileName);
        if (read == null){
            return ERROR_NUM;
        }
        try {
            while (read.readLine() != null){
                count++;
            }

//                速度较慢
//            Iterator<String> iterator = read.lines().iterator();
//            while (iterator.hasNext()){
//                iterator.next();
//                count++;
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(read != null){
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return count;
    }

    /**
     * 递归文件夹
     * @param folderName
     * @return
     */
    public static void FolderCount(String operation, String folderName, String fileName){
        File file = new File(folderName);
        if(file.isDirectory()){
            int index = fileName.lastIndexOf('.');
            if(index >= 0){
                String extension = fileName.substring(index);
                String frontName = fileName.substring(0, index);
                File[] fileList = file.listFiles();
                if (fileList.length > 0){
//                    String regex = ".*"+name+"\\.+"+extension+"$";
//                    for (String f:fileList){
//                        System.out.println(Pattern.matches(regex, f));
//                        System.out.println(f);
//                    }

//                    递归遍历
                    if (!ReadFold(operation, file.getName(), fileList, frontName, extension)){
                        System.out.println("该目录下无对应文件");
                    }
                }else{
                    System.out.println("该目录为空");
                }
            }

        }else{
            System.out.println("参数不是文件夹或路径错误");
        }

    }

    /***
     * 递归子文件
     * @param operation
     * @param fileList
     * @param frontName
     * @param extension
     */
    public static boolean ReadFold(String operation, String parentName, File[] fileList, String frontName, String extension){
        boolean flag = false;
        boolean childFlag = false;
        String fName = null;
        for (File f:fileList){
            //    子文件是文件
            if(f.isFile()){
                fName = f.getName();
                if (fName.indexOf(frontName) >=0 && fName.endsWith(extension)) {
                    switch (operation) {
                        case "-c":
                            System.out.println("【"+parentName+"】下的【"+fName+"】文件的字符数:" + CharCount(f.getPath()));
                            break;
                        case "-w":
                            System.out.println("【"+parentName+"】下的【"+fName+"】文件的单词数：" + WordCount(f.getPath()));
                            break;
                        case "-l":
                            System.out.println("【"+parentName+"】下的【"+fName+"】文件的行数：" + LineCount(f.getPath()));
                            break;
                        case "-a":
                            System.out.println("【"+parentName+"】下的【"+fName+"】文件的信息：");
                            Complex(f.getPath());
                            break;
                        default:
                            System.out.println("第二参数错误（-c,-w,-l）");
                            return false;
                    }
                    flag = true;
                }
            }else{  //子文件是目录
                childFlag = ReadFold(operation, f.getName(), f.listFiles(), frontName, extension) || childFlag ;
            }

        }
        return (flag || childFlag);
    }

    /**
     * 复杂查询（代码行 / 空行 / 注释行）
     * @param filename
     */
    public static void Complex(String filename){
        int codeLine = 0;
        int annotationLine = 0;
        int blankLine = 0;
//        判断是否处于多行注解内
        boolean flag = false;
        BufferedReader bufferedReader = GetFileInmputStream(filename);
        if (bufferedReader == null){
            return;
        }
        String strLine = null;
        String newLine = null;
        try {
            while ((strLine = bufferedReader.readLine())!= null){
                if (flag) {
                    if (strLine.endsWith("*/")){
                        flag = false;
                    }
                    annotationLine++;
                }else {
//                  去除空格
                    newLine = strLine.replaceAll("\\s*", "");
                    if("".equals(newLine)){
                        //空行
                        blankLine++;
                    }else if (newLine.startsWith("/*")){
                        //注释行
                        if (!newLine.endsWith("*/")){
                            //去除/*单行注释*/情况
                            flag = true;
                        }
                        annotationLine++;
                    }else if (newLine.startsWith("//") || newLine.startsWith("}//")){
                        //注释行
                        annotationLine++;
                    }else {
                        //代码行
                        codeLine++;
                    }
                }
            }
            System.out.println("代码行："+codeLine);
            System.out.println("空行："+blankLine);
            System.out.println("注释行："+annotationLine);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
