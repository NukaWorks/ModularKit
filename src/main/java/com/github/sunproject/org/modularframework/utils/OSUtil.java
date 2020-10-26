package com.github.sunproject.org.modularframework.utils;

import com.github.sunproject.org.modularframework.configs.ModularDefaultConfig;

import java.io.File;
import java.io.FileNotFoundException;

public class OSUtil {

	public enum OperatingSystem {
		windows, macos, linux, unknown
	}

	public static OperatingSystem getOS() {
		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.contains("win"))

			return OperatingSystem.windows;

		else if (osName.contains("mac"))

			return OperatingSystem.macos;

		else if (osName.contains("linux"))

			return OperatingSystem.linux;

		else
			return OperatingSystem.unknown;
	}

	public static File getLocalStorage(String dir) throws FileNotFoundException {
		if (dir == null)
			throw new FileNotFoundException("The value \"dir\" cannot be null.");
		String userHome = System.getProperty("user.home");

		if (getOS() == OperatingSystem.windows) {
			return new File(System.getenv("appdata"), "." + dir);
		} else if (getOS() == OperatingSystem.macos) {
			return new File(userHome, "Library/Application Support/" + dir);

		} else {
			return new File(userHome, dir);
		}
	}

	public static File getWorkSpacePath() throws FileNotFoundException {
			return getLocalStorage(ModularDefaultConfig.getModularConfig().getWorkSpaceDir());
	}

	public static File getWorkSpacePath(String child) throws FileNotFoundException {
			return getLocalStorage(ModularDefaultConfig.getModularConfig().getWorkSpaceDir() + "/" + child);
	}
}
