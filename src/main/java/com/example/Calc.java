package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calc {
    private static List<String> list;

    public static int run(String expr) {
        getValue(expr);

        while(list.size() > 1){
            list = getNextList(list);
        }

        return list.stream().mapToInt(Integer::parseInt).sum();
    }

    private static void getValue(String expr) {
        list = Arrays.stream(expr.split(" "))
                .map(e -> e.trim())
                .toList();
    }

    private static List<String> getNextList(List<String> list) {
        List<String> newList = new ArrayList<>();
        newList.add("0");
        String type = "+";
        String prevValue = "0";
        String prevType = "+";
        String prevResult = "0";
        for(String item : list){
            String value = null;
            switch (item) {
                case "+", "-", "*":
                    type = item;
                    break;
                default:
                    value = item;
                    break;
            }

            if (value != null){
                if (!type.equals("*")){
                    int listSize = newList.size();
                    int left = newList.get(listSize - 1).equals("0") ? 0 : Integer.parseInt(newList.get(listSize - 1));
                    int right = value.equals("0") ? 0 : Integer.parseInt(value);
                    newList.remove(listSize - 1);
                    prevResult = String.valueOf(left);
                    String result = String.valueOf(calc(type, left, right));
                    newList.add(String.valueOf(calc(type, left, right)));
                } else {
                    int left = prevValue.equals("0") ? 0 : Integer.parseInt(prevValue);
                    int right = value.equals("0") ? 0 : Integer.parseInt(value);
                    String result = String.valueOf(calc(type, left, right));
                    newList.add(result);
                }
            } else if (type.equals("*") && !prevType.equals("*")){
                int listSize = newList.size();
                newList.remove(listSize - 1);
                newList.add(prevResult);
                newList.add(prevType);
            }

            if (value != null) prevValue = value;
            if(type != null) prevType = type;
        }

        return newList;
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
