package com.corkili.pa.common.config.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import com.corkili.pa.common.dto.Result;
import com.corkili.pa.common.util.IUtils;

public final class YamlConfigLoader extends AbstractConfigLoader {
    
    private static YamlConfigLoader instance;

    static YamlConfigLoader getInstance() {
        if (instance == null) {
            synchronized (YamlConfigLoader.class) {
                if (instance == null) {
                    instance = new YamlConfigLoader();
                }
            }
        }
        return instance;
    }

    private YamlConfigLoader() {

    }

    @Override
    protected Result<Map<String, String>> load0(String filename) {
        try {
            Yaml yaml = new Yaml(new SafeConstructor());
            Map<String, Object> yamlMap = yaml.load(new BufferedReader(new FileReader(filename)));
            Map<String, String> config = new HashMap<>();
            yamlMap.forEach((key, value) -> config.put(key, value.toString()));
            return new Result<>(false, "success", config);
        } catch (Exception e) {
            return new Result<>(false, IUtils.stringifyError(e), null);
        }
    }
}
