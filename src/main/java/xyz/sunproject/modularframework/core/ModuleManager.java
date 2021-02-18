package xyz.sunproject.modularframework.core;

import java.util.HashMap;
import java.util.UUID;

public class ModuleManager {

    private HashMap<String, ModularModule> moduleMap = new HashMap<>();
    private String dynUuiD = null;
    private static ModuleManager instance = null;

    private ModuleManager() {}

    public void runModule(Class<?> module) {
        ModularModule runMod = null;
        try { runMod = (ModularModule) module.newInstance(); }
        catch (InstantiationException | IllegalAccessException e) { e.printStackTrace(); }

        ModularModule finalRunMod = runMod;
        Thread runThread = new Thread(() -> {
            try {
                finalRunMod.exec();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        refreshDynUuiD();
        runThread.setName("Mod_" + runMod.getModuleName() + "#" + dynUuiD + "_" + runMod.getUuid());

        //Starting the module...
        runThread.start();
    }

    public ModularModule findModuleByUuiD(String uuid) throws Exception {
        if (uuid.length() == 8) {
            if (moduleMap.containsKey(uuid)) return moduleMap.get(uuid);
        } else throw new Exception("uuid is incorrect");
        return null;
    }


    public static ModuleManager getInstance() {
        if (instance == null) instance = new ModuleManager();
        return instance;
    }

    private void refreshDynUuiD() {
        dynUuiD = UUID.randomUUID().toString().split("-")[1];
    }

    public String getDynUuiD() {
        return dynUuiD;
    }
}
