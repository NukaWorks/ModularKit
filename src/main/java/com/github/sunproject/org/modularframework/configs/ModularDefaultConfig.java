package com.github.sunproject.org.modularframework.configs;


import com.github.sunproject.org.modularframework.init.ModularInit;

public class ModularDefaultConfig extends ModularConfig {

    public ModularDefaultConfig() {
            setMineBootAPI_LEVEL(1);
            setMineBootVersion(ModularInit.getBuildVersion());
            setWorkSpaceDir("mineboot");
            setEnableStopPluginIfManuallyDeleted(true);
            setEnableAutoReload(false);
        }
    }
