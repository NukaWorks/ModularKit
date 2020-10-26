package com.github.sunproject.org.modularframework.init;

public class ModularShutdownActions {
	synchronized public static void shutdownScript() {
		System.err.println("Shutting down " + ModularInit.getPrjName() + " ...");
		ModularInit.getModuleManager().shutdown();
	}
}
