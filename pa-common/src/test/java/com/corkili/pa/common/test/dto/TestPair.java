package com.corkili.pa.common.test.dto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.corkili.pa.common.dto.Pair;

public class TestPair {

    private Pair<String, Integer> pair1;
    private Pair<String, Integer> pair2;

    @Before
    public void setUp() {
        pair1 = new Pair<>("test-case-1", 1);
        pair2 = new Pair<>("test-case-2", 2);
    }
    
    @After
    public void tearDown() {
        pair1 = null;
        pair2 = null;
    }
    
    @Test
    public void testGetter() {
        assertEquals("key should be \"test-case-1\"", "test-case-1", pair1.getKey());
        assertEquals("value should be \"1\"", Integer.valueOf(1), pair1.getValue());
    }
    
    @Test
    public void testSetter() {
        assertEquals("key should be \"test-case-1\"", "test-case-1", pair1.getKey());
        assertEquals("value should be \"1\"", Integer.valueOf(1), pair1.getValue());
        pair1.setKey("change-key");
        pair1.setValue(0);
        assertEquals("key should be \"change-key\"", "change-key", pair1.getKey());
        assertEquals("value should be \"0\"", Integer.valueOf(0), pair1.getValue());

        assertEquals("key should be \"test-case-2\"", "test-case-2", pair2.getKey());
        assertEquals("value should be \"1\"", Integer.valueOf(2), pair2.getValue());
        pair2.set("change-key", 0);
        assertEquals("key should be \"change-key\"", "change-key", pair2.getKey());
        assertEquals("value should be \"0\"", Integer.valueOf(0), pair2.getValue());
    }
    
}
