package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("Utils/db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }

        if (preparedStatement  != null) {
            preparedStatement.close();
            preparedStatement  = null;
        }
    }

    public static void close(Connection connection, PreparedStatement preparedStatement , ResultSet resultSet) throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }

        if (preparedStatement  != null) {
            preparedStatement .close();
            preparedStatement  = null;
        }

        if (resultSet != null) {
            resultSet.close();
            resultSet = null;
        }
    }
}
