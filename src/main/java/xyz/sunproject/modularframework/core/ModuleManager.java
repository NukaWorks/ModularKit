package xyz.sunproject.modularframework.core;

import java.util.UUID;

public class ModuleManager {

    private String dynUuiD = null;
    private final ModularSource modSource;

    public ModuleManager(ModularSource source) {
        if (source != null) modSource = source;
        else throw new NullPointerException("Source cannot be null.");
    }

    public boolean runModule(ModularModule module) throws Exception {
        if (modSource.getModuleMap().containsKey(module.getUuid())) {
            Thread runThread = new Thread(() -> {
                try {
                    module._exec();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            });

            refreshDynUuiD();
            runThread.setName("Mod_" + module.getModuleName() + "#" + dynUuiD + "_" + module.getUuid());

            //Starting the module...
            runThread.start();
            return true;
        } else throw new Exception("Module not registered !");
    }

    public void stopModule(ModularModule module, @Deprecated boolean forceStop) throws Exception {
        module._stop();
        if (forceStop) module._kill();
    }

    public ModularModule findModuleByUuiD(String uuid) throws Exception {
        if (uuid.length() == 8) {
            if (modSource.getModuleMap().containsKey(uuid)) return modSource.getModuleMap().get(uuid);
        } else throw new Exception("uuid is incorrect");
        return null;
    }

    private void refreshDynUuiD() {
        dynUuiD = UUID.randomUUID().toString().split("-")[1];
    }

    public String getDynUuiD() {
        return dynUuiD;
    }
}