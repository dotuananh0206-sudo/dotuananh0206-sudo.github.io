package mobilecity.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class de doc file cau hinh config.properties
 */
public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                System.err.println("CANH BAO: Khong tim thay file config.properties tren classpath!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Loi khi tai file config.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static int getTimeout() {
        String timeout = getProperty("timeout");
        return timeout != null ? Integer.parseInt(timeout) : 10;
    }

    public static boolean isHeadless() {
        String headless = getProperty("headless");
        return Boolean.parseBoolean(headless);
    }
}
