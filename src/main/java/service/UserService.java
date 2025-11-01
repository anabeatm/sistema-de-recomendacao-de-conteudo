package service;

import dao.UserDAO;
import domain.User;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User registerNewUser(String name, String email) {
        if(userDAO.findByEmail(email) != null) {
            throw new RuntimeException("Error: e-mail alredy registered");
        }
        User newUser = new User(name, email);
        return userDAO.save(newUser);
    }

}
