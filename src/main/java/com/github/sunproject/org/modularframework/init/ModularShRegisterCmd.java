package com.github.sunproject.org.modularframework.init;

import com.github.sunproject.org.modularframework.commands.ModularShutdownCommand;
import com.github.sunproject.org.modularframework.commands.TestCmdArgs;
import com.github.sunproject.org.modularframework.providers.modulemanager.ModularManagerCmd;

// static import for register a command easily.
import static com.github.sunproject.org.modularshell.ModularCommand.registerCommand;


public class ModularShRegisterCmd {

	public static void registerCommands() {
		registerCommand(new ModularShutdownCommand());
		registerCommand(new ModularManagerCmd());
		registerCommand(new TestCmdArgs());
	}
}