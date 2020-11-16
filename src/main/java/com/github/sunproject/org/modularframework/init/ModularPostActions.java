package com.github.sunproject.org.modularframework.init;

import com.github.sunproject.org.modularframework.console.ModularLog;
import com.github.sunproject.org.modularshell.ModularShell;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Modular port-actions tasks.
 */

public class ModularPostActions {
	public static void postInit() {
		ModularLog console = ModularInit.getConsole();
		console.setContext(ModularInit.getPrjName() + "_PostInit");
		console.log("Executing actions for post-init ...");

		try {
			ModularInit.getModuleManager().enableModule(ModularInit.getModuleManager().getModuleByName(ModularShell.moduleName));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
