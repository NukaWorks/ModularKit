package com.github.sunproject.org.modularframework.internal.init;

import com.github.sunproject.org.modularframework.internal.console.ModularCInputs;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Modular shutdown tasks.
 */

public class ModularShutdownActions {
	public static void shutdownScript() {
		ModularInit.getConsole().log("Shutting down " + ModularInit.getPrjName() + " ...");
		ModularInit.getModuleManager().shutdown();
		ModularCInputs.getConsoleInputs().destroy();
	}
}
