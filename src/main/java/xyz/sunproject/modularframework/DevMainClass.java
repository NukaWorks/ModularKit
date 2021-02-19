package xyz.sunproject.modularframework;

import xyz.sunproject.modularframework.core.Modular;
import xyz.sunproject.modularframework.modules.ModuleTest;

public class DevMainClass {

    public static void main(String[] args) throws Exception {
        Modular modular = Modular.getInstance();
        ModuleTest testModule = new ModuleTest();

        modular.getModManager().registerModule(testModule);


        System.out.println(modular.getModManager().getModulesCollection());

        modular.getModManager().runModule(testModule);
        Thread.sleep(2000);
        modular.getModManager().unregisterModule(testModule);

        System.out.println(modular.getModManager().getModulesCollection());
        System.out.println(testModule.getModuleState());
    }
}
