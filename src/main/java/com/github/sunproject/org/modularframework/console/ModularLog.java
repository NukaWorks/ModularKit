package com.github.sunproject.org.modularframework.console;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

/**
 * @since 1.1.7
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * The Logger.
 */

public class ModularLog {

    private String context;

    public ModularLog(String context) {
        this.context = context;
    }

    public void log(String str) {
        System.out.println(Ansi.colorize("[" + context + "]\t", Attribute.CYAN_TEXT()) + str);
    }

    public void log(String internalContext, String str) {
        log(Ansi.colorize("(" + internalContext + ")\t", Attribute.GREEN_TEXT()) + str);
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
