package ru.javalab.socketsapp.repositories;

import ru.javalab.socketsapp.models.User;
import ru.javalab.socketsapp.rowmappers.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class CrudImpl implements CrudInterface {
    private Connection connection;

    public CrudImpl() {
        ru.javalab.socketsapp.connetions.Connection connection = new ru.javalab.socketsapp.connetions.Connection();
        this.connection = connection.connectToDB();
    }

    @Override
    public boolean saveUser(User user) {
        String sqlQuery = "INSERT INTO appusers(login, password,token) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, );
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public RowMapper<User> userRowMapper = rs ->
            new User(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("password")
            );
    public RowMapper<User> userRowedWithToken= rs ->
            new User(
                    rs.getString("login"),
                    rs.getString("token")
            );

    @Override
    public Optional<User> checkUser(User user) {
        String sqlQuery = "SELECT * from appusers where login = ? and password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                User u = null;
                if (resultSet.next())
                    u = userRowMapper.mapRow(resultSet);
                return Optional.ofNullable(u);
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
            return Optional.empty();
        }
    }

    @Override
    public void saveMessageWithId(Long id, String message) {
        String sqlQuery = "INSERT into idsandmessages (id, time, message) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            Date date = new Date();
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, String.valueOf(date));
            preparedStatement.setString(3, message);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
