package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nataly", "Miney", (byte) 23);
        userService.saveUser("Engi", null, (byte) 19);
        userService.saveUser("Vitalii", "Kraken", (byte) 26);
        userService.saveUser("Vlad", "Mishustin", (byte) 24);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
