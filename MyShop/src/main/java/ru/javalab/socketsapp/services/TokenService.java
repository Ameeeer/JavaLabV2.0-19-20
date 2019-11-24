package ru.javalab.socketsapp.services;

import org.apache.commons.codec.binary.Base64;
import ru.javalab.socketsapp.models.User;
import ru.javalab.socketsapp.repositories.CrudImpl;
import ru.javalab.socketsapp.repositories.CrudInterface;

import java.sql.SQLException;

public class TokenService {
    private CrudInterface dbService = new CrudImpl();
    public String getToken(User user) {
        try {
            if (dbService.saveUser(user)) {
                User newUser = dbService.getUserIdAndRole(user);
                byte[] bytesEncode = Base64.encodeBase64((newUser.getId() + "_" + newUser.getRole()).getBytes());
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
