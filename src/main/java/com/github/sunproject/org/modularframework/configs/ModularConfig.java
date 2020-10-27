package com.github.sunproject.org.modularframework.configs;

public abstract class ModularConfig {

	public String mineBootVersion, workSpaceDir;

	public boolean enableStopPluginIfManuallyDeleted, enableAutoReload;
	public long mineBootAPI_LEVEL;

	public String getMineBootVersion() {
		return mineBootVersion;
	}

	public void setMineBootVersion(String mineBootVersion) {
		this.mineBootVersion = mineBootVersion;
	}

	public long getMineBootAPI_LEVEL() {
		return mineBootAPI_LEVEL;
	}

	public void setMineBootAPI_LEVEL(int mineBootAPI_LEVEL) {
		this.mineBootAPI_LEVEL = mineBootAPI_LEVEL;
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
