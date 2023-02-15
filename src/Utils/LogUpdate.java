package Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

public class LogUpdate {
    private static String logPath;
    private static String message= Calendar.getInstance().getTime().toString()+Thread.currentThread().getStackTrace()[2].getMethodName()+"\n";
    static {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("Utils/db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            logPath =properties.getProperty("logPath");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void logUpdate(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(logPath,true);
            fileOutputStream.write(message.getBytes(),0,message.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
