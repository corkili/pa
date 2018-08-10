package com.corkili.pa.common.config.loader;

import java.util.Map;

import com.corkili.pa.common.dto.Query;
import com.corkili.pa.common.dto.Result;
import com.corkili.pa.common.dto.util.Querys;

public abstract class AbstractConfigLoader implements ConfigLoader {

    @Override
    public Result<Map<String, String>> load(Query query) {
        if (!Querys.checkQuery(ConfigLoader.class, "load", query)) {
            return new Result<>(false, "invalid arguments", null);
        }
        return load0(query.getString("filename"));
    }

    protected abstract Result<Map<String, String>> load0(String filename);
}
