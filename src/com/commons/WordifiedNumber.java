package com.commons;

/**
 * Created by mukul on 07/07/14.
 *
 * The following interface provides an interface for the client to get sentence representation of a number
 *
 */

public interface WordifiedNumber {

    /**
     * The following method will take in a number and return its sentence representation
     * @param number : the input number
     * @return string which is sentence representation of the number
     */
    String toWords(int number);
}
