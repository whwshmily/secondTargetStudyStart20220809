package util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ProjectCache {
    private static final Map<String, String> CACHE = new HashMap<String, String>();

    static {
        try {
            Properties prop = new Properties();
            prop.load(ProjectCache.class.getClassLoader().getResourceAsStream("application.properties"));
            Enumeration<?> propertyNames = prop.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String key = (String) propertyNames.nextElement();
                CACHE.put(key, prop.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getValue(String key) {
        return CACHE.get(key);
    }

}
