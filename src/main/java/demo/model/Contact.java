package demo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Contact {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    Logger logger = LoggerFactory.getLogger(Contact.class);

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Contact setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Contact setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
