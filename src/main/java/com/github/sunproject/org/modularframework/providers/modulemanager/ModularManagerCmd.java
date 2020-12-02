package com.github.sunproject.org.modularframework.providers.modulemanager;


import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.github.sunproject.org.modularframework.init.ModularInit;
import com.github.sunproject.org.modularframework.console.ModularLog;
import com.github.sunproject.org.modularshell.ModularCmdArgs;
import com.github.sunproject.org.modularshell.ModularCommand;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Class for managing module in cmdLine
 * @since 1.0
 */

public class ModularManagerCmd extends ModularCommand {

    private final ModularLog console = ModularInit.getConsole();

    public ModularManagerCmd() {
        super("manager");
        setCommandDetails("Control The MineBootModule Manager.");
        setEventHandler(this::exec);
    }

    private void exec() {
        int arSize = ModularCommand.getCommandArgs().size();
        AtomicReference<String> moduleName = new AtomicReference<>("");
        ModularModuleManager mMan = ModularInit.getModuleManager();

        new ModularCmdArgs("ls", this::listModules);

        new ModularCmdArgs("enable", () -> {
            if (arSize != 2) help("SYNTAX");
            else {
                moduleName.set(ModularCommand.getCommandArgs().get(1));

                // Enabling the module ...
                try {
                    if (mMan.getModuleByName(moduleName.get()) != null)
                        mMan.enableModule(mMan.getModuleByName(moduleName.get()));
                    else console.log("Module " + moduleName.get() + " not found :/");
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });

        new ModularCmdArgs("disable", () -> {

            if (arSize != 2) help("SYNTAX");
            else {
                moduleName.set(ModularCommand.getCommandArgs().get(1));

                // Disabling the module ...
                try {
                    if (mMan.getModuleByName(moduleName.get()) != null)
                        mMan.disableModule(mMan.getModuleByName(moduleName.get()));
                    else console.log("Module " + moduleName.get() + " not found :/");
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });

        if (arSize == 0) help();
    }

    private void help() {
        System.out.println("ls - list Modules");
        System.out.println("enable - Enable Module");
        System.out.println("disable - Disable Module");
    }

    private void help(String err) {
        System.err.println("Error - " + (err.equals("SYNTAX") ? "syntax or argument error :/" : err + " not found :/") + "\n");
        help();
    }

    private void listModules() {
        for (Map.Entry<String, ModularModule> entryMod : ModularModule.getModulesList().entrySet()) {
            ModularModule module = entryMod.getValue();
            System.out.println(Ansi.colorize(module.getModuleName(), Attribute.TEXT_COLOR(252, 124, 5))
                    + "@" + Ansi.colorize(module.getModuleVersion(), Attribute.CYAN_TEXT())
                    + (module.isEnabled()
                    ? Ansi.colorize("\t\uD83D\uDFE2", Attribute.TEXT_COLOR(55, 255, 0))
                    : Ansi.colorize("\t\uD83D\uDFE2", Attribute.TEXT_COLOR(255, 46, 0))));
        }
    }
}