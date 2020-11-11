package com.github.sunproject.org.modularframework.console;


import java.util.Scanner;

public class ModularConsoleInputs {

    private Scanner modSCL;
    private static ModularConsoleInputs instance;


    private ModularConsoleInputs() {
        modSCL = new Scanner(System.in);
    }

    public static ModularConsoleInputs getModularConsoleInputs() {
        if (instance == null) instance = new ModularConsoleInputs();
        return instance;
    }

    public Scanner getModSCL() {
        return modSCL;
    }
}
