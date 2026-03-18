package com.movieapp;

public class AnotherBuggySample {
    public static void main(String[] args) {
        AnotherBuggySample abs = new AnotherBuggySample();
        abs.arrayOutOfBoundsBug();
        abs.infiniteLoopBug();
    }

    // Array index out of bounds bug
    public void arrayOutOfBoundsBug() {
        int[] numbers = {1, 2, 3};
        // This will throw ArrayIndexOutOfBoundsException
        System.out.println(numbers[5]);
    }

    // Infinite loop bug
    public void infiniteLoopBug() {
        int i = 0;
        while (i >= 0) { // This loop never ends
            System.out.println("Infinite loop: " + i);
            // i is never incremented or decremented
            if (i == 10) break; // This line is unreachable
        }
    }
}
