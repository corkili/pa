package com.corkili.pa.validation.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.corkili.pa.validation.annotation.IntConstraint;
import com.corkili.pa.validation.annotation.IntRange;
import com.corkili.pa.validation.annotation.StringConstraint;
import com.corkili.pa.validation.annotation.Validated;

public class ValidateDriverManagerTest {

    @Test
    public void testDriver() throws Exception {
        Class.forName("com.corkili.pa.validation.validator.def.DefaultValidateDriver");
        ValidateDriver driver = ValidateDriverManager.getInstance()
                .getDriverByName("com.corkili.pa.validation.validator.def.DefaultValidateDriver");
        Person p1 = new Person("bob", 20);
        System.out.println(driver.validate(p1, Person.class));
        System.out.println(driver.validate(new Person("a", 1000), Person.class));
    }

    @Validated
    class Person {

        @StringConstraint(lengthRanges = {@IntRange(min = 3, max = 20)})
        private String name;

        @IntConstraint(ranges = {@IntRange(min = 0, max = 200)})
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

}