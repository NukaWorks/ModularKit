package com.github.sunproject.org.modularframework.internal.console;


import java.util.Scanner;

public class ModularCInputs {

    private Scanner modSCL;
    private static ModularCInputs instance;


    private ModularCInputs() {
        modSCL = new Scanner(System.in);
    }

    public static ModularCInputs getConsoleInputs() {
        if (instance == null) instance = new ModularCInputs();
        return instance;
    }

    public Scanner getScanner() {
        return modSCL;
    }

    public void destroy() {
        modSCL.close();
        instance = null;
    }
}