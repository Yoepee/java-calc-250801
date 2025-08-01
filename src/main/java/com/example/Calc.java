package com.example;

import java.util.Arrays;
import java.util.List;

public class Calc {
    private static List<String> list;

    public static int run(String expr) {
        getValue(expr);
        int result = 0;
        String type = "+";

        for(String item : list){
            String value = null;
            switch (item) {
                case "+", "-", "*" -> type = item;
                default -> value = item;
            }

            if (type != null && value != null){
                result = calc(type, result, Integer.parseInt(value));
            }
        }
        return result;
    }

    private static void getValue(String expr) {
        list = Arrays.stream(expr.split(" "))
                .map(e -> e.trim())
                .toList();
    }

    private static int calc(String type, int a, int b) {
        int result;
        switch (type) {
            case "+" -> result = plus(a, b);
            case "-" -> result = minus(a, b);
            case "*" -> result = multiply(a, b);
            default -> result = 0;
        }

        return result;
    }

    private static int plus(int a, int b) {
        return a + b;
    }

    private static int minus(int a, int b) {
        return a - b;
    }

    private static int multiply(int a, int b) {
        return a * b;
    }
}
