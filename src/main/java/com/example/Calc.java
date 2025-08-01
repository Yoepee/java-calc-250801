package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calc {
    public static int run(String expr) {
        List<String> list = parse(expr);
        while (list.contains("(") || list.contains("-(")) {
            list = getNextList(list);
        }

        return calc(list);
    }

    private static List<String> parse(String expr) {
        return Arrays.stream(expr.replaceAll("\\(", "( ")
                        .replaceAll("\\)", " )")
                        .split(" "))
                .map(String::trim)
                .filter(e -> !e.isEmpty())
                .toList();
    }

    private static List<String> getNextList(List<String> list) {
        List<String> newList = new ArrayList<>();
        int openIndex = -1;
        boolean isMinusStart = false;
        for (int i = 0; i < list.size(); i++) {
            String current = list.get(i);
            if (current.equals("(")) {
                openIndex = i;
            } else if (current.equals("-(")){
                openIndex = i;
                isMinusStart = true;
            } else if (current.equals(")")) {
                List<String> openList = list.subList(openIndex + 1, i);
                int value = calc(openList);
                newList.addAll(list.subList(0, openIndex));
                newList.add(String.valueOf(value * (isMinusStart ? -1 : 1)));
                newList.addAll(list.subList(i+1, list.size()));
                break;
            }
        }

        return newList.isEmpty() ? list : newList;
    }

    private static int calc(List<String> list) {
        List<String> newList = new ArrayList<>(list);
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).equals("*")) {
                int a = Integer.parseInt(newList.get(i - 1));
                int b = Integer.parseInt(newList.get(i + 1));
                newList.set(i - 1, String.valueOf(multiply(a, b)));
                newList.remove(i);
                newList.remove(i);
                i--;
            }
        }

        int result = Integer.parseInt(newList.get(0));
        for (int i = 1; i < newList.size(); i += 2) {
            if (newList.get(i).equals("+")) result = plus(result, Integer.parseInt(newList.get(i + 1)));
            else result = minus(result, Integer.parseInt(newList.get(i + 1)));
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
