package org.nutdevs.modularkit.core;

import org.nutdevs.modularkit.core.ex.ModSourceEx;
import org.nutdevs.modularkit.core.ex.ModUuidEx;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager {
    private final ModularSource modSource;
    private final ArrayList<ModularModule> modulesDependencies = new ArrayList<>();

    public ModuleManager(ModularSource source) throws ModSourceEx {
        if (source != null) modSource = source;
        else throw new ModSourceEx("Source cannot be null.");
    }

    public synchronized boolean runModule(ModularModule module) throws Exception {
        HashMap<String, ModularModule> runMap = (HashMap<String, ModularModule>) modSource.getModuleMap();
        if (!runMap.isEmpty()) {
            if (runMap.containsKey(module.getUuid())) {
                Thread runThread = new Thread(() -> {
                    try {
                        module.exec();
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
        module.stop();
        if (forceStop) module.kill();
    }

    public ModularModule findModuleByUuiD(String uuid) throws ModUuidEx {
        if (uuid.length() == 8) {
            if (modSource.getModuleMap().containsKey(uuid))
                return modSource.getModuleMap().get(uuid);
        } else throw new ModUuidEx("uuid is incorrect");
        return null;
    }

    public void setDepends(ModularModule... modDeps) {
        for (ModularModule mod : modDeps)
            if (!modulesDependencies.contains(mod)) {
                modulesDependencies.add(mod);
                System.out.println(mod.getModuleName());
            }
    }

}