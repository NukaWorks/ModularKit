package com.github.sunproject.org.modularframework.init;

import java.io.IOException;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Modular shutdown tasks.
 */

public class ModularShutdownActions {
	public static void shutdownScript() {
		ModularInit.getConsole().log("Shutting down " + ModularInit.getPrjName() + " ...");
		ModularInit.getModuleManager().shutdown();
		try {
			System.in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
