package com.movieapp;

public class LowCoverageSample {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Division by zero");
        return a / b;
    }

    public int mod(int a, int b) {
        return a % b;
    }

    public int square(int a) {
        return a * a;
    }

    public int cube(int a) {
        return a * a * a;
    }

    public int negate(int a) {
        return -a;
    }

    public int abs(int a) {
        return a < 0 ? -a : a;
    }
}
