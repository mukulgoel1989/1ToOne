package com.wordified.english.main;

import com.commons.RootMaps;
import com.commons.WordifiedNumber;

/**
 * Created by mukul on 08/07/14.
 *
 * The following class provides an implementation for the method toWords() of Interface WordifiedNumber.
 * This implementation takes in a integer and returns its sentence representation
 */


public class EnglishWordifiedNumber extends RootMaps implements WordifiedNumber {

    private static final String LANGUAGE ="english";
    private static final String PATH_TO_GENERAL_PROPERTIES = "/com/wordified/messages/english/" +
            "englishGeneralWords.properties";



    /**
     * Default constructor for the class
     * Initializes parameters for english language and default outputType = 1
     */
    public EnglishWordifiedNumber(){
        setPathToGeneralProperties(PATH_TO_GENERAL_PROPERTIES);
        getConnectorsFromProperties();
        String fileName = LANGUAGE + "_" + outputType + ".properties";
        setPathOfPropertiesFile(LANGUAGE, fileName);
        populateMapFromProperties();
    }

    /**
     * This constructor will allow the client to pass in the outputType
     * which will decide which properties file is used.
     */

    public EnglishWordifiedNumber(String outputType){
        setPathToGeneralProperties(PATH_TO_GENERAL_PROPERTIES);
        this.outputType = outputType;
        getConnectorsFromProperties();
        String fileName = LANGUAGE + "_" + outputType + ".properties";
        setPathOfPropertiesFile(LANGUAGE, fileName);
        populateMapFromProperties();
    }


    /**
     * The following method overrides the method from interface takes in a
     * number and returns its sentence representation
     * @param number number
     * @return sentence representation of the number
     */
    @Override
    public String toWords(int number){

        if(number==0){  //Check if number is 0
            return getRootNumberWord(0);
        }

        if(number < 0){  //Check if number is a negative number
            return negativeString + spaceString + toWords(Math.abs(number));
        }

        return formNumberWord(number);
    }

    /**
     * The following method builds up string representation for a
     * positive non zero number
     * @param number Input number
     * @return return string
     */
    private String formNumberWord(int number) {
        StringBuilder result = new StringBuilder();
        int indexOfDigit = 9;
        while(indexOfDigit > 1){
            int digitValue = (int)Math.pow(10, indexOfDigit);
            if(number/digitValue >0){
                result.append(toWords(number / digitValue));
                result.append(spaceString);
                result.append(getRootNumberWord(digitValue));
                number = number % digitValue;
                if(number > 0) {
                    result.append(spaceString);
                }
            }
            if(indexOfDigit==9){
                indexOfDigit=6;
            }else if(indexOfDigit ==6){
                indexOfDigit = 3;
            }else if(indexOfDigit ==3){
                indexOfDigit = 2;
            }else {
                indexOfDigit = 0;
            }
        }
        if(number > 0) {
            if (result.length() > 0) {
                result.append(andString);
                result.append(spaceString);
            }
            if (number < 20) {
                result.append(getRootNumberWord(number));
            } else {
                result.append(getRootNumberWord((number / 10) * 10));
                int numberModTen = number % 10;
                if (numberModTen > 0) {
                    result.append(spaceString);
                    result.append(getRootNumberWord(numberModTen));
                }
            }
        }
    return result.toString();
    }

}

