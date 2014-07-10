package com.factory;

import com.commons.WordifiedNumber;
import com.wordified.english.main.EnglishWordifiedNumber;

/**
 * Created by mukul on 09/07/14.
 *
 * The following class provides a factory method which constructs appropriate object and returns to the calling class
 *
 */
public class WordifiedFactory {

    private static final String ENGLISH = "English";

    public static WordifiedNumber getWordifiedNumber(String selectedLanguage){
        if(selectedLanguage.equalsIgnoreCase(ENGLISH)){
            return new EnglishWordifiedNumber();
        }else
            return null;
    }



    //Uncomment to support different output type

    /**
     * This method would be of importance in future releases when output type is passed
     * @param selectedLanguage
     * @param selectedOutputType
     * @return
     */
/*
    public static WordifiedNumber getWordifiedNumber(String selectedLanguage, String selectedOutputType ){
        if(selectedLanguage.equalsIgnoreCase(ENGLISH)){
            return new EnglishWordifiedNumber(selectedOutputType);
        }else
            return null;
    }

*/

}
