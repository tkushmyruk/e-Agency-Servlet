package ua.taras.kushmyruk.dao;

import java.sql.Connection;
import java.sql.SQLException;
import ua.taras.kushmyruk.exception.DaoException;
import ua.taras.kushmyruk.model.User;

public interface UserDao {
    long saveUser(User user) throws DaoException;

    User findUserByUsername(String username);
}
