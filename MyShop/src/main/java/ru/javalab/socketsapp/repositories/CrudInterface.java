package ru.javalab.socketsapp.repositories;

import ru.javalab.socketsapp.models.User;

import java.util.Optional;

public interface CrudInterface {
    boolean saveUser(User user);

    Optional<User> checkUser(User user);

    void saveMessageWithId(Long id, String message);
}
