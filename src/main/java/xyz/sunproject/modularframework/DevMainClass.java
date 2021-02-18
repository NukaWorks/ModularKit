package xyz.sunproject.modularframework;

import xyz.sunproject.modularframework.core.ModularModule;
import xyz.sunproject.modularframework.core.ModuleManager;
import xyz.sunproject.modularframework.modules.ModuleTest;

public class DevMainClass {

    public static void main(String[] args) throws Exception {

        ModuleManager.getInstance().runModule(ModuleTest.class);

    }
}
