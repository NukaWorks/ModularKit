package com.github.sunproject.org.modularframework.configs;

import java.io.FileNotFoundException;

/**
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Default Configuration for ModularFramework.
 * @since 1.0
 */

public class ModularDefaultConfig extends ModularConfig {

    public ModularDefaultConfig() throws FileNotFoundException {
        setModularAPI_LEVEL(1);
        setWorkSpaceDir("ModularFramework");
        // *** [ BETA FEATURES ] *** //
        setEnableStopPluginIfManuallyDeleted(true);
        setEnableAutoReload(false);
        /////////////////////////////////////////////////
    }
}