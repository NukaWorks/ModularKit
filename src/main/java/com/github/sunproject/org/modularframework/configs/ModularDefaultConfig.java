package com.github.sunproject.org.modularframework.configs;


import com.github.sunproject.org.modularframework.init.ModularInit;

public class ModularDefaultConfig {
    private static boolean isInstanced;
    private static ModularConfig config;


    public static void defaultConfig() {
        if (!isInstanced) {
            config = new ModularConfig();
            config.setMineBootAPI_LEVEL(1);
            config.setMineBootVersion(ModularInit.getBuildVersion());
            config.setWorkSpaceDir("mineboot");
            config.setEnableStopPluginIfManuallyDeleted(true);
            config.setEnableAutoReload(false);
        }
        isInstanced = true;
    }

    public static ModularConfig getModularConfig() {
        return config;
    }
}
