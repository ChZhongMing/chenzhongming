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
    }

    @Override
    public String toString() {
        if (this.integer > 0) {
            return this.integer + "’" + this.molecule + "/" + denominator;
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
