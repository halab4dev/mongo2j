package com.github.halab4dev.mongo2j.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * author halab
 */
public class ValidatorTests {

    @Test
    @DisplayName("Test validate null object")
    public void tesValidateNullObject() {
        Integer nullInteger = null;
        Integer notNullInteger = 1;
        assertTrue(Validator.isNull(nullInteger));
        assertFalse(Validator.isNull(notNullInteger));
    }

    @Test
    @DisplayName("Test validate not null object")
    public void tesValidateNotNullObject() {
        Integer nullInteger = null;
        Integer notNullInteger = 1;
        assertFalse(Validator.isNotNull(nullInteger));
        assertTrue(Validator.isNotNull(notNullInteger));
    }

    @Test
    @DisplayName("Test validate empty string")
    public void tesValidateEmptyString() {
        String s1 = null;
        String s2 = "";
        String s3 = "halab";
        assertTrue(Validator.isEmpty(s1));
        assertTrue(Validator.isEmpty(s2));
        assertFalse(Validator.isEmpty(s3));
    }
}
