package com.github.sunproject.org.modularframework.providers.modulemanager;

import com.github.sunproject.org.modularframework.init.ModularInit;
import com.github.sunproject.org.modularframework.console.ModularLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @since 1.1.7
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Class for load a moduleObject in a .modlr (jar) file.
 */

public class ModularModuleFileLoader {
	private static File modulesDir;
	private static ModularLog console = ModularInit.getConsole();

	public ModularModuleFileLoader() throws FileNotFoundException {
		modulesDir = ModularInit.getWorkSpaceBuilder().getWorkspaceDirs().get("MODULES");
	}

	public static File getModulesDir() {
		return modulesDir;
	}

	public void startIndexation() throws FileNotFoundException {
		console.log("Searching Modules ... ");
		if (!modulesDir.exists()) throw new FileNotFoundException("Sorry, this folder not exist ;(");

		URLClassLoader RSLoader = null;
		Class<?> RSClass = null;

		for(File modlr : modulesDir.listFiles()) {
			try {
				RSLoader = new URLClassLoader(new URL[] {new URL("file", null, modlr.getAbsolutePath())});
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			try {
				RSClass = Class.forName("R", false, RSLoader);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				if (RSClass != null) RSClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}