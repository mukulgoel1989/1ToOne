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

        int indexOfDigit = 9;   //Start with index equal to Billion

        while(indexOfDigit > 1){
            int digitValue = (int)Math.pow(10, indexOfDigit);

            if(number/digitValue >0){ //Check if the number lies in the this digitValue if yes then

                result.append(toWords(number / digitValue));    //Get word for the digit at the index eg: one,two,three etc
                result.append(spaceString);                     //Append space
                result.append(getRootNumberWord(digitValue));   //Get word for the index position eg: billion, million, etc

                number = number % digitValue;  //Reduce number by taking remainder

                if(number > 0) { //if more places
                    result.append(spaceString);
                }
            }

            //The if/else/if statements reduce index to next stage

            if(indexOfDigit==9){
                indexOfDigit=6;
            }else if(indexOfDigit ==6){
                indexOfDigit = 3;
            }else if(indexOfDigit ==3){
                indexOfDigit = 2;
            }else {
                indexOfDigit = 0;
            }
        }  //End While

        if(number > 0) {  //if number is > 0
            if (result.length() > 0) { //if already something in sentence, this will be false if number is less than 100
                result.append(andString);
                result.append(spaceString);
            }
            if (number < 20) { //Number below 20 have unique names
                result.append(getRootNumberWord(number)); //if so, get the word and append to result
            } else {
                result.append(getRootNumberWord((number / 10) * 10));  //get the word for the tens place eg: if number is 99 this would give "ninety"
                int numberModTen = number % 10; //Get the number at unit place
                if (numberModTen > 0) { //if not zero
                    result.append(spaceString);
                    result.append(getRootNumberWord(numberModTen)); //Get word for unit place
                }
            }
        }
    return result.toString();
    }

/*
   Uncomment the below method to support type 2 output
*/

/*
    protected String getRootNumberWord(int number, int indexOfDigit){
        StringBuilder numberWord = new StringBuilder();
        if(number<20){
            if(outputType.equals("2")&& indexOfDigit==0){
                numberWord.append(rootNumberMap.get(Integer.valueOf(rootNumKeysType2[number])));

            }
        }else{
            numberWord.append(getRootNumberWord(number));
        }
        return numberWord.toString();
    }
*/


}

