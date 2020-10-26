package com.github.sunproject.org.modularframework.init;

import com.github.sunproject.org.modularshell.ModularShell;

public class ModularPostActions {
	public static void postInit() {
		System.out.println("Executing actions for post-init ...");

		try {
			ModularInit.getModuleManager().enableModule(ModularInit.getModuleManager().getModuleByName(ModularShell.moduleName));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
