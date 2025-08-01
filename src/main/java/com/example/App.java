package com.example;

import java.util.Scanner;

public class App {
    public void run(){
        System.out.println("== 계산기 ==");
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine();

        System.out.println(new Calc().run(expr));
    }
}
