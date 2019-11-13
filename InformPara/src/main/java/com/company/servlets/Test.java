package com.company.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBRegistration", "postgres", "frnbvtkm");
        Statement statement = connection.createStatement();
        String sqlQueryAddUser = "INSERT INTO mysiteusers(Email,Login,Password) VALUES('amir@mail.ru','amir','asdfg')";
        //statement.executeQuery(sqlQueryAddUser);
    }
}
