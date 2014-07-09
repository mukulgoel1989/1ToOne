package com.wordified.english.main;

import com.commons.RootMaps;
import com.commons.WordifiedNumber;
import java.util.Properties;

import com.sun.org.apache.xml.internal.utils.Constants;

/**
 * Created by mukul on 08/07/14.
 *
 * The following class provides an implementation for the method toWords() of Interface WordifiedNumber.
 * This implementation takes in a integer and returns its sentence representation
 */


public class EnglishWordifiedNumber extends RootMaps implements WordifiedNumber {

    //Keys in properties file
    private static final String AND_STRING_KEY = "AND";
    private static final String NEGATIVE_STRING_KEY = "NEGATIVE";
    private static final String SPACE_STRING_KEY = "SPACE";
    private static final String LANGUAGE ="english";

    //A general properties file specifies values related to sentence connectors such as and/string etc
    private static final String PATH_TO_GENERAL_PROPERTIES = "/com/wordified/messages/english/" +
                                                             "englishGeneralWords.properties";

    private Properties generalProperties;

    //Default values for connectors
    private String andString = "and";
    private String negativeString = "negative";
    private String spaceString = " ";




    private String outputType = "1"; /*Extension mechanism, allow different types of output to be printed
                                        =1 would pick LANGUAGE_1 properties file
                                        =2 would use LANGUAGE_2 properties file

                                        So, client can configure which type of output to use
                                        */


    /**
     * Default constructor for the class
     * Initializes parameters for english language and default outputType = 1
     */
    public EnglishWordifiedNumber(){
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
        this.outputType = outputType;
        getConnectorsFromProperties();
        String fileName = LANGUAGE + "_" + outputType + ".properties";
        setPathOfPropertiesFile(LANGUAGE, fileName);
        populateMapFromProperties();
    }

    /**
     * Loads connector words from the properties file
     */
    private void getConnectorsFromProperties() {
        if(generalProperties ==null ){
            generalProperties = new Properties();
            try{
                generalProperties.load(Constants.class.getResourceAsStream(PATH_TO_GENERAL_PROPERTIES));
            }catch(Exception e){
                System.out.println("Unable to load " + PATH_TO_GENERAL_PROPERTIES);
                System.exit(1);
            }
        }
        this.andString = (String) generalProperties.get(AND_STRING_KEY);
        this.negativeString = (String)generalProperties.get(NEGATIVE_STRING_KEY);
        this.spaceString = (String)generalProperties.get(SPACE_STRING_KEY);
    }


    /**
     * Gets the word representation for the digit from the rootNumberMap
     * @param number input number
     * @return string representation of number
     */
    private String getRootNumberWord(int number){
        return rootNumberMap.get(Integer.valueOf(number));
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

        String result = formNumberWord(number);

        return result;
    }

    /**
     * The following method builds up string representation for a
     * positive non zero number
     * @param number
     * @return
     */
    private String formNumberWord(int number) {
        StringBuilder result = new StringBuilder();
        int digitIndex = 9;
        while(digitIndex > 1){
            int digitValue = (int)Math.pow(10, digitIndex);
            if(number/digitValue >0){
                result.append(toWords(number / digitValue));
                result.append(spaceString);
                result.append(getRootNumberWord(digitValue));
                number = number % digitValue;
                if(number > 0) {
                    result.append(spaceString);
                }
            }
            if(digitIndex==9){
                digitIndex=6;
            }else if(digitIndex ==6){
                digitIndex = 3;
            }else if(digitIndex ==3){
                digitIndex = 2;
            }else {
                digitIndex = 0;
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

