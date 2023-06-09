package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Connection connection = Util.getMySQLConnection();
            //System.out.println("Connection installed");
            try (Statement statement = connection.createStatement()) {
                {
                    //statement.executeUpdate("drop table User");
                    statement.executeUpdate("CREATE TABLE if not exists User (" +
                            "id bigint not null auto_increment," +
                            "name VARCHAR(30) not null," +
                            "lastName VARCHAR(30)," +
                            "age tinyint," +
                            "PRIMARY KEY(id)" +
                            ")");
                    //System.out.println("Table created");
                }
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            Connection connection = Util.getMySQLConnection();
            //System.out.println("Connection installed");
            try (Statement statement = connection.createStatement()) {
                {
                        statement.executeUpdate("DROP TABLE if exists User");
                }
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Connection connection = Util.getMySQLConnection();
            //System.out.println("Connection installed");
            try (Statement statement = connection.createStatement()) {
                {
                    statement.executeUpdate("INSERT INTO User set name = \"" + name +
                            "\", lastName = \"" +lastName +
                            "\", age = " + age);
                }
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            Connection connection = Util.getMySQLConnection();
            //System.out.println("Connection installed");
            try (Statement statement = connection.createStatement()) {
                {
                    statement.executeUpdate("DELETE FROM User WHERE id = " + id);
                }
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            Connection connection = Util.getMySQLConnection();
            //System.out.println("Connection installed");
            try (Statement statement = connection.createStatement()) {
                {
                    ResultSet rs = statement.executeQuery("SELECT * FROM User");
                    while(rs.next()){
                        allUsers.add(new User(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getByte(4)));
                    }
                }
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            Connection connection = Util.getMySQLConnection();
            //System.out.println("Connection installed");
            try (Statement statement = connection.createStatement()) {
                {
                    statement.executeUpdate("TRUNCATE TABLE User");
                }
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
