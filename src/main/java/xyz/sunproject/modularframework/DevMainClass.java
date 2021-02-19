package xyz.sunproject.modularframework;

import xyz.sunproject.modularframework.core.Modular;
import xyz.sunproject.modularframework.testModules.ModuleTest;

public class DevMainClass {

    public static void main(String[] args) throws Exception {
        Modular modular = Modular.getInstance();
        ModuleTest testModule = new ModuleTest();

        modular.getModManager().registerModule(testModule);

        modular.getModManager().runModule(testModule);

        Thread.sleep(3000);

        modular.getModManager().stopModule(testModule, false);
        Thread.sleep(3000);
        modular.getModManager().unregisterModule(testModule);

        System.out.println("state 1: " + modular.getModManager().getModulesCollection());

        System.out.println("state 2: " + testModule.getModuleState());
    }
}
