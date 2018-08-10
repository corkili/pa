package com.corkili.pa.common.test.dto;

import static org.junit.Assert.*;

import org.junit.Test;

import com.corkili.pa.common.annotation.Param;
import com.corkili.pa.common.annotation.Params;
import com.corkili.pa.common.dto.Query;
import com.corkili.pa.common.dto.util.Querys;

public class TestQuery {

    @Params(params = {
            @Param(name = "str", type = String.class),
            @Param(name = "integer", type = Integer.class)})
    private boolean invoke(Query query) {
        return Querys.checkQuery(TestQuery.class, "invoke", query);
    }

    @Test
    public void testQuery() {
        Query query = new Query();
        query.add("str", "test-case");
        query.add("integer", 1);
        assertTrue(invoke(query));
        assertEquals("test-case", query.get("str", String.class));
        assertEquals(Integer.valueOf(1), query.get("integer", Integer.class));
    }

}
