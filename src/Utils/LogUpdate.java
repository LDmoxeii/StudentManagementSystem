package Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Properties;

public class LogUpdate {
    private static String logPath;
    private static InputStream inputStream;
    private static String message =
        Calendar.getInstance().getTime().toString() + "\t"+Thread.currentThread().getStackTrace()[2].getMethodName() + "\n";
    private static BufferedWriter bufferedWriter;
    static {
        try {
            inputStream = ClassLoader.getSystemResourceAsStream("Utils/db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            logPath = properties.getProperty("logPath");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void logUpdate() {
        try {
            bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logPath, true), StandardCharsets.UTF_8));
            bufferedWriter.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                bufferedWriter = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
