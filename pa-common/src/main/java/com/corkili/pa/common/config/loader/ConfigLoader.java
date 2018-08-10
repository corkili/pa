package com.corkili.pa.common.config.loader;

import java.util.Map;

import com.corkili.pa.common.annotation.Param;
import com.corkili.pa.common.annotation.Params;
import com.corkili.pa.common.dto.Query;
import com.corkili.pa.common.dto.Result;

public interface ConfigLoader {

    @Params(params = {
            @Param(name = "filename", type = String.class)})
    Result<Map<String, String>> load(Query query);

}
