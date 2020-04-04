package com.myapp.entity;

/**
 * 带分数
 */
public class Fraction {
    /**
     * 带分数的整数部分
     */
    private int integer;

    /**
     * 分子
     */
    private int molecule;
    /**
     * 分母
     */
    private int denominator;

    /**
     * @param integer     整数
     * @param molecule    分子
     * @param denominator 分母
     */
    public Fraction(int integer, int molecule, int denominator) {
        this.integer = integer;
        this.molecule = molecule;
        this.denominator = denominator;
    }

    /**
     * 加一个整数
     *
     * @param num
     */
    public void add(int num) {
        this.integer += num;

    }

    /**
     * 加一个分式
     *
     * @return
     */
    public void add(Fraction fraction) {
        this.integer += fraction.getInteger();
        if (this.denominator == fraction.getDenominator()) {
            this.molecule += fraction.getMolecule();
        } else {
            this.molecule = this.molecule * fraction.getDenominator() + fraction.getMolecule() * this.denominator;
            this.denominator *= fraction.getDenominator();
        }
    }

    /**
     * 减法一个整数
     * @param num
     */
    public boolean reduce(int num) {
        this.integer -= num;
        if (this.integer < 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 减一个分式
     *
     * @param fraction
     */
    public boolean reduce(Fraction fraction) {
        this.integer -= fraction.getInteger();
        if (this.denominator == fraction.getDenominator()) {
            this.molecule -= fraction.getMolecule();
        } else {
            this.molecule = this.molecule * fraction.getDenominator() - fraction.getMolecule() * this.denominator;
            this.denominator *= fraction.getDenominator();
        }

        //分子小于等于0
        while (this.molecule < 0) {
            this.molecule += this.denominator;
            this.integer--;
        }
        if (this.integer < 0) {
            return false;
        } else {
            return true;
        }
    }



    /**
     * 乘一个整数
     *
     * @param num
     */
    public void ride(int num) {
        if (this.integer != 0) {
            this.molecule += this.integer * this.denominator;
            this.integer = 0;
        }
        this.molecule *= num;

    }

    /**
     * 乘一个分式
     *
     * @param fraction
     */
    public void ride(Fraction fraction) {
        if (this.integer != 0) {
            this.molecule += this.integer * this.denominator;
            this.integer = 0;
        }

//        if(fraction.getInteger() != 0) {
//            fraction.setMolecule(fraction.getInteger() * fraction.getDenominator() +fraction.getMolecule());
//            fraction.setInteger(0);
//        }
//        this.molecule *= fraction.molecule;

        //不改参数
        this.molecule *= fraction.getInteger() * fraction.getDenominator() +fraction.getMolecule();

        this.denominator *= fraction.getDenominator();

    }

    /**
     * 除以一个整数
     * @param num
     */
    public boolean divide(int num){
        if (num == 0) {
            return false;
        }
        this.molecule += this.integer * this.denominator;
        this.integer = 0;
        this.denominator *= num;
        return true;
    }

    /**
     * 除以一个分式
     * @param fraction
     */
    public boolean divide(Fraction fraction){
        if (fraction.getVaule() == 0) {
            return false;
        }
        if (this.integer != 0) {
            this.molecule += this.integer * this.denominator;
            this.integer = 0;
        }
        if(fraction.getInteger() != 0) {
            this.denominator *= fraction.getInteger() * fraction.getDenominator() +fraction.getMolecule();
        }else {
            this.denominator *= fraction.getMolecule();
        }
        this.molecule *= fraction.getDenominator();
        return true;
    }

    /**
     * 求两数最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    private static int getCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            int remainder = a % b;
            a = b;
            b = remainder;
            return getCommonDivisor(a, b);
        }
    }

    /**
     * 化简
     */
    public void simplification(){
        //化简
        if (this.molecule > this.denominator) {
            this.integer += this.molecule / this.denominator;
            this.molecule = this.molecule % this.denominator;
        }
        //约分
        int commonDivisor = getCommonDivisor(this.denominator, this.molecule);
        this.denominator /= commonDivisor;
        this.molecule /= commonDivisor;
    }

    @Override
    public String toString() {
        simplification();
        if (this.molecule == 0){
            //分子为0
            return this.integer+"";
        } else if (denominator == 1) {
            //分母为1
            return (this.integer + this.molecule) + "";
        } else if (this.integer != 0) {
            return this.integer + "'" + this.molecule + "/" + denominator;
        } else {
            return this.molecule + "/" + denominator;
        }
    }

    /**
     * 返回小数数值
     *
     * @return
     */
    public double getVaule() {
        return this.integer + 1.0 * this.molecule / this.denominator;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public int getMolecule() {
        return molecule;
    }

    public void setMolecule(int molecule) {
        this.molecule = molecule;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }


}
