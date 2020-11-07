package com.github.sunproject.org.modularframework.init;

import com.github.sunproject.org.modularshell.ModularShell;

// static import for import registerModule method
import static com.github.sunproject.org.modularframework.providers.modulemanager.ModularModule.registerModule;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Module Registraction Class.
 */

public class ModularModuleInit {
	public static void init() {
		registerModule(new ModularShell(ModularShRegisterCmd::registerCommands));
	}
}
