package com.wordfifiedclient;

import com.commons.WordifiedNumber;
import com.factory.WordifiedFactory;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mukul on 08/07/14.
 *
 * The following class acts as a client and consumes method(s) provided by the interface WordifiedNumber
 * to convert numbers to its word representation
 * The class asks user to input the desirable language and the type of output it expects and then uses a Factory method
 * to get instance of appropriate class
 *
 */
class WordifiedNumberClient {

    private static final String ENTER_LANGUAGE_MESSAGE = "Enter language (supported language(s) English)";
    private static final String IMPROPER_INPUT = "Improper input";
    private static final String EXCEPTION_OCCURRED = "Exception occurred";
    private static final String LANGUAGE_NOT_YET_SUPPORTED_ERROR = " is not yet supported";
    private String selectedLanguage = "English";
    private static final Logger log = Logger.getLogger("WordifiedClient");

/*    private String selectedOutputType = "1";*/ //Uncomment to support different output type



    public static void main(String args[]) {

        WordifiedNumberClient client = new WordifiedNumberClient();
        WordifiedNumber wordifiedNumber;

        try{
            System.out.println(ENTER_LANGUAGE_MESSAGE);
            Scanner sc = new Scanner(System.in);
            client.setSelectedLanguage(sc.next());

            //Uncomment to support different output type
/*

            System.out.println("Enter output type 1/2");
            client.selectedOutputType = sc.next();
*/

        }catch(InputMismatchException IE){

            log.log(Level.SEVERE, IMPROPER_INPUT + IE.toString());

        }catch(Exception e){
            log.log(Level.SEVERE, EXCEPTION_OCCURRED + e.toString());
        }

        wordifiedNumber = WordifiedFactory.getWordifiedNumber(client.selectedLanguage);  /* Invoke factory method to
                                                                                            Get appropriate object */

        if((wordifiedNumber)!=null){

            System.out.println("1000100 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(1000100));
            System.out.println("0 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(0));
            System.out.println("105 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(105));
            System.out.println("110105 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(110105));
            System.out.println("1000099 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(1000099));
            System.out.println("2000100 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(2000100));
            System.out.println("56945781 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(56945781));
            System.out.println("999999999 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(999999999));
            System.out.println("1000000000 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(1000000000));


            System.out.println("-2000100 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(-2000100));
            System.out.println("-56945781 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(-56945781));
            System.out.println("-999999999 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(-999999999));
            System.out.println("-1000000000 in " + client.selectedLanguage + " is = " + wordifiedNumber.toWords(-1000000000));

        }else{
            System.out.println(client.selectedLanguage + LANGUAGE_NOT_YET_SUPPORTED_ERROR);
        }

    }

    private void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }



}






