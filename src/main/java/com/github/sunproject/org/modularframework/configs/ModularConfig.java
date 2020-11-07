package com.github.sunproject.org.modularframework.configs;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Configuration object definition class.
 */

public abstract class ModularConfig {

	public String workSpaceDir;
	public boolean enableStopPluginIfManuallyDeleted, enableAutoReload;
	public long modularAPI_LEVEL;

	public long getModularAPI_LEVEL() {
		return modularAPI_LEVEL;
	}

	public void setModularAPI_LEVEL(int modularAPI_LEVEL) {
		this.modularAPI_LEVEL = modularAPI_LEVEL;
	}

	public String getWorkSpaceDir() {
		return workSpaceDir;
	}

	public void setWorkSpaceDir(String workSpaceDir) {
		this.workSpaceDir = workSpaceDir;
	}

	public boolean isEnableStopPluginIfManuallyDeleted() {
		return enableStopPluginIfManuallyDeleted;
	}

	public void setEnableStopPluginIfManuallyDeleted(boolean enableStopPluginIfManuallyDeleted) {
		this.enableStopPluginIfManuallyDeleted = enableStopPluginIfManuallyDeleted;
	}

	public boolean isEnableAutoReload() {
		return enableAutoReload;
	}

	public void setEnableAutoReload(boolean enableAutoReload) {
		this.enableAutoReload = enableAutoReload;
	}
}
