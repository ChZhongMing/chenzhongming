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
     *分子
     */
    private int molecule;
    /**
     * 分母
     */
    private int denominator;

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
