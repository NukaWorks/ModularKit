package org.nutdevs.modularkit;

import org.nutdevs.modularkit.core.ModularModule;

public class AnotherModule extends ModularModule {

    public AnotherModule() throws Exception {
        super("ModuleLidl", "452457c5", "Sundev79", "1.0.0");
    }

    @Override
    public void runEvent() {
        System.out.println("Hello Lidl !");
    }
}
