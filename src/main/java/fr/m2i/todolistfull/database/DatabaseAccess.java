package fr.m2i.todolistfull.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseAccess implements AutoCloseable {
    private static final String HOST = "jdbc:mysql://localhost:3306/todolistfull";
    private static final String USER = "root";
    private static final String PASSWORD = "Ifen76srfc35";
    private Connection connection;

    private static DatabaseAccess instance = null;


    private DatabaseAccess() {
        createConnection();
    }

    private void createConnection() {
        try {
            connection = DriverManager.getConnection(HOST, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseAccess getInstance() {
        if (instance == null) {
            instance = new DatabaseAccess();
        }
        return instance;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
