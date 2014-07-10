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
    private static final int INDEX_BILLION = 9;
    private static final int INDEX_MILLION = 6;
    private static final int INDEX_THOUSAND = 3;
    private static final int INDEX_HUNDERED = 2;
    private static final String PROPERTIES_EXTENSION = ".properties";


    /**
     * Default constructor for the class
     * Initializes parameters for english language and default outputType = 1
     */
    public EnglishWordifiedNumber(){
        setPathToGeneralProperties(PATH_TO_GENERAL_PROPERTIES);
        getConnectorsFromProperties();
        String fileName = LANGUAGE + "_" + outputType + PROPERTIES_EXTENSION;
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
        String fileName = LANGUAGE + "_" + outputType + PROPERTIES_EXTENSION;
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
            return getRootNumberWord(number);
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

        int indexOfDigit = INDEX_BILLION;   //Start with index equal to Billion

        while(indexOfDigit > 1){
            int digitValue = (int)Math.pow(10, indexOfDigit);

            if(number/digitValue >0){                                         //Check if the number lies in the this digitValue if yes then

                result.append(toWords(number / digitValue));                  //Get word for the digit at the index eg: one,two,three etc
                result.append(spaceString);                                   //Append space
                result.append(getRootNumberWord(digitValue,indexOfDigit));   //Get word for the index position eg: billion, million, etc

                number = number % digitValue;                               //Reduce number by taking remainder

                if(number > 0) {                                            //if more places
                    result.append(spaceString);
                }
            }

            //The if/else/if statements reduce index to next stage

            if(indexOfDigit == INDEX_BILLION){
                indexOfDigit = INDEX_MILLION;
            }else if(indexOfDigit == INDEX_MILLION){
                indexOfDigit = INDEX_THOUSAND;
            }else if(indexOfDigit == INDEX_THOUSAND){
                indexOfDigit = INDEX_HUNDERED;
            }else {
                indexOfDigit = 0;
            }
        }  //End While

        if(number > 0) {  //if number is > 0
            if (result.length() > 0) {                                              /*if already something in sentence,
                                                                                     this will be false if number is less than 100*/
                result.append(andString);
                result.append(spaceString);
            }
            if (number < 20) {                                                         //Number below 20 have unique names
                result.append(getRootNumberWord(number,indexOfDigit));                 //if so, get the word and append to result
            } else {
                result.append(getRootNumberWord(((number / 10) * 10),indexOfDigit));  /*get the word for the tens place
                                                                                       eg: if number is 99 this would give "ninety"*/

                int numberModTen = number % 10;                                       //Get the number at unit place
                if (numberModTen > 0) { //if not zero
                    result.append(spaceString);
                    result.append(getRootNumberWord(numberModTen,indexOfDigit)); //Get word for unit place
                }
            }
        }
        return result.toString();
    }


    protected String getRootNumberWord(int number, int indexOfDigit){
        StringBuilder numberWord = new StringBuilder();
        if(outputType.equals("1")) {
            numberWord.append(getRootNumberWord(number));
        }else if(outputType.equals("2")){
            //Code to get values for type 2 output
        }

        return numberWord.toString();
    }


}

