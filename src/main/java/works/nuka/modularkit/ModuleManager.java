package works.nuka.modularkit;

import works.nuka.modularkit.events.ModuleStatus;
import works.nuka.modularkit.ex.ModRegisterEx;
import works.nuka.modularkit.ex.ModRunEx;
import works.nuka.modularkit.ex.ModSourceEx;
import works.nuka.modularkit.ex.ModUuidEx;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings({"UnusedReturnValue", "unused"})

public class ModuleManager {
    private final ModularSource modSource;
    private final ArrayList<ModularModule> modulesDependencies = new ArrayList<>();

    /**
     * The ModuleManager - Manage your Modules !
     *
     * @param source - Give a ModularSource which contains all modules.
     * @throws ModSourceEx - Cause a ModSourceEx if the source is null.
     * @since 1.0
     */

    public ModuleManager(ModularSource source) throws ModSourceEx {
        if (source != null)
            modSource = source;
        else
            throw new ModSourceEx("a ModularSource cannot be null.");
    }

    /**
     * Run the Module
     *
     * @param module - Give the Module needed to run.
     * @return Return true if the runModule operation is successful.
     * @throws ModRegisterEx - Return a ModRegisterEx if Module Registration fails.
     * @since 1.0
     */

    public synchronized boolean runModule(ModularModule module) throws ModRegisterEx {
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
                // Starting the module...
                runThread.start();
                return true;
            } else
                throw new ModRegisterEx("the module is not registered !");
        } else
            throw new ModRegisterEx("Module not found :/");
    }

    /**
     * Stop the Module
     *
     * @param module    - Give the Module Object to stop.
     * @param forceStop - Force Stop a Module.
     * @throws ModRunEx - Return a ModRunEx if an error occur.
     * @since 1.0
     */

    public void stopModule(ModularModule module, @Deprecated boolean forceStop) throws ModRunEx {
        module.setModuleStatus(ModuleStatus.STOPPING);
        module.stop();
        if (forceStop)
            module.kill();
    }

    public void stopModule(String uuid, @Deprecated boolean forceStop) throws ModRunEx {
        try {
            ModularModule mod = findModuleByUuiD(uuid);
            if (mod == null)
                throw new ModRunEx("Module not found !");

            stopModule(mod, forceStop);
        } catch (ModUuidEx e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds and Return a ModularModule Object by UuID
     *
     * @param uuid - Give the needed Module-Uuid.
     * @return - Return the found ModularModule.
     * @throws ModUuidEx - Return a ModUuidEx if the Module-UuID is incorrect.
     * @since 1.0
     */

    public ModularModule findModuleByUuiD(String uuid) throws ModUuidEx {
        if (uuid.length() == 8) {
            if (modSource.getModuleMap().containsKey(uuid))
                return modSource.getModuleMap().get(uuid);
        } else
            throw new ModUuidEx("The uuid is incorrect !");
        return null;
    }

    /**
     * Set module dependencies
     *
     * @param modDeps - Give an array of ModularModule Objects.
     * @since 1.3
     */

    public void setDepends(ModularModule... modDeps) {
        for (ModularModule mod : modDeps)
            if (!modulesDependencies.contains(mod)) {
                modulesDependencies.add(mod);
            }
    }
}
