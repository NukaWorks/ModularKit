package com.github.sunproject.org.modularframework.init;

import com.github.sunproject.org.modularshell.ModularShell;

// static import for import registerModule method
import static com.github.sunproject.org.modularframework.providers.modulemanager.ModularModule.registerModule;

public class ModularModuleInit {
	public static void init() {
		registerModule(new ModularShell(ModularShRegisterCmd::registerCommands));
	}
}
