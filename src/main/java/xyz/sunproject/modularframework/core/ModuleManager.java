package xyz.sunproject.modularframework.core;

import xyz.sunproject.modularframework.core.events.ModuleStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ModuleManager {

    protected HashMap<String, ModularModule> moduleMap = new HashMap<>();
    private String dynUuiD = null;
    private static final ModuleManager instance = new ModuleManager();

    private ModuleManager() {}

    public boolean runModule(ModularModule module) throws Exception {
        if (moduleMap.containsKey(module.getUuid())) {
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

    /**
     * Registering a module with the ModuleClass.
     * @param module Class Object contains a ModularModule Object.
     * @throws Exception
     */
    public boolean registerModule(Class<?> module) throws Exception {
        ModularModule mod = (ModularModule) module.newInstance();
        return registerModule(mod);
    }

    /**
     * Registering a module with the ModularModule Object.
     * @param module Module Object
     * @throws Exception
     */

    public boolean registerModule(ModularModule module) throws Exception {
        if (!moduleMap.containsKey(module.getUuid())) {
            moduleMap.put(module.getUuid(), module);
            return true;
        } else throw new Exception("Module already instantiated !");
    }

    public boolean unregisterModule(ModularModule module) throws Exception {
        System.out.println(module.getModuleState());
        if (moduleMap.containsKey(module.getUuid())) {
            if (module.getModuleState() == ModuleStatus.RUNNING) throw new Exception("Failed to unregister the module : the module is running.");
            else {
                moduleMap.remove(module.getUuid(), module);
                return true;
            }
        }
        return false;
    }

    public ModularModule findModuleByUuiD(String uuid) throws Exception {
        if (uuid.length() == 8) {
            if (moduleMap.containsKey(uuid)) return moduleMap.get(uuid);
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

    public Map<String, ModularModule> getModulesCollection() {
        return Collections.unmodifiableMap(moduleMap);
    }
}
