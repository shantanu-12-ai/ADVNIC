package com.jisasoftech.ADVNIC.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            // Load the configuration file
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    /**
     * Reads a property value by its key.
     *
     * @param key The property key to look up.
     * @return The property value.
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in the config.properties file");
        }
        return value.trim();
    }
}



