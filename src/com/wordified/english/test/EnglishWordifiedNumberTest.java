package com.wordified.english.test;

import com.wordified.english.main.EnglishWordifiedNumber;
import junit.framework.Assert;
import junit.framework.TestCase;
import com.commons.WordifiedNumber;

/***
 * The following class represent JUint test cases for the EnglishWordifiedNumber class
 */

public class EnglishWordifiedNumberTest extends TestCase {

    public void testToWords() throws Exception {

        WordifiedNumber wordifiedNumber = new EnglishWordifiedNumber();

        Assert.assertEquals("one", wordifiedNumber.toWords(1));
        Assert.assertEquals("twenty one", wordifiedNumber.toWords(21));
        Assert.assertEquals("one hundred and five", wordifiedNumber.toWords(105));
        Assert.assertEquals("one thousand and five", wordifiedNumber.toWords(1005));
        Assert.assertEquals("one hundred and ten thousand one hundred and five", wordifiedNumber.toWords(110105));
        Assert.assertEquals("one million and ninety nine", wordifiedNumber.toWords(1000099));
        Assert.assertEquals("one million one hundred", wordifiedNumber.toWords(1000100));
        Assert.assertEquals("fifty six million nine hundred and forty five thousand seven hundred and eighty one", wordifiedNumber.toWords(56945781));


        Assert.assertEquals("negative one", wordifiedNumber.toWords(-1));
        Assert.assertEquals("negative twenty one", wordifiedNumber.toWords(-21));
        Assert.assertEquals("negative one hundred and five", wordifiedNumber.toWords(-105));
        Assert.assertEquals("negative one thousand and five", wordifiedNumber.toWords(-1005));
        Assert.assertEquals("negative one hundred and ten thousand one hundred and five", wordifiedNumber.toWords(-110105));
        Assert.assertEquals("negative one million and ninety nine", wordifiedNumber.toWords(-1000099));
        Assert.assertEquals("negative one million one hundred", wordifiedNumber.toWords(-1000100));
        Assert.assertEquals("negative fifty six million nine hundred and forty five thousand seven hundred and eighty one", wordifiedNumber.toWords(-56945781));

    }
}