package xyz.sunproject.modularframework.core;

import xyz.sunproject.modularframework.core.events.ModuleStatus;
import xyz.sunproject.modularframework.core.events.RunEvent;
import xyz.sunproject.modularframework.core.manager.ModuleManager;

public abstract class ModularModule implements RunEvent {

    private final String uuid, moduleName, author, version;
    private boolean isStopping = false;



    // Thread naming conventions : Mod_$name#$dynUuid_$uuid

    private String threadName = null;
    private Thread modThread = null;
    /**
     * TODO
     * @param _name
     * @param _uuid
     * @param author
     * @param version
     */

    public ModularModule(String _name, String _uuid, String author, String version) throws Exception {
        this.author = author;
        this.version = version;
        if (_uuid == null) throw new NullPointerException("uuid cannot be null.");
        else if (_uuid.length() != 8) throw new Exception("uuid is incorrect !");
        if (_name == null) throw new NullPointerException("name cannot be null.");
        if (_name.isEmpty()) moduleName = "I Have a no-name !";
        else moduleName = _name;
        uuid = _uuid;

        if (ModuleManager.getInstance().findModuleByUuiD(uuid) != null) throw new Exception("Module already instantiated !");


    }

    public void _exec() throws Exception {
        isStopping = false;
        threadName = Thread.currentThread().getName();
        modThread = Thread.currentThread();
        if (!threadName.equals("Mod_" + moduleName + "#" + ModuleManager.getInstance().getDynUuiD() + "_" + uuid)) throw new Exception("This module cannot be run outside a ModThread.");
        System.out.println(modThread.getState());

        runEvent();
        System.out.println("looool");
        isStopping = false;
    }

    /**
     * Stop the module
     */

    public void _stop() {
        isStopping = true;
    }

    public void _kill() throws Exception {
        if (!isStopping) throw new Exception("Please try with stop() before call _kill() !");
        modThread.stop();
        isStopping = false;
    }

    public String getUuid() {
        return uuid;
    }

    public String getModuleName() {
        return moduleName;
    }

    public ModuleStatus getModuleState() {
        System.out.println(isStopping);
        if (modThread != null) {
            if (isStopping) return ModuleStatus.STOPPING;
            if (modThread.getState() == Thread.State.RUNNABLE) return ModuleStatus.RUNNING;
        } else return ModuleStatus.STOPPED;
        return ModuleStatus.STOPPED;
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
}
