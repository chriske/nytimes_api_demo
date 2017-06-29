package hu.autsoft.nytimes.mock.util;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class JavaResourceUtil {
    private static Gson gson;
    private static ClassLoader classLoader;

    public static void init(Gson gson, ClassLoader classLoader) {
        JavaResourceUtil.gson = gson;
        JavaResourceUtil.classLoader = classLoader;
    }

    private static Gson getGson() {
        if (gson == null) {
            throw new RuntimeException("Gson not initialized in " + JavaResourceUtil.class.getSimpleName() + "! Use init method to initialize");
        }
        return gson;
    }

    private static ClassLoader getClassLoader() {
        if (classLoader == null) {
            throw new RuntimeException("ClassLoader not initialized in " + JavaResourceUtil.class.getSimpleName() + "! Use init method to initialize");
        }
        return classLoader;
    }

    public static <T> T getObjectFromJsonResource(String resourcePath, Type type) {
        String jsonString = getAssetFileAsString(resourcePath);
        return getGson().fromJson(jsonString, type);
    }

    public static <T> T getObjectFromJsonResource(String resourcePath, Class<T> objectClass) {
        String jsonString = getAssetFileAsString(resourcePath);
        return getGson().fromJson(jsonString, objectClass);
    }

    public static String getAssetFileAsString(String resourcePath) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = getClassLoader().getResourceAsStream(resourcePath);
            byte[] buffer = new byte[2048];
            int readSize;
            while ((readSize = inputStream.read(buffer)) >= 0) {
                sb.append(new String(buffer, 0, readSize));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}