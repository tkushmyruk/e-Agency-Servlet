package ua.taras.kushmyruk.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import ua.taras.kushmyruk.exception.DaoException;
import ua.taras.kushmyruk.model.CreditCard;
import ua.taras.kushmyruk.model.Message;
import ua.taras.kushmyruk.model.User;

public interface UserDao {
    long saveUser(User user) throws DaoException;

    User findUserByUsername(String username);

    List<User> findAllUsers();

    void setActive(String username);

    void setBlock(String username);

    void changeUserRole(String username, String userRole);

    void updateUser(String username, String password, String email);

    void setCreditCard(String username, String cardNumber, String cardPassword);

    void updateCreditCardBalance(String username, double balance);

    CreditCard findCreditCardByNumber(String cardNumber);

    void saveMessage(String topic, String tag, String text, String username);

    List<Message> getUserMessages(String username);
}
