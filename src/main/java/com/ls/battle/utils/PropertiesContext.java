package com.ls.battle.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesContext {

    private static final String ALLURE_PROPERTIES = "allure";
    private static final String CONFIG_PROPERTIES = "config";
    private static final String USER_PROPERTIES = "user";
    private static PropertiesContext instance = new PropertiesContext();
    private Properties configMap = new Properties();
    private Properties generalMap = new Properties();

    private PropertiesContext() {
        init();
    }

    public static PropertiesContext getInstance() {
        if (instance == null)
            instance = new PropertiesContext();
        return instance;
    }

    private void init() {
        loadPropertiesFromClassPath(configMap, ALLURE_PROPERTIES);
        loadPropertiesFromClassPath(configMap, CONFIG_PROPERTIES);
        loadPropertiesFromClassPath(configMap, USER_PROPERTIES);
        generalMap.putAll(configMap);
    }

    public String getProperty(String key) {
        String result = System.getProperty(key);
        try {
            if (result == null || result.equals(""))
                result = (String) generalMap.get(key);
        } catch (NullPointerException ignored) {
            System.out.println("Property \"" + key + "\" is missing");
            System.out.println("Using default property.");
        }
        return result;
    }

    private void loadPropertiesFromClassPath(Properties properties, String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream resourceStream = classLoader.getResourceAsStream(fileName + ".properties");

            if (resourceStream != null)
                properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
