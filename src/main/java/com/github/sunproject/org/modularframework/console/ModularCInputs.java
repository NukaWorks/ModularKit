package com.github.sunproject.org.modularframework.console;


import java.util.Scanner;

public class ModularCInputs {

    private Scanner modSCL;
    private static ModularCInputs instance;


    private ModularCInputs() {
        modSCL = new Scanner(System.in);
    }

    public static ModularCInputs getModularConsoleInputs() {
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