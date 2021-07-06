package org.nutdevs.modularkit;

import org.nutdevs.modularkit.core.ModularModule;

public class ModuleLidl extends ModularModule {

    public ModuleLidl() throws Exception {
        super("ModuleLidl", "452457c5", "Sundev79", "1.0.0");
    }

    @Override
    public void runEvent() {
        System.out.println("Hello Lidl !");
    }
}
