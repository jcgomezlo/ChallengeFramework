package utils.reader;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {


    private Properties properties;
    private static Logger LOGGER = Logger.getLogger(PropertiesReader.class);

    public PropertiesReader(String path){
        properties= new Properties();

        try{
            properties.load(new FileInputStream(path));
        } catch (Exception e){
            LOGGER.error(e.getMessage());
        }

    }

    public Properties getProperties() {
        return properties;
    }

    public String getValue(String value){
        return (String) properties.get(value);
    }
}
