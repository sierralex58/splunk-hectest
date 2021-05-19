
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
    private long wait = 0;
    private long finalwait = 0;

    public FillIt() throws IOException {
        log = LogManager.getLogger(this.getClass().getName());
        properties = new Properties();
        properties.load(new FileInputStream(System.getProperty("hectest.propertyfile")));

        if(properties.getProperty("hectest.messages") == null || properties.getProperty("hectest.messages").equals("0")) {
            messages = 100;
        } else {
            messages = Integer.parseInt(properties.getProperty("hectest.messages"));
        }

        if(properties.getProperty("hectest.bytes") == null || properties.getProperty("hectest.bytes").equals("0")) {
            bytes = 100;
        } else {
            bytes = Integer.parseInt(properties.getProperty("hectest.bytes"));
        }

        if(properties.getProperty("hectest.pregenerate") == null || properties.getProperty("hectest.pregenerate").equals("true")) {
            pregenerate = true;
        }

        if(properties.getProperty("hectest.wait") == null || properties.getProperty("hectest.wait").equals("0")) {
            wait = 0;
        } else {
            wait = Integer.parseInt(properties.getProperty("hectest.wait"));
        }
        if(properties.getProperty("hectest.finalwait") == null || properties.getProperty("hectest.finalwait").equals("0")) {
            finalwait = 0;
        } else {
            finalwait = Integer.parseInt(properties.getProperty("hectest.finalwait"));
        }
    }

    public void doIt() throws InterruptedException {
        ArrayList<String> arrayList = new ArrayList<>();
        int i = 0;



        if(!pregenerate) {
            log.info(String.format("[%06d] writing %d messages with %d bytes", i++, messages, bytes));

            for(; i < messages; i++) {
                log.info(String.format("[%06d] %s", i, getAlphaNumericString(bytes)));
                if(wait > 0) {
                    Thread.sleep(wait);
                }
            }
        } else {
            log.info(String.format("[%06d] generating %d messages with %d bytes", i++, messages, bytes));
            for(int count = 0; count < messages; count++) {
                arrayList.add(String.format("[%06d] %s", count, getAlphaNumericString(bytes)));
            }

            log.info(String.format("[%06d] writing %d pre-generated messages with %d bytes", i++, messages, bytes));

            for(; i < messages; i++) {
                log.info(arrayList.get(i));
                if(wait > 0) {
                    Thread.sleep(wait);
                }
            }
        }

        if(finalwait > 0) {
            Thread.sleep(finalwait);
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
