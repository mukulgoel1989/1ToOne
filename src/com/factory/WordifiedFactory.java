package com.factory;

import com.commons.WordifiedNumber;
import com.wordified.english.main.EnglishWordifiedNumber;

/**
 * Created by mukul on 09/07/14.
 */
public class WordifiedFactory {

    public static WordifiedNumber getWordifiedNumber(String selectedLanguage){
        if(selectedLanguage.equalsIgnoreCase("English")){
            return new EnglishWordifiedNumber();
        }else
            return null;
    }


    public static WordifiedNumber getWordifiedNumber(String selectedLanguage, String selectedOutputType ){
        if(selectedLanguage.equalsIgnoreCase("English")){
            return new EnglishWordifiedNumber(selectedOutputType);
        }else
            return null;
    }



}
