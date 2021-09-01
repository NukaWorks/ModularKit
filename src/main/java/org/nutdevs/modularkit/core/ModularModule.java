package org.nutdevs.modularkit.core;

import org.nutdevs.modularkit.core.events.ModuleStatus;
import org.nutdevs.modularkit.core.events.RunEvent;
import org.nutdevs.modularkit.core.ex.ModRunEx;
import org.nutdevs.modularkit.core.ex.ModSourceEx;
import org.nutdevs.modularkit.core.ex.ModUuidEx;

import java.util.ArrayList;

@SuppressWarnings("unused")

public abstract class ModularModule implements RunEvent {

    private final String uuid; // The Module UuID, needed for find a unique Module.
    private final String moduleName; // The Module names.
    private final String author; // The Module Author name.
    private final String version; // The Module Version Number.
    private final ArrayList<ModularModule> tmpModDepsList = new ArrayList<>(); // (BETA) Temp ModuleDeps ArrayList<ModularModule>.
    private ModuleStatus modStatus = ModuleStatus.STOPPED; // Default Module Execution Status.
    private ModularSource modSource;

    // Thread naming conventions : Mod_$name#$dynUuid_$uuid
    private String threadName;
    private Thread modThread;

    /**
     * The ModularModule Module Object, the fabulous "ModularModule" !
     *
     * @param _name   - Module Name.
     * @param _uuid   - Module UuID.
     * @param author  - Author of the Module.
     * @param version - Module Version Number.
     * @param modDeps - (Optional) Add Module Dependencies.
     * @throws ModUuidEx - Can return a ModUuidEx if the uuid is incorrect or null.
     * @since 1.0
     */

    public ModularModule(String _name, String _uuid, String author, String version, ModularModule... modDeps) throws ModUuidEx {
        this.author = author;
        this.version = version;

        if (_uuid == null) throw new ModUuidEx("uuid cannot be null.");
        else if (_uuid.length() != 8)
            throw new ModUuidEx("uuid is incorrect !");
        uuid = _uuid;
        if (_name.isEmpty()) moduleName = "I Have a no-name !";
        else moduleName = _name;
        if (modDeps != null) {
            for (ModularModule mod : modDeps)
                if (!tmpModDepsList.contains(mod)) tmpModDepsList.add(mod);
        }
    }

    protected void setModuleSource(ModularSource source) throws ModSourceEx, ModUuidEx {
        if (source != null) modSource = source;
        else throw new ModSourceEx("ModSource cannot be null !");
        if (modSource.getModuleManager().findModuleByUuiD(uuid) != null)
            throw new ModUuidEx("Module already instantiated !");
        if (!tmpModDepsList.isEmpty()) for (ModularModule mod : tmpModDepsList)
            getModSource().getModuleManager().setDepends(mod);
    }


    protected void exec() {
        modStatus = ModuleStatus.RUNNING;
        modThread = Thread.currentThread();
        threadName = modThread.getName();

        runEvent();
        modStatus = ModuleStatus.STOPPED;
    }

    /**
     * Stop the module
     */

    protected void stop() {
        modStatus = ModuleStatus.STOPPING;
    }

    @SuppressWarnings("deprecation") // Because modThread.stop() is deprecated.
    protected void kill() throws ModRunEx {
        if (modStatus != ModuleStatus.STOPPING)
            throw new ModRunEx("Please try with stop() before call kill() !");
        modThread.stop();
        modStatus = ModuleStatus.STOPPED;
    }

    public String getUuid() {
        return uuid;
    }

    public String getModuleName() {
        return moduleName;
    }

    public ModuleStatus getModuleState() {
        return modStatus;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String getThreadName() {
        return threadName;
    }

    private ModularSource getModSource() {
        return modSource;
    }
}