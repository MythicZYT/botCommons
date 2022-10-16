package me.PolakOn420.botcommons.config;

import me.duncte123.botcommons.JSONHelper;

import java.io.File;
import java.io.IOException;

public class ConfigUtils {

    public static <T> T loadFromFile(String fileName, Class<T> classOfT) throws IOException {
        return loadFromFile(new File(fileName), classOfT);
    }

    public static <T> T loadFromFile(File file, Class<T> classOfT) throws IOException {
        return JSONHelper.createObjectMapper().readValue(file, classOfT);
    }
}
