package com.wordfifiedclient;

import com.commons.WordifiedNumber;
import com.factory.WordifiedFactory;
import com.wordified.english.main.EnglishWordifiedNumber;

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

    private String selectedLanguage = "English";
    private String selectedOutputType = "1";

    private static final Logger log = Logger.getLogger("WordifiedClient");

    public static void main(String args[]) {

        WordifiedNumberClient client = new WordifiedNumberClient();
        WordifiedNumber wordifiedNumber;

       try{
           System.out.println("Enter language (supported language(s) English)");
           Scanner sc = new Scanner(System.in);
           client.setSelectedLanguage(sc.next());

    //Uncomment the following code to prompt user to enter outPutType

    /*
            System.out.println("Enter output type 1/2");
            client.selectedOutputType = sc.next();
    */

       }catch(InputMismatchException IE){

           log.log(Level.SEVERE, "Improper input" + IE.toString());

       }catch(Exception e){
           log.log(Level.SEVERE,"Exception occurred" + e.toString());
       }

        wordifiedNumber = WordifiedFactory.getWordifiedNumber(client.selectedLanguage);  /* Invoke factory method to
                                                                                            Get appropriate object */

        if((wordifiedNumber)!=null){

                System.out.println(wordifiedNumber.toWords(1000100));
                System.out.println(wordifiedNumber.toWords(0));
                System.out.println(wordifiedNumber.toWords(105));
                System.out.println(wordifiedNumber.toWords(110105));
                System.out.println(wordifiedNumber.toWords(1000099));
                System.out.println(wordifiedNumber.toWords(1000100));
                System.out.println(wordifiedNumber.toWords(56945781));
                System.out.println(wordifiedNumber.toWords(999999999));
                System.out.println(wordifiedNumber.toWords(1000000000));
        }else{
            System.out.println(client.selectedLanguage + " is not yet supported");
        }

    }

    private void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }



}






