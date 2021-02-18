package xyz.sunproject.modularframework.modules;

import xyz.sunproject.modularframework.core.ModularModule;
import xyz.sunproject.modularframework.core.events.RunEvent;

public class ModuleTest extends ModularModule {

    public ModuleTest() throws Exception {
        super("TestModule", "8460d607");

        System.out.println("Lol, It Works !!!");
    }

    @Override
    public void runEvent() {
        System.out.println("Hello World !");
    }
}
