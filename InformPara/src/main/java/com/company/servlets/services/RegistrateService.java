package com.company.servlets.services;

import com.company.servlets.ConnectionDB;
import com.company.servlets.models.User;
import com.company.servlets.repositories.UsersRepoIMLP;
import com.company.servlets.repositories.UsersRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Optional;

public class RegistrateService {
    ConnectionDB connectionDB = new ConnectionDB();

    public boolean registrateUser(User newuser) {
        UsersRepository usersRepository = null;
        try {
            usersRepository = new UsersRepoIMLP(connectionDB.getConnetion());
            boolean reg = usersRepository.save(newuser);
            if (reg) {
                return false;
            }else {
                return true;
            }
        } catch (SQLException | ClassNotFoundException | NamingException e) {
            return true;
        }
    }
}
