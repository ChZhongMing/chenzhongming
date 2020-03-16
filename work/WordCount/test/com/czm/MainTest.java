package com.czm;

import org.junit.Test;



public class MainTest {

    @Test
    public void CharCountTest(){
        System.out.println("字符数:"+Main.CharCount("测试文件/oneChar.c"));
        System.out.println("字符数:"+Main.CharCount("测试文件/CodeText.txt"));
    }

    @Test
    public void FolderCountTest(){

//        Main.FolderCount("-a","./测试文件",".txt");
        Main.FolderCount("-a","./测试文件","e.txt");
//        Main.FolderCount("-a","./测试文件",".java");
    }

    @Test
    public void ComplexTest(){
        Main.Complex("测试文件/CodeText.txt");
    }
}