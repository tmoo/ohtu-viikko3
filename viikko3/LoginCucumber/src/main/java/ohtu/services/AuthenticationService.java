package ohtu.services;

import ohtu.domain.User;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private UserDao userDao;

    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        if (password.length() < 8) {
            return true;
        }
        
        boolean erik = false;
        for (char c : password.toCharArray()) {
            if (!Character.isLetter(c)) {
                erik = true;
            }
        }
        if (!erik) {
            return true;
        }
        
        if (username.length() < 3) {
            return true;
        }
        
        for (char c : username.toCharArray()) {
            if (!Character.isLetter(c)) {
                return true;
            }
        }
        
        return false;
    }
}
