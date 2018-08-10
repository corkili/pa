package com.corkili.pa.common.config.loader;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.corkili.pa.common.dto.Result;
import com.corkili.pa.common.util.IUtils;

public final class PropertiesConfigLoader extends AbstractConfigLoader {

    private static PropertiesConfigLoader instance;

    static PropertiesConfigLoader getInstance() {
        if (instance == null) {
            synchronized (PropertiesConfigLoader.class) {
                if (instance == null) {
                    instance = new PropertiesConfigLoader();
                }
            }
        }
        return instance;
    }

    private PropertiesConfigLoader() {

    }

    @Override
    protected Result<Map<String, String>> load0(String filename) {
        try {
            PropertiesConfiguration configuration = new PropertiesConfiguration(filename);
            Map<String, String> config = new HashMap<>();
            configuration.getKeys().forEachRemaining(key -> config.put(key, configuration.getString(key)));
            return new Result<>(true, "success", config);
        } catch (Exception e) {
            return new Result<>(false, IUtils.stringifyError(e), null);
        }
    }
}
