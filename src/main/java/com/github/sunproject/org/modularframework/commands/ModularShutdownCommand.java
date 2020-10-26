package com.github.sunproject.org.modularframework.commands;

import com.github.sunproject.org.modularframework.init.ModularInit;
import com.github.sunproject.org.modularshell.ModularCommand;

public class ModularShutdownCommand extends ModularCommand {

	public ModularShutdownCommand() {
		super("shutdown");
		this.setCommandDetails("Shutdown " + ModularInit.getPrjName() + " properly.");
		this.setEventHandler(ModularInit::shutdown);
	}

}
