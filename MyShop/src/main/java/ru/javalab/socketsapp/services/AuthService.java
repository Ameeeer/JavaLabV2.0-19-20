package ru.javalab.socketsapp.services;

import ru.javalab.socketsapp.models.User;
import ru.javalab.socketsapp.repositories.CrudImpl;
import ru.javalab.socketsapp.repositories.CrudInterface;

import java.util.Optional;

public class AuthService implements AuthServiceInteface {
    private CrudInterface dbService = new CrudImpl();

    public AuthService() {
    }

    @Override
    public boolean isAdmin(User user) {

        return false;
    }

    @Override
    public User authUser(User user) {
        Optional<User> checkUser = dbService.checkUser(user);
        if (checkUser.isPresent()) {
            return checkUser.get();
        }else {
            registrateUser(user);
        }
        return null;
    }

    @Override
    public String registrateUser(User user) {

        return null;
    }
}
