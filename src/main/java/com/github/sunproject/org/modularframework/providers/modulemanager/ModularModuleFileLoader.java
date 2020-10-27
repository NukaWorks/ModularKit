package com.github.sunproject.org.modularframework.providers.modulemanager;

import com.github.sunproject.org.modularframework.configs.ModularDefaultConfig;
import com.github.sunproject.org.modularframework.init.ModularInit;
import com.github.sunproject.org.modularframework.utils.OSUtil;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class ModularModuleFileLoader {
	private static File modulesDir;

	private final ArrayList<String> modulesNameList = new ArrayList<>();

	public ModularModuleFileLoader() throws FileNotFoundException {
		modulesDir = OSUtil.getWorkSpacePath("modules");
	}

	public static File getModulesDir() {
		return modulesDir;
	}

	public ArrayList<String> getModulesNameList() {
		return modulesNameList;
	}

	public void startIndexation() throws FileNotFoundException {
		System.out.println("Searching Modules ... ");
		if (!modulesDir.exists())
			throw new FileNotFoundException("Sorry, this folder not exist ;(");
		if (!getModulesNameList().isEmpty())
			getModulesNameList().clear();

		for (File file : modulesDir.listFiles()) {
			if (file.getName().endsWith(".jar")) {

				URLClassLoader pluginJarClassLoader = null;
				try {
					pluginJarClassLoader = new URLClassLoader(
							new URL[] { new URL("file", null, file.getAbsolutePath()) });
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				Class<?> pluginMainClass = null;
				JSONObject pluginMetaJsonFile = null;

				try {
					pluginMainClass = Class.forName("R", true, pluginJarClassLoader);
					pluginMetaJsonFile = (JSONObject) JSONValue.parse(new InputStreamReader(pluginMainClass.getResourceAsStream("/ModuleMeta.json")));

					String moduleName = (String) pluginMetaJsonFile.get("moduleName");

				} catch (ClassNotFoundException e) {
					System.err.println("failed !");
					System.err.println("The class \"PluginClassMain\" was not found ! file: " + file.getAbsolutePath());
					e.printStackTrace();
				} catch (NullPointerException e) {
					System.err.println("failed !");
					System.err.println("File \"ModuleMeta.json\" was not found ! file: " + file.getAbsolutePath());
					e.printStackTrace();
				}
				System.out.println(pluginMetaJsonFile.get("pluginName"));
				getModulesNameList().add((String) pluginMetaJsonFile.get("pluginName"));
			}
		}
		
		for (String moduleName : ModularModule.getModulesList().keySet()) {
			if (!getModulesNameList().contains(moduleName)) {
				System.out.println("Removing " + moduleName + " ...");
				if (ModularInit.getDefaultConfig().isEnableStopPluginIfManuallyDeleted()) {
					try {
						ModularInit.getModuleManager()
								.disableModule(ModularInit.getModuleManager().getModuleByName(moduleName));
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				}
				ModularModule.getModulesList().remove(moduleName);
			}
		}

		System.out.println("...done !");
	}
}
