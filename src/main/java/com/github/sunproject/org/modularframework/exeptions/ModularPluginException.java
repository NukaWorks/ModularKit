package com.github.sunproject.org.modularframework.exeptions;

public class ModularPluginException extends Exception {

	private static final long serialVersionUID = 1L;

	public ModularPluginException(String message) {
        super(message);
    }

    public ModularPluginException() {
        this(null);
    }
}
