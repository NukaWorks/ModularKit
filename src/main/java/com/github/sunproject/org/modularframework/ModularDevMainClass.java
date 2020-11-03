package com.github.sunproject.org.modularframework;

import com.github.sunproject.org.modularframework.init.ModularInit;
import java.util.Scanner;

class ModularDevMainClass {


    @SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

        System.err.println("Warning ! executing ModularFramework with this class is for development and for test purposes only.");
        System.err.println("Please type \"yes\" to start the framework...\n");


        if (new Scanner(System.in).nextLine().equalsIgnoreCase("yes")) {
            System.err.println("Proceeding anyway ...\n\n");
            ModularInit.initModular();
        }
    }
}
