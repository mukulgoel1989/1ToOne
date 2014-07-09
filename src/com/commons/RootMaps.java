package com.commons;

import com.sun.org.apache.xml.internal.utils.Constants;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mukul on 09/07/14.
 *
 * The following class provides a map and methods to populate it
 * The map, rootNumberMap is populated from appropriate rootNumberProperties file depending on the language selected (default english)
 * The rootNumberProperties file contains mapping of number to its word names in the language.
 *
 * The class also provides basic sentence connector properties for a language (and/negative/spaces) and methods to load them from properties file too
 *
 */

public abstract class RootMaps {

    //Keys in properties file
    protected static final String AND_STRING_KEY = "AND";
    protected static final String NEGATIVE_STRING_KEY = "NEGATIVE";
    protected static final String SPACE_STRING_KEY = "SPACE";
    private final String BASE_PATH = "/com/wordified/messages/";

    private static final Logger log = Logger.getLogger("RootMaps");


    //A general properties file specifies values related to sentence connectors such as and/string etc
    protected String pathToGeneralProperties;

    //rootNumberMap maintains key value pairs for number and its word in the language
    //eg: key 1 , value one etc
    protected HashMap<Integer, String> rootNumberMap = new HashMap<Integer, String>();

    protected Properties generalProperties;

    //Default values for connectors
    protected String andString = "";
    protected String negativeString = "";
    protected String spaceString = " ";

    protected String outputType = "1"; /*Extension mechanism, allow different types of output to be printed
                                        =1 would pick LANGUAGE_1 properties file
                                        =2 would use LANGUAGE_2 properties file

                                        So, client can configure which type of output to use
                                        */

    private Properties rootNumberProperties;
    private String pathOfRootNumberPropertiesFile;

    /**
     * Invokes the method getPropertiesRef() to get rootNumberProperties file reference
     * and iterates over the keys and stores key value pairs in the rootNumberMap
     */

    protected void populateMapFromProperties() {
        getPropertiesRef();
        if (rootNumberProperties != null) {
            for (String key : rootNumberProperties.stringPropertyNames()) {
                String value = rootNumberProperties.getProperty(key);
                rootNumberMap.put(Integer.parseInt(key), value);
            }
        }
    }

    /**
     * Opens up the rootNumberProperties file referred by @pathOfRootNumberPropertiesFile and stores the reference
     * in @rootNumberProperties variable
     */
    private void getPropertiesRef(){
        if(rootNumberProperties ==null ){
            rootNumberProperties = new Properties();
            try{
                rootNumberProperties.load(Constants.class.getResourceAsStream(pathOfRootNumberPropertiesFile));
            }catch(Exception e){
                log.log(Level.SEVERE, "Unable to load properties "+ pathOfRootNumberPropertiesFile);
            }
        }
    }

    protected void setPathOfPropertiesFile(String language, String pathOfPropertiesFile) {
        this.pathOfRootNumberPropertiesFile = BASE_PATH + language + "/" +  pathOfPropertiesFile;
    }

    public void setPathToGeneralProperties(String pathToGeneralProperties) {
        this.pathToGeneralProperties = pathToGeneralProperties;
    }


    /**
     * Loads connector words from the properties file
     */
    protected void getConnectorsFromProperties() {
        if(generalProperties ==null ){
            generalProperties = new Properties();
            try{
                generalProperties.load(Constants.class.getResourceAsStream(pathToGeneralProperties));
            }catch(Exception e){
                System.out.println("Unable to load " + pathToGeneralProperties);
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
    protected String getRootNumberWord(int number){
        return rootNumberMap.get(Integer.valueOf(number));
    }
}
