package com;

public class DemoBuggy {
    public static void main(String[] args) {
        // Hardcoded credentials (bad practice)
        String username = "admin";
        String password = "12345";

        // Hardcoded magic number
        int maxAttempts = 3;

        System.out.println("This code will throw an exception!");
        int[] arr = new int[2];
        // This will throw ArrayIndexOutOfBoundsException
        System.out.println(arr[5]);

        // Unused variable
        int unused = 42;

        // Null pointer dereference
        String s = null;
        System.out.println(s.length());
    }
}
