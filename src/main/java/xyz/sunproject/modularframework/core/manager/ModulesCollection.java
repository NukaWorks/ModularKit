package xyz.sunproject.modularframework.core.manager;


import xyz.sunproject.modularframework.core.ModularModule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModulesCollection {

    private final Map<String, ModularModule> moduleMap;

    protected ModulesCollection(HashMap<String, ModularModule> modMap) {
        moduleMap = Collections.unmodifiableMap(modMap);
    }

    public Map<String, ModularModule> getUnmodifiableModuleCollection() {
        return moduleMap;
    }
}
