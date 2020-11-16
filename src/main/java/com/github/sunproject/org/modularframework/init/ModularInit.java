package com.github.sunproject.org.modularframework.init;

import java.io.File;
import java.io.IOException;

import com.github.sunproject.org.modularframework.configs.ModularDefaultConfig;
import com.github.sunproject.org.modularframework.console.ModularLog;
import com.github.sunproject.org.modularframework.providers.ModularFileWatcher;
import com.github.sunproject.org.modularframework.providers.ModularWorkSpaceBuilder;
import com.github.sunproject.org.modularframework.providers.modulemanager.ModularModuleFileLoader;
import com.github.sunproject.org.modularframework.providers.modulemanager.ModularModuleManager;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * ModularFramework Initialization class
 */

public class ModularInit {

	// *** --[ Project Configuration ] -- ***
	private static final String prjName = "ModularFramework";
	private static final String prjVBuildVersion = "2.0.0";
	// *** ------------------------------ ***

	private static ModularModuleFileLoader moduleFileLoader;
	private static ModularModuleManager moduleManager;
	private static ModularFileWatcher fileWatcher;
	private static ModularDefaultConfig defaultConfig;
	private static ModularWorkSpaceBuilder workSpaceBuilder;
	private static ModularLog console;


	public static void initModular() throws IOException {

		// Modular Logger init
		console = new ModularLog(prjName + "_Init");

		// MineBootAPI configuration init ...
		console.log("Initializing " + getPrjName() + " ...");
		defaultConfig = new ModularDefaultConfig();

		workSpaceBuilder = new ModularWorkSpaceBuilder(new File(defaultConfig.getWorkSpaceDir()));

		moduleFileLoader = new ModularModuleFileLoader();
		moduleFileLoader.startIndexation();
		moduleManager = new ModularModuleManager();

		if (getDefaultConfig().isEnableAutoReload()) {
			fileWatcher = new ModularFileWatcher(ModularModuleFileLoader.getModulesDir().toPath(),
					() -> console.log("fileWatcher", "Modules changes detected !"));
			fileWatcher.startFileWatcher();
		}

		ModularPostActions.postInit();
		console.setContext(prjName);
		console.log("init complete", "Done !");

		/////////////////////////////////////////////////////////
	}

	public static ModularModuleFileLoader getModuleLoader() {
		return moduleFileLoader;
	}

	public static ModularModuleManager getModuleManager() {
		return moduleManager;
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

	public static ModularWorkSpaceBuilder getWorkSpaceBuilder() {
		return workSpaceBuilder;
	}

	public static ModularLog getConsole() {
		return console;
	}

	public static void shutdown() {
		ModularShutdownActions.shutdownScript();
	}
}
