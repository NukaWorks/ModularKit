package com.github.sunproject.org.modularframework.providers.modulemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.github.sunproject.org.modularframework.init.ModularInit;
import com.github.sunproject.org.modularframework.utils.OSUtil;

/**
 * @since 1.1.7
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Class for load a moduleObject in a .modlr (jar) file.
 */

public class ModularModuleFileLoader {
	private static File modulesDir;

	public ModularModuleFileLoader() throws FileNotFoundException {
		modulesDir = OSUtil.getWorkSpacePath("MODULEs");
	}

	public static File getModulesDir() {
		return modulesDir;
	}


	public void startIndexation() throws FileNotFoundException {
		System.out.println("Searching Modules ... ");
		if (!modulesDir.exists()) throw new FileNotFoundException("Sorry, this folder not exist ;(");
		
		for(File modlr : modulesDir.listFiles()) {
			URLClassLoader RSLoader = null;
			Class<?> RSClass = null;

			try {
				RSLoader = new URLClassLoader(new URL[] {
						new URL("file", null, modlr.getAbsolutePath())
				});
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			try {
				RSClass = Class.forName("R", false, RSLoader);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				RSClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		
	}
}
		
