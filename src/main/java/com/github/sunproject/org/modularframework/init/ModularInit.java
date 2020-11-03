package com.github.sunproject.org.modularframework.init;

import java.io.IOException;

import com.github.sunproject.org.modularframework.configs.ModularDefaultConfig;
import com.github.sunproject.org.modularframework.providers.ModularFileWatcher;
import com.github.sunproject.org.modularframework.providers.ModularWorkSpaceBuilder;
import com.github.sunproject.org.modularframework.providers.modulemanager.ModularModuleFileLoader;
import com.github.sunproject.org.modularframework.providers.modulemanager.ModularModuleManager;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * MineBootFramework Initialization class
 */

public class ModularInit {
	// *** --[ Project Configuration ] -- ***
	private static final String prjName = "MineBootFramework";
	private static final String prjVBuildVersion = "1.1.5";


	private static ModularModuleFileLoader pluginLoader;
	private static ModularModuleManager pluginManager;
	private static ModularFileWatcher pluginsFileWatcher;
	private static ModularDefaultConfig defaultConfig;

	public static void initModular() throws IOException {
		System.out.println("Initializing " + getPrjName() + " ...");

		// MineBootAPI configuration init ...
		defaultConfig = new ModularDefaultConfig();
		ModularWorkSpaceBuilder.initDefaultWorkSpace();

		pluginManager = new ModularModuleManager();
//		pluginLoader = new MineBootModuleFileLoader();
//		pluginLoader.startIndexation();

		if (getDefaultConfig().isEnableAutoReload()) {
			pluginsFileWatcher = new ModularFileWatcher(ModularModuleFileLoader.getModulesDir().toPath(),
					() -> System.out.println("Plugins changed !"));
			pluginsFileWatcher.startFileWatcher();
		}

//        System.out.println("Launching " + MineBootDashBoard.class.getSimpleName() + " ...");
//        new MineBootDashBoard();

		System.out.println("Initialization done !");

		ModularPostActions.postInit();

		/////////////////////////////////////////////////////////

	}

	public static ModularModuleFileLoader getModuleLoader() {
		return pluginLoader;
	}

	public static ModularModuleManager getModuleManager() {
		return pluginManager;
	}

	public static void shutdown() {
		// Code for shutdown MineBoot-API properly !
		ModularShutdownActions.shutdownScript();
		System.exit(0);
	}

	public static String getBuildVersion() {
		return prjVBuildVersion;
	}

	public static String getPrjName() {
		return prjName;
	}

	public static ModularDefaultConfig getDefaultConfig() {
		return defaultConfig;
	}
}
