package com.corkili.pa.common.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.corkili.pa.common.config.loader.ConfigLoaderFactory;
import com.corkili.pa.common.dto.Query;
import com.corkili.pa.common.dto.Result;
import com.corkili.pa.common.util.CheckUtils;

public final class ConfigManager {

    private static ConfigManager instance;

    private Config config;
    private Map<String, ConfigType> fileTypeMap;
    private Map<String, Long> fileLastModifiedTimeMap;
    private ScheduledExecutorService executor;
    private int updateConfigInterval;

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private ConfigManager() {
        initConfig();
        initTask();
    }

    public Config getConfig() {
        return this.config;
    }

    public void registerConfigFile(String filename, ConfigType configType) {
        if (CheckUtils.hasNull(filename, configType)) {
            return;
        }
        fileTypeMap.put(filename, configType);
        fileLastModifiedTimeMap.put(filename, Long.MIN_VALUE);
        updateConfig();
    }

    public void shutdown() {
        fileTypeMap.clear();
        fileLastModifiedTimeMap.clear();
        executor.shutdownNow();
    }

    private void initConfig() {
        config = new Config();
        fileTypeMap = new ConcurrentHashMap<>();
        fileLastModifiedTimeMap = new ConcurrentHashMap<>();
        fileTypeMap.put(ConfigConstant.CONFIG_FILE_DEFAULT_AND_REQUIRED_CONFIG_FILE, ConfigType.YAML_CONFIG);
        fileLastModifiedTimeMap.put(ConfigConstant.CONFIG_FILE_DEFAULT_AND_REQUIRED_CONFIG_FILE, Long.MIN_VALUE);
        updateConfigInterval = config.getInteger(ConfigConstant.CONFIG_NAME_UPDATE_CONFIG_INTERVAL_SEC,
                ConfigConstant.CONFIG_DEFAULT_UPDATE_CONFIG_INTERVAL_SEC);
    }

    private void initTask() {
        executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread();
            t.setName("scheduled-update-config-thread");
            t.setDaemon(true);
            return t;
        });
        executor.scheduleAtFixedRate(this::updateConfig, updateConfigInterval, updateConfigInterval, TimeUnit.SECONDS);
    }

    private void updateConfig() {
        Map<String, String> configMap = new HashMap<>();
        fileTypeMap.forEach((filename, type) -> {
            File file = new File(filename);
            if (file.exists() && file.lastModified() > fileLastModifiedTimeMap.get(filename)) {
                fileLastModifiedTimeMap.put(filename, file.lastModified());
                configMap.putAll(loadConfigFile(filename, type));
            }
        });
        config.setConfigMap(configMap);
    }

    private Map<String, String> loadConfigFile(String filename, ConfigType configType) {
        Query query = new Query();
        query.add("filename", filename);
        Result<Map<String, String>> result = ConfigLoaderFactory.getConfigLoader(configType).load(query);
        return result.isSuccess() ? result.getExtra() : new HashMap<>();
    }


}
