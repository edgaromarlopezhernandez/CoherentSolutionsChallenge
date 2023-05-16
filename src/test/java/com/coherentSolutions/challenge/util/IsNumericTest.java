package com.coherentSolutions.challenge.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class IsNumericTest {
    @DisplayName("Should return false if string to validate is null ")
    @Test
    void shouldReturnFalseIfStringToValidateIsNull() {
        Assertions.assertFalse(IsNumeric.verify(null));
    }

    @DisplayName("Should return false if string to validate is empty")
    @Test
    void shouldReturnFalseIfStringToValidateIsEmpty() {
        Assertions.assertFalse(IsNumeric.verify(""));
    }

    @DisplayName("Should return false if the string to validate has a length of one and is -")
    @Test
    void  shouldReturnFalseIfTheStringToValidateHasALengthOfOneAndIsMinusSign() {
        Assertions.assertFalse(IsNumeric.verify("-"));
    }

    @DisplayName("Should return false if the string to validate is not numeric")
    @Test
    void  shouldReturnFalseIfTheStringToValidateIsNotNumeric() {
        Assertions.assertFalse(IsNumeric.verify("InvalidString"));
    }

    @DisplayName("Should return true if the string to validate is numeric -> Happy path:)!")
    @Test
    void  shouldReturnTrueIfTheStringToValidateIsNumeric() {
        Assertions.assertTrue(IsNumeric.verify("-3"));
    }


    @DisplayName("Should return false if string to validate is null in unsigned method")
    @Test
    void shouldReturnFalseIfStringToValidateIsNullForNaturalNumbers() {
        Assertions.assertFalse(IsNumeric.verifyUnsigned(null));
    }

    @DisplayName("Should return false if string to validate is empty in unsigned method ")
    @Test
    void shouldReturnFalseIfStringToValidateIsEmptyForNaturalNumbers() {
        Assertions.assertFalse(IsNumeric.verifyUnsigned(""));
    }

    @DisplayName("Should return false if the string to validate is not numeric in unsigned method")
    @Test
    void  shouldReturnFalseIfTheStringToValidateIsNotNumericForNaturalNumbers() {
        Assertions.assertFalse(IsNumeric.verifyUnsigned("InvalidString"));
    }

    @DisplayName("Should return true if the string to validate is numeric in unsigned method -> Happy path:)!")
    @Test
    void  shouldReturnTrueIfTheStringToValidateIsNumericForNaturalNumbers() {
        Assertions.assertTrue(IsNumeric.verify("777"));
    }
}
