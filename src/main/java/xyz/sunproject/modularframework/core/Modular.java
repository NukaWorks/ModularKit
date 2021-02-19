package xyz.sunproject.modularframework.core;

public class Modular {

    private static final Modular instance = new Modular();
    private final ModuleManager modManager = ModuleManager.getInstance();

    private Modular() {}

    public static Modular getInstance() {
        return instance;
    }

    public final ModuleManager getModManager() {
        return modManager;
    }
}
