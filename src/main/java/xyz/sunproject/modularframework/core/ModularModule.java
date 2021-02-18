package xyz.sunproject.modularframework.core;

import xyz.sunproject.modularframework.core.events.RunEvent;

public abstract class ModularModule implements RunEvent {

    private final String uuid;
    private final String moduleName;

    // Thread naming conventions : Mod_$name#$dynUuid_$uuid

    private String threadName = null;

    /**
     * TODO
     * @param _name
     * @param _uuid
     */

    public ModularModule(String _name, String _uuid) throws Exception {
        if (_uuid == null) throw new NullPointerException("uuid cannot be null.");
        else if (_uuid.length() != 8) throw new Exception("uuid is incorrect !");
        if (_name == null) throw new NullPointerException("name cannot be null.");
        if (_name.isEmpty()) moduleName = "I Have a no-name !";
        else moduleName = _name;
        uuid = _uuid;

        if (ModuleManager.getInstance().findModuleByUuiD(uuid) != null) throw new Exception("Module already instantiated !");
    }

    public void exec() throws Exception {
        threadName = Thread.currentThread().getName();
        if (!threadName.equals("Mod_" + moduleName + "#" + ModuleManager.getInstance().getDynUuiD() + "_" + uuid)) throw new Exception("This module cannot be run outside a ModThread.");
        runEvent();
    }

    public String getUuid() {
        return uuid;
    }

    public String getModuleName() {
        return moduleName;
    }
}
