package org.pet_store.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    private static String baseURL = null;
    private static Properties properties = null;


    public static String getBaseURL(){

        if(baseURL == null){
            if(properties == null){
                loadProperties();
            }
            baseURL = properties.getProperty("base_url");
        }

        return baseURL;
    }

    private static void loadProperties(){
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE_PATH));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
