package xyz.sunproject.modularframework.testModules;

import xyz.sunproject.modularframework.core.ModularModule;
import xyz.sunproject.modularframework.core.events.ModuleStatus;

public class ModuleTest extends ModularModule {

    public ModuleTest() throws Exception {
        super("TestModule", "8460d607", "Sundev79", "1.0.0");

        System.out.println("Lol, It Works !!!");
    }

    @Override
    public void runEvent() {
        while (true) {
            System.out.println("Hello World !");
            System.out.println(getModuleState());
/*
            try {
                System.out.println(Modular.getInstance().getModManager().findModuleByUuiD("8460d607").getModuleState());
            } catch (Exception e) {
                e.printStackTrace();
            }
*/
            if (getModuleState() == ModuleStatus.STOPPING) break;

            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }


        }
    }
}
