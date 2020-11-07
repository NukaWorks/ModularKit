package com.github.sunproject.org.modularframework.providers.modulemanager;

import com.github.sunproject.org.modularframework.init.ModularInit;
import com.github.sunproject.org.modularframework.init.ModularModuleInit;
import com.github.sunproject.org.modularframework.utils.Pluralize;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;

public class ModularModuleManager {

	public ModularModuleManager() {
		System.out.println("Starting up Module Manager ...");
		ModularModuleInit.init();
		System.out.println("Detected " + "(" + ModularModule.getModuleNumber() + ") "
				+ Pluralize.pluralizeWord("Module", ModularModule.getModuleNumber()) + " !");
		System.out.println("done !");
	}

	public boolean reloadPluginList() {
		System.out.println("Reloading Module List ...");
		try {
			ModularInit.getModuleLoader().startIndexation();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void shutdown() {
		System.out.println("Stopping Module Manager ...");
		System.err.println("Stopping ALL Modules " + "(" + ModularModule.getModuleNumber() + ")" + "...");

		if (!ModularModule.getModulesList().isEmpty()) {
			Iterator<Map.Entry<String, ModularModule>> it = ModularModule.getModulesList().entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<String, ModularModule> entry = it.next();

				try {
					disableModule(getModuleByName(entry.getValue().getModuleName()));
					it.remove();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("done !");
	}

	public void enableModule(ModularModule module) throws NoSuchMethodException {
		if (!module.isEnabled()) {
			System.out.println("Enabling Module " + module.getModuleName() + "...");
			Thread modRunner = new Thread(() -> {
				module.onEnable();
			});

			modRunner.setName("Module_" + module.getModuleName());
			modRunner.start();
			module.setEnabled(true);
		}
	}

	public void disableModule(ModularModule module) throws NoSuchMethodException {
		if (module.isEnabled()) {
			System.out.println("Disabling Module " + module.getModuleName() + "...");
			Thread modRunner = new Thread(() -> {
				module.onDisable();
			});

			modRunner.setName("Module_" + module.getModuleName() + " Stopping ...");
			modRunner.start();
			module.setEnabled(false);
		}
	}

	public void deleteModule(ModularModule module) {
		System.out.println("Deleting Module" + module.getModuleName() + " ...");
		try {
			disableModule(module);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		try {
			module.removeModule();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ModularModule getModuleByName(String moduleName) {

		if (!ModularModule.getModulesList().isEmpty())
			return ModularModule.getModulesList().get(moduleName);

		return null;

	}

}
