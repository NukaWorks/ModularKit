package xyz.sunproject.modularframework.core;

import xyz.sunproject.modularframework.core.events.ModuleStatus;
import xyz.sunproject.modularframework.core.events.RunEvent;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ModularModule implements RunEvent {

    private final String uuid, moduleName, author, version;
    private ModuleStatus modStatus = ModuleStatus.STOPPED;
    private ModularSource modSource;
    public final ArrayList<ModularModule> modDependencies;

    // Thread naming conventions : Mod_$name#$dynUuid_$uuid

    private String threadName = null;
    private Thread modThread = null;

    /**
     * TODO
     * @param _name
     * @param _uuid
     * @param author
     * @param version
     * @param modDeps
     */

    public ModularModule(String _name, String _uuid, String author, String version, ModularModule... modDeps) throws Exception {
        this.author = author;
        this.version = version;
        modDependencies = (ArrayList<ModularModule>) Arrays.asList(modDeps);
        if (_uuid == null) throw new NullPointerException("uuid cannot be null.");
        else if (_uuid.length() != 8) throw new Exception("uuid is incorrect !");
        if (_name == null) throw new NullPointerException("name cannot be null.");
        if (_name.isEmpty()) moduleName = "I Have a no-name !";
        else moduleName = _name;
        uuid = _uuid;
    }


    protected void setModuleSource(ModularSource source) throws Exception {
        if (source != null) modSource = source;
        else throw new NullPointerException();
        if (modSource.getModuleManager().findModuleByUuiD(uuid) != null) throw new Exception("Module already instantiated !");
    }


    protected void _exec() throws Exception {
        modStatus = ModuleStatus.RUNNING;
        threadName = Thread.currentThread().getName();
        modThread = Thread.currentThread();
        if (!threadName.equals("Mod_" + moduleName + "#" + modSource.getModuleManager().getDynUuiD() + "_" + uuid)) throw new Exception("This module cannot be run outside a ModThread.");
        if (!modDependencies.isEmpty()) for (ModularModule mod : modDependencies) {
            if (mod.getModuleState() == ModuleStatus.STOPPED) mod.getModSource().getModuleManager().runModule(mod);
        }
        runEvent();
        modStatus = ModuleStatus.STOPPED;
    }

    /**
     * Stop the module
     */

    protected void _stop() {
        depsCleaner();
        modStatus = ModuleStatus.STOPPING;
    }

    protected void _kill() throws Exception {
        if (modStatus != ModuleStatus.STOPPING) throw new Exception("Please try with stop() before call _kill() !");
        depsCleaner();
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

    private void depsCleaner() {
        for (ModularModule modDeps : modDependencies) {
            if (modDeps.modDependencies.size() >= 1 && modDeps.modDependencies.contains(modDeps)) {
                try {
                    modDeps.getModSource().getModuleManager().stopModule(modDeps, false);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }
}
