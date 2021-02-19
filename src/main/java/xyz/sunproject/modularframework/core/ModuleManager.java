package xyz.sunproject.modularframework.core;

import java.util.UUID;

public class ModuleManager {

    private final ModularSource defaultSource = new ModularSource("ab1f4a59");
    private String dynUuiD = null;
    private static final ModuleManager instance = new ModuleManager();


    private ModuleManager() {}


    public boolean runModule(ModularModule module, ModularSource source) throws Exception {
        ModularSource runSource = defaultSource;
        if (source != null) runSource = source;

        if (runSource.getModuleMap().containsKey(module.getUuid())) {
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

    public ModularModule findModuleByUuiD(String uuid, ModularSource source) throws Exception {
        ModularSource findSource = defaultSource;
        if (source != null) findSource = source;

        if (uuid.length() == 8) {
            if (findSource.getModuleMap().containsKey(uuid)) return findSource.getModuleMap().get(uuid);
        } else throw new Exception("uuid is incorrect");
        return null;
    }

    public ModularSource findSourceByUuiD(String uuid) throws Exception {
        if (uuid.length() == 8) {
            if (defaultSource.getSourceMap().containsKey(uuid)) return defaultSource.getSourceMap().get(uuid);
        } else throw new Exception("uuid is incorrect");
        return null;
    }

    protected static ModuleManager getInstance() {
        return instance;
    }

    private void refreshDynUuiD() {
        dynUuiD = UUID.randomUUID().toString().split("-")[1];
    }

    public String getDynUuiD() {
        return dynUuiD;
    }

    protected ModularSource getDefaultSource() {
        return defaultSource;
    }
}
