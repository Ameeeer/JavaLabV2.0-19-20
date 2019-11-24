package ru.javalab.socketsapp.repositories;

import ru.javalab.socketsapp.models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface DBService extends CrudInterface {
    User getUserIdAndRole(User user);
    void saveMessageWithId(Long id, String message);
}
