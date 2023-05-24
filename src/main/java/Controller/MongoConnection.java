package Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class MongoConnection {
    protected static String uriString() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("C:\\Users\\Mateus\\Desktop\\COMMITS\\Java_Mongo\\src\\main\\resources\\uri_key.properties"));
        return prop.getProperty("uri_key");
    }
}
