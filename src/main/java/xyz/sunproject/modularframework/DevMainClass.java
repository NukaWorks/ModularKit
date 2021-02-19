package xyz.sunproject.modularframework;

import xyz.sunproject.modularframework.core.ModularModule;
import xyz.sunproject.modularframework.core.manager.ModuleManager;
import xyz.sunproject.modularframework.modules.ModuleTest;

public class DevMainClass {

    public static void main(String[] args) throws Exception {

        // ModuleManager.getInstance().runModule(ModuleTest.class);

        System.out.println(ModuleTest.class.getSuperclass());

        ModuleManager.getInstance().registerModule(ModuleTest.class);
        ModularModule testMod = ModuleManager.getInstance().findModuleByUuiD("8460d607");
        System.out.println(ModuleManager.getInstance().findModuleByUuiD("8460d607").getModuleState());
        ModuleManager.getInstance().runModule(testMod);

        System.out.println(ModuleManager.getInstance().findModuleByUuiD("8460d607").getModuleState());

        Thread.sleep(5000);
        ModuleManager.getInstance().stopModule(testMod, false);
        Thread.sleep(2000);
        System.out.println(ModuleManager.getInstance().findModuleByUuiD("8460d607").getModuleState());

        System.out.println(ModuleManager.getInstance().getModulesCollection().getUnmodifiableModuleCollection());
        System.out.println("Unregistering the TestModule ...");

        System.out.println(ModuleManager.getInstance().unregisterModule(testMod));

        System.out.println(ModuleManager.getInstance().getModulesCollection().getUnmodifiableModuleCollection());
    }
}
