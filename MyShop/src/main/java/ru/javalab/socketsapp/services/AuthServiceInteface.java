package ru.javalab.socketsapp.services;

import ru.javalab.socketsapp.models.User;

public interface AuthServiceInteface {
    boolean isAdmin(User user);
    User authUser(User user);
    String registrateUser(User user);
}
