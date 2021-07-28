package org.nutdevs.modularkit.core;


import org.nutdevs.modularkit.core.events.ModuleStatus;
import org.nutdevs.modularkit.core.ex.ModRegisterEx;
import org.nutdevs.modularkit.core.ex.ModSourceEx;
import org.nutdevs.modularkit.core.ex.ModUuidEx;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ModularSource {

    private final Map<String, ModularModule> moduleMap = new HashMap<>();
    private static HashMap<String, ModularSource> sourceMap = new HashMap<>();
    private final String uuid;
    private ModuleManager moduleManager;


    public ModularSource(String _uuid) throws ModUuidEx {
        try {
            this.moduleManager = new ModuleManager(this);
        } catch (ModSourceEx modSourceEx) {
            modSourceEx.printStackTrace();
        }

        if (_uuid == null) throw new ModUuidEx("uuid cannot be null.");
        if (_uuid.length() != 8) try { throw new ModUuidEx("uuid is incorrect !"); }
        catch (Exception e) { e.printStackTrace(); }
        uuid = _uuid;
        registerSource();
    }

    public ModularSource(String _uuid, File path) throws ModUuidEx {
        if (_uuid == null) throw new ModUuidEx("uuid cannot be null.");
        if (_uuid.length() != 8) try { throw new ModUuidEx("uuid is incorrect !"); }
        catch (Exception e) { e.printStackTrace(); }
        uuid = _uuid;

        if (path.exists() && path.canRead()) {
            try {
                Files.walk(path.toPath()).forEach(e -> {
                    if (e.toFile().isFile() && e.toFile().getName().endsWith(".jar")) {
                        URLClassLoader classLoader = null;
                        try {
                            classLoader = new URLClassLoader(new URL[] {new URL("file", null, e.toFile().getAbsolutePath())});
                        } catch (MalformedURLException malformedURLException) { malformedURLException.printStackTrace(); }

                        Properties modlrFile = new Properties();
                        try {
                            InputStream inModlr = classLoader.getResourceAsStream(".modlr");
                            if (inModlr != null) {
                                modlrFile.load(inModlr);
                            } else throw new IOException("File .modlr not found !");
                        } catch (IOException ioException) { ioException.printStackTrace(); }

                        for (Map.Entry<Object, Object> entry : modlrFile.entrySet()) {
                            try {
                                String[] className = entry.getValue().toString().split("\\.");
                                Class<?> modClass = Class.forName(entry.getValue().toString(), false, classLoader);
                                if (!modClass.getSuperclass().getName().equals(ModularModule.class.getSimpleName())) {
                                    ModularModule newModule = (ModularModule) modClass.newInstance();
                                    registerModule(newModule);
                                }
                                else throw new ModSourceEx("The module not extends ModularModule.");
                            } catch (Exception classNotFoundException) {
                                classNotFoundException.printStackTrace();
                            }
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        registerSource();
    }

    private synchronized boolean registerSource() {
        if (!sourceMap.containsKey(uuid)) {
            sourceMap.put(uuid, this);
            return true;
        }
        return false;
    }

    private boolean unregisterSource() {
        if (sourceMap.containsKey(uuid)) {
            sourceMap.remove(uuid, this);
            return true;
        }
        return false;
    }

    public boolean destroy(@Deprecated boolean forceDestroy) {
        for (Map.Entry<String, ModularModule> moduleEntry : moduleMap.entrySet()) {
            moduleEntry.getValue().stop();
            if (forceDestroy) {
                try { moduleEntry.getValue().kill(); }
                catch (Exception e) {
                    System.err.println("Thread Killed !");
                    e.printStackTrace();
                }
            }
        }
        return unregisterSource();
    }

    /**
     * Registering a module with the ModularModule Object.
     * @param module Module Object
     * @throws Exception
     */

    public boolean registerModule(ModularModule module) throws ModRegisterEx, ModUuidEx, ModSourceEx {
        if (!moduleMap.containsKey(module.getUuid())) {
            module.setModuleSource(this);
            moduleMap.put(module.getUuid(), module);
            return true;
        } else throw new ModRegisterEx("Module already instantiated !");
    }

    /**
     * Registering a module with the ModuleClass.
     * @param module Class Object contains a ModularModule Object.
     * @throws Exception
     */

    public boolean registerModule(Class<?> module) throws ModUuidEx, ModRegisterEx, ModSourceEx, InstantiationException, IllegalAccessException {
        ModularModule mod = (ModularModule) module.newInstance();
        return registerModule(mod);
    }

    public boolean unregisterModule(ModularModule module) throws ModRegisterEx {
        System.out.println(module.getModuleState());
        if (moduleMap.containsKey(module.getUuid())) {
            if (module.getModuleState() == ModuleStatus.RUNNING) throw new ModRegisterEx("Failed to unregister the module : the module is running.");
            else {
                moduleMap.remove(module.getUuid(), module);
                return true;
            }
        }
        return false;
    }

    public static ModularSource findSourceByUuiD(String uuid) throws ModUuidEx {
        if (uuid.length() == 8) {
            if (sourceMap.containsKey(uuid)) return sourceMap.get(uuid);
        } else throw new ModUuidEx("uuid is incorrect");
        return null;
    }

    public Map<String, ModularModule> getUnmodifiableModuleMap() {
        return Collections.unmodifiableMap(moduleMap);
    }

    protected Map<String, ModularModule> getModuleMap() {
        return moduleMap;
    }

    protected static HashMap<String, ModularSource> getSourceMap() {
        return sourceMap;
    }

    public static HashMap<String, ModularSource> getUnmodifiableSourceMap() {
        return (HashMap<String, ModularSource>) Collections.unmodifiableMap(sourceMap);
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public String getUuid() {
        return uuid;
    }
}