package com.github.sunproject.org.modularframework.init;

// static import for register a command easily.
import com.github.sunproject.org.modularframework.commands.ModularShutdownCommand;
import com.github.sunproject.org.modularframework.providers.modulemanager.ModularManagerCmd;

import static com.github.sunproject.org.modularshell.ModularCommand.registerCommand;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Class for registers ModularCommand based Object.
 */

public class ModularShRegisterCmd {

	public static void registerCommands() {
		registerCommand(new ModularShutdownCommand());
		registerCommand(new ModularManagerCmd());
	}
}