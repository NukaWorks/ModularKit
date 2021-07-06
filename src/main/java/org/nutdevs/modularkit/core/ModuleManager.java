package org.nutdevs.modularkit.core;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager {
    private final ModularSource modSource;
    private ArrayList<ModularModule> modulesDependencies = new ArrayList<>();

    public ModuleManager(ModularSource source) {
        if (source != null) modSource = source;
        else throw new NullPointerException("Source cannot be null.");
    }

    public synchronized boolean runModule(ModularModule module) throws Exception {
        HashMap<String, ModularModule> runMap = (HashMap<String, ModularModule>) modSource.getModuleMap();
        if (!runMap.isEmpty()) {
                if (runMap.containsKey(module.getUuid())) {
                    Thread runThread = new Thread(() -> {
                        try {
                            module._exec();
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    });

                    runThread.setName("Mod_" + module.getModuleName() + "_" + module.getUuid());
                    //Starting the module...
                    runThread.start();
                    return true;
                } else throw new Exception("Module not registered !");
        } else throw new Exception("Module not found !");
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

    public void setDepends(ModularModule... modDeps) {
        for (ModularModule mod : modDeps) if (!modulesDependencies.contains(mod)) {
            modulesDependencies.add(mod);
            System.out.println(mod.getModuleName());
        }
    }

}