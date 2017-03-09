package com.oscar.dbtest.common.model;

import java.util.concurrent.ConcurrentHashMap;

public class ConfigRegister {
	private static Config defaultConfig;
	private static volatile ConfigRegister instance;
	private static ConcurrentHashMap<String, Config> configMap;

	private ConfigRegister() {
		loadDefaultConfig();
		loadConfigs();
	}

	private void loadDefaultConfig() {

	}

	private void loadConfigs() {

	}

	public static ConfigRegister getInstance() {
		if (instance == null) {
			synchronized (ConfigRegister.class) {
				if (instance == null) {
					instance = new ConfigRegister();
				}
			}
		}
		return instance;
	}

	public Config getDefault() {
		return defaultConfig;
	}
	
	/**
	 * ������Ŀ����
	 * 2017��3��9�� 
	 * @param projectId
	 * @return
	 */
	public Config getConfig(String projectId) {
		return configMap.get(projectId);
	}
	
	public void reStoreDefault(Config def) {
		
	}
	
	public void reSotreConfig(String projectID, Config config) {
		
	}
	
	static class ConfigBuilder {

	}
}
