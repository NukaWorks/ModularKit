package org.nutdevs.modularkit.core;

import org.nutdevs.modularkit.core.events.ModuleStatus;
import org.nutdevs.modularkit.core.events.RunEvent;
import org.nutdevs.modularkit.core.ex.ModEx;
import org.nutdevs.modularkit.core.ex.ModRunEx;
import org.nutdevs.modularkit.core.ex.ModSourceEx;
import org.nutdevs.modularkit.core.ex.ModUuidEx;

import java.util.ArrayList;

public abstract class ModularModule implements RunEvent {

    private final String uuid; // The Module UuID, needed for find a unique Module.
    private final String moduleName; // The Module Name.
    private final String author; // The Module Author Name.
    private final String version; // The The Module Version Number.

    private ModuleStatus modStatus = ModuleStatus.STOPPED; // Default Module Execution Status.
    private ModularSource modSource;
    private ArrayList<ModularModule> tmpModDepsList = new ArrayList<>(); // (BETA) Temp ModuleDeps ArrayList<ModularModule>.

    // Thread naming conventions : Mod_$name#$dynUuid_$uuid

    private String threadName;
    private Thread modThread;

    /**
     * @param _name - Module Name.
     * @param _uuid - Module UuID.
     * @param author - Author of the Module.
     * @param version - Module Version Number.
     * @param modDeps - (Optional) Add Module Dependencies.
     */

   public ModularModule(String _name, String _uuid, String author, String version, ModularModule... modDeps) throws Exception {
        this.author = author;
        this.version = version;
        if (modDeps != null) {
            for (ModularModule mod : modDeps) if (!tmpModDepsList.contains(mod)) tmpModDepsList.add(mod);
        }

        if (_uuid == null) throw new ModUuidEx("uuid cannot be null.");
        else if (_uuid.length() != 8) throw new ModUuidEx("uuid is incorrect !");
        if (_name == null) throw new ModUuidEx("name cannot be null.");
        if (_name.isEmpty()) moduleName = "I Have a no-name !";
        else moduleName = _name;
        uuid = _uuid;
    }

    protected void setModuleSource(ModularSource source) throws ModSourceEx, ModUuidEx {
        if (source != null) modSource = source;
        else throw new ModSourceEx("ModSource cannot be null !");
        if (modSource.getModuleManager().findModuleByUuiD(uuid) != null)
            throw new ModUuidEx("Module already instantiated !");
        if (!tmpModDepsList.isEmpty()) for (ModularModule mod : tmpModDepsList) getModSource().getModuleManager().setDepends(mod);
    }


    protected void exec() throws Exception {
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

    protected void kill() throws ModRunEx {
        if (modStatus != ModuleStatus.STOPPING) throw new ModRunEx("Please try with stop() before call kill() !");
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
