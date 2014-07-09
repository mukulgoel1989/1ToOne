package com.commons;

import com.sun.org.apache.xml.internal.utils.Constants;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created by mukul on 09/07/14.
 *
 * The following class provides a map and methods to populate it
 * The map, rootNumberMap is populated from appropriate rootNumberProperties file depending on the language selected (default english)
 * The rootNumberProperties file contains mapping of number to its word names in the language.
 */

public abstract class RootMaps {

    //rootNumberMap maintains key value pairs for number and its word in the language
    //eg: key 1 , value one etc
    protected HashMap<Integer, String> rootNumberMap = new HashMap<Integer, String>();

    private Properties rootNumberProperties;
    private final String BASE_PATH = "/com/wordified/messages/";

    private String pathOfPropertiesFile;

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
     * Opens up the rootNumberProperties file referred by @pathOfPropertiesFile and stores the reference
     * in @rootNumberProperties variable
     */
    private void getPropertiesRef(){

        if(rootNumberProperties ==null ){
            rootNumberProperties = new Properties();
            try{
                rootNumberProperties.load(Constants.class.getResourceAsStream(pathOfPropertiesFile));
            }catch(Exception e){
                System.out.println("Unable to load " + pathOfPropertiesFile);
                System.exit(1);
            }
        }
    }

    protected void setPathOfPropertiesFile(String language, String pathOfPropertiesFile) {
        this.pathOfPropertiesFile = BASE_PATH + language + "/" +  pathOfPropertiesFile;
    }


}
