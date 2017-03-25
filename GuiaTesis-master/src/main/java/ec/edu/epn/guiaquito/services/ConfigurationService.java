package ec.edu.epn.guiaquito.services;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Startup
@Singleton
public class ConfigurationService {

    public static String GEO_API_CONTEXT;
    public static String FS_FOOD_ID;
    public static String FS_HOTEL_ID;
    public static String FS_CHURCH_ID;
    public static String FS_ENTERTAINMENT_ID;

    @PostConstruct
    public void init() {
        readProperties();
    }

    public void readProperties() {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            input = classLoader.getResourceAsStream("app.properties");

            properties.load(input);

            GEO_API_CONTEXT = properties.getProperty("GEO_API_CONTEXT");
            FS_FOOD_ID = properties.getProperty("FS_FOOD_ID");
            FS_HOTEL_ID = properties.getProperty("FS_HOTEL_ID");
            FS_CHURCH_ID = properties.getProperty("FS_CHURCH_ID");
            FS_ENTERTAINMENT_ID = properties.getProperty("FS_MUSEUM_ID");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
