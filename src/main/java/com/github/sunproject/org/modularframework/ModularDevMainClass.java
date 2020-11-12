package com.github.sunproject.org.modularframework;

import com.github.sunproject.org.modularframework.console.ModularCInputs;
import com.github.sunproject.org.modularframework.init.ModularInit;
import java.util.Scanner;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Launch ModularFramework (for test purposes).
 */

class ModularDevMainClass {


    @SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
        System.err.println("Warning ! executing ModularFramework with this class is for development and for test purposes only.");
        System.err.println("Please type \"yes\" to start the framework...\n");


        if (ModularCInputs.getModularConsoleInputs().getScanner().nextLine().equalsIgnoreCase("yes")) {
            System.err.println("Proceeding anyway ...\n\n");
            ModularInit.initModular();
        }
    }
}
