package com.corkili.pa.validation.validator;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.corkili.pa.validation.annotation.DoubleConstraint;
import com.corkili.pa.validation.annotation.DoubleRange;
import com.corkili.pa.validation.annotation.DoubleValue;
import com.corkili.pa.validation.annotation.IntConstraint;
import com.corkili.pa.validation.annotation.IntRange;
import com.corkili.pa.validation.annotation.StrObjMapConstraint;
import com.corkili.pa.validation.annotation.StrObjMapValue;
import com.corkili.pa.validation.annotation.StringConstraint;
import com.corkili.pa.validation.annotation.Validated;

public class ValidateDriverManagerTest {

    @Test
    public void testDriver() throws Exception {
        Class.forName("com.corkili.pa.validation.validator.def.DefaultValidateDriver");
        ValidateDriver driver = ValidateDriverManager.getInstance()
                .getDriverByName("com.corkili.pa.validation.validator.def.DefaultValidateDriver");
        Person p1 = new Person("bob", 20);
        p1.infos.put("score", 60.0);
        System.out.println(driver.validate(p1, Person.class));
    }

    @Validated
    class Person {

        @StringConstraint(lengthRanges = {@IntRange(min = 3, max = 20)})
        private String name;

        @IntConstraint(ranges = {@IntRange(min = 0, max = 200)})
        private int age;

        @StrObjMapConstraint({
                @StrObjMapValue(key = "score", valueType = Double.class, validateDouble = true,
                        doubleConstraint = @DoubleConstraint(ranges = @DoubleRange(
                                min = @DoubleValue(value = 0.0, precision = 0.01),
                                max = @DoubleValue(value = 100.0, precision = 0.01))))
        })
        private Map<String, Object> infos;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            this.infos = new HashMap<>();
        }
    }

}