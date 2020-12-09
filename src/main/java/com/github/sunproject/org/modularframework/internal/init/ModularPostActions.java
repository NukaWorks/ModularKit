package com.github.sunproject.org.modularframework.internal.init;

import com.github.sunproject.org.modularframework.internal.console.ModularLog;

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
	}
}
