package com.syphan.bringglobal.dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DbConnection {

    private static final Logger logger = Logger.getLogger(DbConnection.class.getName());

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try {
            Properties prop = setUpProperty();
            String driver = prop.getProperty("driver-class-name");
            String url = prop.getProperty("url");
            String user = prop.getProperty("username");
            String password = prop.getProperty("password");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            logger.severe("getConnection() -> ClassNotFoundException: " + e.getMessage());
        } catch (SQLException e) {
            logger.severe("getConnection() -> SQLException: " + e.getMessage());
        } catch (FileNotFoundException e) {
            logger.severe("getConnection() -> FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            logger.severe("getConnection() -> IOException: " + e.getMessage());
        }
        return connection;
    }

    private static void initializeDatabase() throws IOException, SQLException {
        InputStream inputStream = DbConnection.class.getClassLoader().getResourceAsStream("initial.sql");

        if(inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder scriptSql = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                scriptSql.append(linha).append("\n");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(scriptSql.toString());
            preparedStatement.executeUpdate();
        }
    }

    private static Properties setUpProperty() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = DbConnection.class.getClassLoader().getResourceAsStream("application.properties");
        prop.load(inputStream);
        return prop;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.severe("closeConnection() -> SQLException: " + e.getMessage());
        }
    }
}
