package com.corkili.pa.common.test.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.corkili.pa.common.util.CheckUtils;

public class TestCheckUtils {

    private Object nullObj1;
    private Object nullObj2;
    private Object notNullObj1;
    private Object notNullObj2;
    private Object[] nullArr;
    private Object[] emptyArr;
    private Object[] notEmptyArr;

    @Before
    public void setUp() {
        nullObj1 = null;
        nullObj2 = null;
        notNullObj1 = new Object();
        notNullObj2 = new Object();
        nullArr = null;
        emptyArr = new Object[0];
        notEmptyArr = new Object[3];
    }

    @After
    public void tearDown() {
        nullObj1 = null;
        nullObj2 = null;
        notNullObj1 = null;
        notNullObj2 = null;
    }

    @Test
    public void testIsNull() {
        assertTrue("object is null, result should be true", CheckUtils.isNull(nullObj1));
        assertFalse("object is not null, result should be false", CheckUtils.isNull(notNullObj1));
    }

    @Test
    public void testIsNotNull() {
        assertTrue("object is not null, result should be true", CheckUtils.isNotNull(notNullObj1));
        assertFalse("object is null, result should be false", CheckUtils.isNotNull(nullObj1));
    }

    @Test
    public void testAllIsNull() {
        assertTrue("all object is null, result should be true", CheckUtils.allIsNull(nullObj1, nullObj2));
        assertFalse("some object is not null, result should be false", CheckUtils.allIsNull(nullObj1, notNullObj1));
        assertFalse("all object is not null, result should be false", CheckUtils.allIsNull(notNullObj1, notNullObj2));
    }

    @Test
    public void testAllIsNotNull() {
        assertTrue("all object is not null, result should be true", CheckUtils.allIsNotNull(notNullObj1, notNullObj2));
        assertFalse("some object is null, result should be false", CheckUtils.allIsNotNull(notNullObj1, nullObj1));
        assertFalse("all object is null, result should be false", CheckUtils.allIsNotNull(nullObj1, nullObj2));
    }

    @Test
    public void testHasNull() {
        assertFalse("all object is not null, result should be false", CheckUtils.hasNull(notNullObj1, notNullObj2));
        assertTrue("some object is null, result should be true", CheckUtils.hasNull(notNullObj1, nullObj1));
        assertTrue("all object is null, result should be true", CheckUtils.hasNull(nullObj1, nullObj2));
    }

    @Test
    public void testHasNotNull() {
        assertFalse("all object is null, result should be false", CheckUtils.hasNotNull(nullObj1, nullObj2));
        assertTrue("some object is not null, result should be true", CheckUtils.hasNotNull(notNullObj1, nullObj1));
        assertTrue("all object is not null, result should be true", CheckUtils.hasNotNull(notNullObj1, notNullObj2));
    }

    @Test
    public void testIsEmpty() {
        assertTrue("array is null, result should be true", CheckUtils.isEmpty(nullArr));
        assertTrue("array is empty, result should be true", CheckUtils.isEmpty(emptyArr));
        assertFalse("array is not null or empty, result should be false", CheckUtils.isEmpty(notEmptyArr));
    }

    @Test
    public void testIsNotEmpty() {
        assertTrue("array is not null or empty, result should be true", CheckUtils.isNotEmpty(notEmptyArr));
        assertFalse("array is empty, result should be false", CheckUtils.isNotEmpty(emptyArr));
        assertFalse("array is , result should be false", CheckUtils.isNotEmpty(nullArr));
    }

}
