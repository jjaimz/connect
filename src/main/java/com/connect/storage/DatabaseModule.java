package com.connect.storage;

//import com.connect.tasks.Task;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseModule {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl( "jdbc:mysql://localhost:3306/connect");
        config.setUsername( "shubh" );
        config.setPassword( "password" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

//    public static List<Task> getTasks() throws SQLException {
//        List<Task> tasks = new ArrayList<Task>();
//
//        try (Connection connection = DatabaseModule.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks");
//
//            ResultSet resultSet = statement.executeQuery();
//
//            Task task = null;
//
//            while (resultSet.next()) {
//                task = new Task();
//                task.setTaskId(resultSet.getInt("taskid"));
//                task.setTaskowner(resultSet.getString("taskowner"));
//                task.setTask(resultSet.getString("task"));
//                task.setAttributes(resultSet.getString("attributes"));
//                task.setExecuteon(resultSet.getString("executeon"));
//                task.setStatus(resultSet.getString("status"));
//
//                if (task != null) {
//                    tasks.add(task);
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return tasks;
//    }
}
