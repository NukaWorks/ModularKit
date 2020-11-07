package com.github.sunproject.org.modularframework.commands;

import com.github.sunproject.org.modularframework.init.ModularInit;
import com.github.sunproject.org.modularshell.ModularCommand;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Shutdown command for "shutdown" properly the application.
 */

public class ModularShutdownCommand extends ModularCommand {

	public ModularShutdownCommand() {
		super("shutdown");
		this.setCommandDetails("Shutdown " + ModularInit.getPrjName() + " properly.");
		this.setEventHandler(ModularInit::shutdown);
	}

}
