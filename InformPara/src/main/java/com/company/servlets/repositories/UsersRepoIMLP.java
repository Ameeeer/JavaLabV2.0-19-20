package com.company.servlets.repositories;

import com.company.servlets.models.User;

import java.sql.*;
import java.util.Optional;

public class UsersRepoIMLP implements UsersRepository {
    private Connection connection;

    public UsersRepoIMLP(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<User> userRowMapper = resultSet -> new User(
            resultSet.getString("email"),
            resultSet.getString("login"),
            resultSet.getString("password")
    );
    private RowMapper<User> userEmailUniqueCheck = resultSet -> new User(
            resultSet.getString("email")
    );
    private RowMapper<User> rowMapperWithAllInfo = resultSet -> new User(
            resultSet.getString("email"),
            resultSet.getString("login"),
            resultSet.getString("password"),
            resultSet.getString("country"),
            resultSet.getString("infoaboutuser")
    );
//    private RowMapper<User> userRole = resultSet -> new User(
//            resultSet.getString("email"),
//            resultSet.getString("email"),
//            resultSet.getString("email"),
//            resultSet.getString("email"),
//            resultSet.getString("email")
//    );

    public boolean save(User user) {
        String sqlQueryAddUser = "INSERT INTO mysiteusers(email,login,password,country,infoaboutuser) VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryAddUser)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getCountry());
            preparedStatement.setString(5, user.getInfo());
            boolean ret = preparedStatement.execute();
            System.out.println(ret);
            return ret;
        } catch (SQLException e) {
            return true;
        }
    }

    @Override
    public boolean checkUser(User user) {
        String sqlQ = "SELECT mysiteusers.email from mysiteusers where email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQ)) {
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user1 = userEmailUniqueCheck.mapRow(resultSet);
                if ((user1 != null)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public void update(User user) {

    }
}
