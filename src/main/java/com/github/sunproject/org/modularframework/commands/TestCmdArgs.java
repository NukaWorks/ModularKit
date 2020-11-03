package com.github.sunproject.org.modularframework.commands;

import com.github.sunproject.org.modularshell.ModularCmdArgs;
import com.github.sunproject.org.modularshell.ModularCommand;

public class TestCmdArgs extends ModularCommand {

	public TestCmdArgs() {
		super("test");
		this.setEventHandler(() -> {
			ModularCmdArgs args = new ModularCmdArgs("lol", () -> System.out.println("sfsdfsdf"));
		});
	}
}
