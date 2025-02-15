package demo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
    private String email;
    private String password;

    Logger logger = LoggerFactory.getLogger(User.class);
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

}
