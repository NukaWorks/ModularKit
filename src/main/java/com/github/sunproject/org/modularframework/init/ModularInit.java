package com.github.sunproject.org.modularframework.init;

import com.github.sunproject.org.modularframework.configs.ModularDefaultConfig;
import com.github.sunproject.org.modularframework.providers.ModularFileWatcher;
import com.github.sunproject.org.modularframework.providers.ModularWorkSpaceBuilder;
import com.github.sunproject.org.modularframework.providers.modulemanager.ModularModuleFileLoader;
import com.github.sunproject.org.modularframework.providers.modulemanager.ModularModuleManager;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * MineBootFramework Initialization class
 */

public class ModularInit {

	private static ModularModuleFileLoader pluginLoader;

	private static ModularModuleManager pluginManager;

	private static ModularFileWatcher pluginsFileWatcher;

	private static final String prjName = "MineBootFramework";

	private static String prjVBuildVersion = "1.0.2";

	public static void initModular() throws IOException {
		System.out.println("Initializing " + getPrjName() + " ...");

		// MineBootAPI configuration init ...
		ModularDefaultConfig.defaultConfig();

		try {
			ModularWorkSpaceBuilder.initDefaultWorkSpace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		pluginManager = new ModularModuleManager();
//		pluginLoader = new MineBootModuleFileLoader();
//		pluginLoader.startIndexation();

		if (ModularDefaultConfig.getModularConfig().isEnableAutoReload()) {
			pluginsFileWatcher = new ModularFileWatcher(getModuleLoader().getModulesDir().toPath(),
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
}
