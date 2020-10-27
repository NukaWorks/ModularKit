package com.github.sunproject.org.modularframework.providers.modulemanager;


import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;
import com.github.sunproject.org.modularframework.init.ModularInit;
import com.github.sunproject.org.modularshell.ModularCommand;

import java.util.Map;

public class ModularManagerCmd extends ModularCommand {

	public ModularManagerCmd() {
		super("manager");
		setCommandDetails("Control The MineBootModule Manager.");
		setEventHandler(this::exec);

	}

	private void exec() {
		if (getCommandArgs() != null) {
			if (getCommandArgs().contains("ls")) {
				listModules();
			} else if (getCommandArgs().contains("infos")) {
				moduleProperties(ModularInit.getModuleManager().getModuleByName(getCommandArgs().get(getCommandArgs().size() - 1)));
			} else if (getCommandArgs().contains("enable")) {
				try {
					enableModule(ModularInit.getModuleManager().getModuleByName(getCommandArgs().get(getCommandArgs().size() - 1)));
				} catch (Exception e) {
					System.err.println("Error while trying to enable your module.");
					e.printStackTrace();
				}
			} else if (getCommandArgs().contains("disable")) {
				try {
					disableModule(ModularInit.getModuleManager().getModuleByName(getCommandArgs().get(getCommandArgs().size() - 1)));
				} catch (Exception e) {
					System.err.println("Error while trying to disable your module.");
					e.printStackTrace();
				}
			}
			else help();
		}
	}

	private void help() {
		System.out.println("ls - list Modules");
		System.out.println("infos - get properties of module");
		System.out.println("enable - Enable Module");
		System.out.println("disable - Disable Module");
		// System.out.println("reloadExternalModuleManager - reload external module support.");
	}

	private void moduleProperties(ModularModule module) {
		if (module != null) System.out.println(Ansi.colorize("Module author : ", new AnsiFormat(Attribute.TEXT_COLOR(69, 71, 74))) + module.getAuthor());
		else System.err.println("Sorry, please give a valid module name for find that.");
	}

	private void listModules() {
		for (Map.Entry<String, ModularModule> entryMod : ModularModule.getModulesList().entrySet()) {
			ModularModule module = entryMod.getValue();
			System.out.println(Ansi.colorize(module.getModuleName(), Attribute.TEXT_COLOR(252, 124, 5))
					+ "@" + Ansi.colorize(module.getModuleVersion(), Attribute.CYAN_TEXT()));
		}
	}

	private void enableModule(ModularModule module) throws Exception {
		ModularInit.getModuleManager().enableModule(module);
	}
	
	private void disableModule(ModularModule module) throws Exception {
		ModularInit.getModuleManager().disableModule(module);
	}
}
