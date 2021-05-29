package ua.taras.kushmyruk.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import ua.taras.kushmyruk.exception.DaoException;
import ua.taras.kushmyruk.model.User;

public interface UserDao {
    long saveUser(User user) throws DaoException;

    User findUserByUsername(String username);

    List<User> findAllUsers();

    void setActive(String username);

    void setBlock(String username);

    void changeUserRole(String username, String userRole);
}
