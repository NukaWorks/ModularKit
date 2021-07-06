package org.nutdevs.modularkit.core;


import org.nutdevs.modularkit.core.events.ModuleStatus;

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
    private final ModuleManager moduleManager = new ModuleManager(this);


    public ModularSource(String _uuid) {
        if (_uuid == null) throw new NullPointerException("uuid cannot be null.");
        if (_uuid.length() != 8) try { throw new Exception("uuid is incorrect !"); }
        catch (Exception e) { e.printStackTrace(); }
        uuid = _uuid;
        registerSource();
    }


    public ModularSource(String _uuid, File path) {
        if (_uuid == null) throw new NullPointerException("uuid cannot be null.");
        if (_uuid.length() != 8) try { throw new Exception("uuid is incorrect !"); }
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
                                else throw new Exception("The module not extends ModularModule.");
                            } catch (Exception classNotFoundException) {
                                classNotFoundException.printStackTrace();
                            }
                        }
                    };
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
            moduleEntry.getValue()._stop();
            if (forceDestroy) {
                try { moduleEntry.getValue()._kill(); }
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

    public boolean registerModule(ModularModule module) throws Exception {
        if (!moduleMap.containsKey(module.getUuid())) {
            module.setModuleSource(this);
            moduleMap.put(module.getUuid(), module);
            return true;
        } else throw new Exception("Module already instantiated !");
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

    public static ModularSource findSourceByUuiD(String uuid) throws Exception {
        if (uuid.length() == 8) {
            if (sourceMap.containsKey(uuid)) return sourceMap.get(uuid);
        } else throw new Exception("uuid is incorrect");
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