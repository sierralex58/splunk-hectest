
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class FillIt {
    private Logger log ;
    private Properties properties;
    private int bytes;
    private int messages;
    private boolean pregenerate = false;

    public FillIt() throws IOException {
        log = LogManager.getLogger(this.getClass().getName());
        properties = new Properties();
        properties.load(new FileInputStream(System.getProperty("hectest.propertyfile")));

        if(properties.getProperty("hectest.messages") == null || properties.getProperty("hectest.messages") == "0") {
            messages = 100;
        } else {
            messages = Integer.parseInt(properties.getProperty("hectest.messages"));
        }

        if(properties.getProperty("hectest.bytes") == null || properties.getProperty("hectest.bytes") == "0") {
            bytes = 100;
        } else {
            bytes = Integer.parseInt(properties.getProperty("hectest.bytes"));
        }

        if(properties.getProperty("hectest.pregenerate") == null || properties.getProperty("hectest.pregenerate") == "true") {
            pregenerate = true;
        }
    }

    public void doIt() {
        ArrayList<String> arrayList = new ArrayList<>();


        if(!pregenerate) {
            log.info("writing " + messages + " messages " + " with " + bytes + " bytes");

            for(int i = 0; i < messages; i++) {
                log.info(String.format("[%06d] %s", i, getAlphaNumericString(bytes)));
            }
        } else {
            log.info("generating " + messages + " messages with " + bytes + " bytes");
            for(int i = 0; i < messages; i++) {
                arrayList.add(String.format("[%06d] %s", i, getAlphaNumericString(bytes)));
            }

            log.info("writing " + messages + " pre-generated messages");
            for(int i = 0; i < messages; i++) {
                log.info(arrayList.get(i));
            }
        }


        LogManager.shutdown();
    }

    static String getAlphaNumericString(int n)
    {

        // lower limit for LowerCase Letters
        int lowerLimit = 97;

        // lower limit for LowerCase Letters
        int upperLimit = 122;

        Random random = new Random();

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer(n);

        for (int i = 0; i < n; i++) {

            // take a random value between 97 and 122
            int nextRandomChar = lowerLimit
                    + (int)(random.nextFloat()
                    * (upperLimit - lowerLimit + 1));

            // append a character at the end of bs
            r.append((char)nextRandomChar);
        }

        // return the resultant string
        return r.toString();
    }

}
