package com.corkili.pa.common.config.loader;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.XMLConfiguration;

import com.corkili.pa.common.dto.Result;
import com.corkili.pa.common.util.IUtils;

public final class XmlConfigLoader extends AbstractConfigLoader {

    private static XmlConfigLoader instance;

    static XmlConfigLoader getInstance() {
        if (instance == null) {
            synchronized (XmlConfigLoader.class) {
                if (instance == null) {
                    instance = new XmlConfigLoader();
                }
            }
        }
        return instance;
    }

    private XmlConfigLoader() {

    }

    @Override
    protected Result<Map<String, String>> load0(String filename) {
        try {
            XMLConfiguration configuration = new XMLConfiguration(filename);
            Map<String, String> config = new HashMap<>();
            configuration.getKeys().forEachRemaining(key -> config.put(key, configuration.getString(key)));
            return new Result<>(false, "success", config);
        } catch (Exception e) {
            return new Result<>(false, IUtils.stringifyError(e), null);
        }
    }
}
