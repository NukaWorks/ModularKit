package xyz.sunproject.modularframework.core;


import java.io.File;

public class DevMainClass {

    public static void main(String[] args) throws Exception {
        ModularSource mainSource = new ModularSource("e1797cef", new File("/home/sundev79/Bureau/modules/"));

        mainSource.getModuleManager().runModule(mainSource.getModuleManager().findModuleByUuiD("9da15b11"));
    }

/*
    public static void registerCommands() {
        modularShell.setPrompt("ModularShell@" + modularShell.getVersion() + " |~$ ");
        ModularCommand testCmd = new ModularCommand("hello");
        testCmd.setEventHandler(() -> {
            System.out.println("Hello World !");
        });

        ModularCommand.registerCommand(testCmd);
    }
*/

}
